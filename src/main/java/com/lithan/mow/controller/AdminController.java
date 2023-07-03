package com.lithan.mow.controller;

import com.lithan.mow.constraint.ERole;
import com.lithan.mow.constraint.EStatus;
import com.lithan.mow.entity.MealPackage;
import com.lithan.mow.entity.Order;
import com.lithan.mow.entity.Partner;
import com.lithan.mow.entity.Customer;
import com.lithan.mow.payload.response.MessageResponse;
import com.lithan.mow.payload.response.OrderResponse;
import com.lithan.mow.payload.response.PartnerResponse;
import com.lithan.mow.payload.response.CustomerResponse;
import com.lithan.mow.repository.MealPackageRepository;
import com.lithan.mow.repository.OrderRepository;
import com.lithan.mow.repository.PartnerRepository;
import com.lithan.mow.repository.CustomerRepository;
import com.lithan.mow.service.OrderService;
import com.lithan.mow.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CAREGIVER','ROLE_PARTNER')")
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
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

    @GetMapping("/meal")
    public List<MealPackage> getMealPackages() {
        return mealPackageRepository.findAll();
    }

    @GetMapping("/order/all")
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/order/complete")
    public List<OrderResponse> getCompleteOrders() {
        List<Order> orders = orderService.getOrderWithStatus(EStatus.ORDER_COMPLETE);
        return orders.stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/order/delivery-complete")
    public List<OrderResponse> getDeliveryCompleteOrders() {
        List<Order> orders = orderService.getOrderWithStatus(EStatus.DELIVERY_COMPLETE);
        return orders.stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/order/on-delivery")
    public List<OrderResponse> getOnDeliveryOrders() {
        List<Order> orders = orderService.getOrderWithStatus(EStatus.ON_DELIVERY);
        return orders.stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/order/pending")
    public List<OrderResponse> getPendingOrders() {
        List<Order> orders = orderService.getOrderWithStatus(EStatus.PENDING);
        return orders.stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/order/ready-to-deliver")
    public List<OrderResponse> getReadyToDeliverOrders() {
        List<Order> orders = orderService.getOrderWithStatus(EStatus.READY_TO_DELIVER);
        return orders.stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/count")
    public int getCustomerCount() {
        return customerRepository.countAllBy();
    }

    @GetMapping("/customer")
    public List<Customer> getCustomers() {
        return customerRepository.findAllByRoleNotOrderByCreatedAtDesc(ERole.ROLE_ADMIN);
    }

    @GetMapping("/customer/{id}/activate")
    public MessageResponse activateCustomer(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        customer.setActive(true);
        customerRepository.save(customer);

        return new MessageResponse(String.format("Activated customer with ID: %d", id));
    }

    @GetMapping("/partner")
    public List<Partner> getPartners() {
        return partnerRepository.findAll();
    }

    @GetMapping("/partner/{id}/activate")
    public MessageResponse activatePartner(@PathVariable Long id) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Partner not found"));

        partner.setActive(true);
        partnerRepository.save(partner);

        return new MessageResponse(String.format("Activated partner with ID: %d", id));
    }

    @GetMapping("/partner/active")
    public List<Partner> getActivePartners() {
        return partnerRepository.findByActive(true);
    }

    @GetMapping("/driver")
    public List<Customer> getDrivers() {
        return customerRepository.findAllByRole(ERole.ROLE_DRIVER);
    }

    @GetMapping("/driver/active")
    public List<Customer> getActiveDrivers() {
        return customerRepository.findAllByRoleAndActive(ERole.ROLE_DRIVER, true);
    }

    @GetMapping("/customer/{id}/{rolecode}")
    public MessageResponse assignVolunteerRole(@PathVariable Long id, @PathVariable Long rolecode) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        customer.setActive(true);
        ERole role = (rolecode == 1) ? ERole.ROLE_DRIVER : ERole.ROLE_CAREGIVER;
        customer.setRole(role);
        customerRepository.save(customer);

        return new MessageResponse(String.format("Assigned volunteer with ID: %d", id));
    }

    @PostMapping("/order/{orderId}/deliver/{riderId}")
    public MessageResponse deliverOrder(@PathVariable Long orderId, @PathVariable Long riderId) {
        orderService.deliverOrder(orderId, riderId);
        return new MessageResponse("Order delivered successfully");
    }

    @PostMapping("/order/{orderId}/prepare/{partnerId}")
    public MessageResponse prepareOrder(@PathVariable Long orderId, @PathVariable Long partnerId) {
        orderService.prepareOrder(orderId, partnerId);
        return new MessageResponse("Order prepared successfully");
    }

    @GetMapping("/partner/current")
    public PartnerResponse getCurrentPartner() {
        Partner partner = customerService.getCurrentPartner();
        return new PartnerResponse(partner);
    }

    @GetMapping("/customer/current")
    public CustomerResponse getCurrentCustomer() {
        Customer customer = customerService.getCurrentUser();
        return new CustomerResponse(customer);
    }
}
