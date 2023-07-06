package com.lithan.mow.controller;

import com.lithan.mow.entity.Customer;
import com.lithan.mow.entity.Order;
import com.lithan.mow.constraint.EStatus;
import com.lithan.mow.payload.response.MessageResponse;
import com.lithan.mow.payload.response.OrderResponse;
import com.lithan.mow.repository.CustomerRepository;
import com.lithan.mow.repository.OrderRepository;
import com.lithan.mow.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("hasAnyRole('ROLE_DRIVER','ROLE_VOLUNTEER')")
@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderRepository orderRepository;

    //View order delivery
    @GetMapping("/order")
    public List<OrderResponse> getOrder() {
        List<OrderResponse> orderList = new ArrayList<>();
        orderRepository.findByStatusAndDeliveredBy(EStatus.READY_TO_DELIVER, customerService.getCurrentUser())
                .forEach(order -> orderList.add(new OrderResponse(order)));
        orderRepository.findByStatusAndDeliveredBy(EStatus.ON_DELIVERY, customerService.getCurrentUser())
                .forEach(order -> orderList.add(new OrderResponse(order)));

        return orderList;
    }

    //View all order delivery
    @GetMapping("/order/all")
    public List<OrderResponse> getAllOrder() {
        List<OrderResponse> orderList = new ArrayList<>();
        orderRepository.findByDeliveredBy(customerService.getCurrentUser())
                .forEach(order -> orderList.add(new OrderResponse(order)));

        return orderList;
    }

    //Deliver order
    @GetMapping("/order/{id}/deliver")
    public MessageResponse deliverOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id).get();
        Customer driver = customerService.getCurrentUser();

        order.setStatus(EStatus.ON_DELIVERY);
        driver.setStatus(EStatus.BUSY);
        order.setDeliveredBy(driver);

        orderRepository.save(order);
        customerRepository.save(driver);

        return new MessageResponse("deliver order_id: " + id);
    }

    //Complete Order Delivery
    @GetMapping("/order/{id}/complete")
    public MessageResponse deliverOrderComplete(@PathVariable Long id) {
        Order order = orderRepository.findById(id).get();
        Customer driver = customerService.getCurrentUser();

        if (orderRepository.findByStatusAndDeliveredBy(EStatus.ON_DELIVERY, driver).isEmpty())
            driver.setStatus(EStatus.AVAILABLE);
        
        order.setStatus(EStatus.DELIVERY_COMPLETE);

        orderRepository.save(order);
        customerRepository.save(driver);

        return new MessageResponse("deliver order_id: " + id);
    }

    @GetMapping("/status/{statuscode}")
    public MessageResponse setStatus(@PathVariable Long statuscode) {
        Customer driver = customerService.getCurrentUser();

        EStatus status = null;
        if (statuscode == 1) {
            status = EStatus.AVAILABLE;
        }
        if (statuscode == 2) {
            status = EStatus.BUSY;
        }
        if (statuscode == 3) {
            status = EStatus.NOT_AVAILABLE;
        }

        driver.setStatus(status);
        customerRepository.save(driver);
        System.out.println(driver.getStatus());

        return new MessageResponse("Status set: " + status );
    }

}
