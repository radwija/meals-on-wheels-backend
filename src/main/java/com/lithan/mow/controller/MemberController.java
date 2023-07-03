package com.lithan.mow.controller;

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
import java.util.Date;
import java.util.List;

@PreAuthorize("hasRole('ROLE_MEMBER')")
@RestController
@RequestMapping("/api/member")
public class MemberController {

   @Autowired
   MealPackageRepository mealPackageRepository;

   @Autowired
   OrderRepository orderRepository;

   @Autowired
   CustomerRepository customerRepository;

   @Autowired
   CustomerService customersService;

   @GetMapping("/order")
   public List<OrderResponse> getUncompletedOrder() {
      List<OrderResponse> orderList = new ArrayList<>();
      orderRepository.findByStatusIsNotAndOrderedBy(EStatus.ORDER_COMPLETE, customersService.getCurrentUser())
            .forEach(order -> orderList.add(new OrderResponse(order)));
      return orderList;
   }

   @GetMapping("/order/all")
   public List<OrderResponse> getAllOrder() {
      List<OrderResponse> orderList = new ArrayList<>();
      orderRepository.findByStatusAndOrderedBy(EStatus.DELIVERY_COMPLETE, customersService.getCurrentUser())
            .forEach(order -> orderList.add(new OrderResponse(order)));
      return orderList;
   }

   @GetMapping("/order/{id}/create")
   public MessageResponse orderMeal(@PathVariable Long id) {
      MealPackage meal = mealPackageRepository.findById(id).get();

      Order orderRequest = new Order();
      orderRequest.setMealPackage(meal);
      orderRequest.setOrderedOn(new Date());
      orderRequest.setStatus(EStatus.PENDING);
      orderRequest.setOrderedBy(customersService.getCurrentUser());

      orderRepository.save(orderRequest);

      return new MessageResponse("You Have Successfully Requested an Order");
   }

   @GetMapping("/order/{id}/complete")
   public MessageResponse completeOrder(@PathVariable Long id) {
      Order order = orderRepository.findById(id).get();
      order.setStatus(EStatus.ORDER_COMPLETE);

      orderRepository.save(order);

      return new MessageResponse("Happy Eating, Hope You are Enjoying Our Meal");
   }

}
