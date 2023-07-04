package com.lithan.mow.controller;

import com.lithan.mow.constraint.ERole;
import com.lithan.mow.constraint.EStatus;
import com.lithan.mow.entity.MealPackage;
import com.lithan.mow.entity.Order;
import com.lithan.mow.entity.Partner;
import com.lithan.mow.entity.Customer;
import com.lithan.mow.payload.request.FeedbackRequest;
import com.lithan.mow.payload.request.MealPackageRequest;
import com.lithan.mow.payload.response.*;
import com.lithan.mow.repository.*;
import com.lithan.mow.service.FileStorageService;
import com.lithan.mow.property.FileStorageProperties;
import com.lithan.mow.service.OrderService;
import com.lithan.mow.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RestController
@RequestMapping("/admin")

public class AdminController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private MealPackageRepository mealPackageRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/order/pending")
    public List<OrderResponse> getPendingOrder() {
        return orderService.getOrderWithStatus(EStatus.PENDING);
    }

    @GetMapping("/order/{orderId}/prepare/{partnerId}")
    public MessageResponse assignPartner(@PathVariable("orderId") Long orderId,
                                         @PathVariable("partnerId") Long partnerId) {
        Order order = orderRepository.findById(orderId).get();
        order.setPreparedBy(partnerRepository.findById(partnerId).get());

        orderRepository.save(order);
        return new MessageResponse(String.format("order %d assign to partner %d", orderId, partnerId));
    }

    @GetMapping("/order/ready-to-deliver")
    public List<OrderResponse> getReadyToDeliverOrder() {
        return orderService.getOrderWithStatus(EStatus.READY_TO_DELIVER);
    }

    @GetMapping("/order/{orderId}/deliver/{driverId}")
    public MessageResponse assignDriver(@PathVariable("orderId") Long orderId, @PathVariable("driverId") Long driverId) {
        Order order = orderRepository.findById(orderId).get();
        order.setDeliveredBy(customerRepository.findById(driverId).get());

        orderRepository.save(order);
        return new MessageResponse(String.format("order %d assign to driver %d", orderId, driverId));
    }

    @GetMapping("/order/all")
    public List<OrderResponse> getOrder() {
        List<OrderResponse> orderList = new ArrayList<>();
        orderRepository.findAll().forEach(data -> orderList.add(new OrderResponse(data)));

        return orderList;
    }

    @GetMapping("/user")
    public List<CustomerResponse> getUser() {
        List<CustomerResponse> customerList = new ArrayList<>();
        customerRepository.findByRoleIsNot(ERole.ROLE_ADMIN).forEach(data -> customerList.add(new CustomerResponse(data)));
        //
        Collections.sort(customerList, CustomerResponse.comparatorByIdDesc);

        return customerList;
    }
    @GetMapping("/partner")
    public List<CustomerResponse> getPartner() {
        List<CustomerResponse> customerList = new ArrayList<>();
        partnerRepository.findAll().forEach(
                x -> customerList.add(CustomerResponse.builder().id(x.getId()).name(x.getName()).profilePicture(x.getProfilePicture())
                        .address(x.getAddress()).email(x.getEmail()).status(x.getStatus()).role(ERole.ROLE_PARTNER).active(x.isActive()).build()));
        Collections.sort(customerList, CustomerResponse.comparatorByIdDesc);

        return customerList;
    }

    @GetMapping("/user/count")
    public UserCountResponse getUserCount() {

        int partner = partnerRepository.findByActive(true).size();
        int driver = customerRepository.findByRoleAndActive(ERole.ROLE_DRIVER, true).size();
        int customer = customerRepository.findByRoleAndActive(ERole.ROLE_MEMBER, true).size();
        int volunteer = customerRepository.findByRoleAndActive(ERole.ROLE_VOLUNTEER, true).size();

        return new UserCountResponse(customer, volunteer, partner, driver);
    }

    @GetMapping("/driver")
    public List<CustomerResponse> getDriver() {
        List<CustomerResponse> customerList = new ArrayList<>();
        customerRepository.findByRoleAndActive(ERole.ROLE_DRIVER, true)
                .forEach(data -> customerList.add(new CustomerResponse(data)));

        return customerList;
    }

    @GetMapping("/menu")
    public List<MealPackageRequest> getMeal() {
        List<MealPackageRequest> mealList = new ArrayList<>();
        mealPackageRepository.findAll().forEach(data -> mealList.add(new MealPackageRequest(data)));

        return mealList;
    }

    @GetMapping("/user/{id}/activate")
    public MessageResponse activateUser(@PathVariable Long id) {
        Customer user = customerRepository.findById(id).get();
        user.setActive(true);
        customerRepository.save(user);

        return new MessageResponse(String.format("activate user with id: %d", id));
    }

    @GetMapping("/user/{id}/{rolecode}")
    public MessageResponse assignVolunteerRole(@PathVariable Long id, @PathVariable Long rolecode) {
        Customer user = customerRepository.findById(id).get();
        user.setActive(true);
        ERole role = null;
        if(rolecode == 1) {
            role = ERole.ROLE_DRIVER;
        } else {
            role = ERole.ROLE_CAREGIVER;
        }
        user.setRole(role);
        customerRepository.save(user);

        return new MessageResponse(String.format("Assigned volunteer with id: %d", id));
    }

    @GetMapping("/partner/{id}/activate")
    public MessageResponse activatePartner(@PathVariable Long id) {
        Partner partner = partnerRepository.findById(id).get();
        partner.setActive(true);
        partnerRepository.save(partner);

        return new MessageResponse(String.format("activate user with id: %d", id));
    }

    @GetMapping("/feedback")
    public List<FeedbackRequest> getFeedback() {
        List<FeedbackRequest> feedbackList = new ArrayList<>();
        feedbackRepository.findAll().forEach(data -> feedbackList.add(new FeedbackRequest(data)));

        return feedbackList;
    }

    @GetMapping("/menu/{id}/active")
    public MessageResponse activateMenu(@PathVariable Long id) {
        MealPackage menu = mealPackageRepository.findById(id).get();
        menu.setActive(true);
        mealPackageRepository.save(menu);
        return new MessageResponse("menu active: "+id);
    }
    @GetMapping("/menu/{id}/deactive")
    public MessageResponse deactivateMenu(@PathVariable Long id) {
        MealPackage menu = mealPackageRepository.findById(id).get();
        menu.setActive(false);
        mealPackageRepository.save(menu);
        return new MessageResponse("menu deactive: "+id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("menu/add")
    public MessageResponse addMenu(@RequestParam("packageName") String packageName,
                                   @RequestParam("mainCourse") String mainCourse, @RequestParam("salad") String salad,
                                   @RequestParam("soup") String soup,
                                   @RequestParam("dessert") String dessert, @RequestParam("drink") String drink,
                                   @RequestParam("frozen") String frozen, @RequestParam("packageImage") MultipartFile packageImage) {

        boolean frozenBool = false;

        if (frozen.equals("1")) {
            frozenBool = true;
        }

        String imageName = fileStorageService.storeFile(packageImage);
        String imageDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/file/downloadFile/")
                .path(imageName).toUriString();

        MealPackage meal = new MealPackage();
        meal.setPackageName(packageName);
        meal.setMainCourse(mainCourse);
        meal.setSalad(salad);
        meal.setSoup(soup);
        meal.setDessert(dessert);
        meal.setDrink(drink);
        meal.setFrozen(frozenBool);
        meal.setPackageImage(imageDownloadUri);
        meal.setActive(true);
        mealPackageRepository.save(meal);

        return new MessageResponse("success add menu with name: "+ meal.getPackageName());
    }

}
