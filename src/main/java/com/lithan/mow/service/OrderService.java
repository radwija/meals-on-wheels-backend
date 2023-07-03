package com.lithan.mow.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.lithan.mow.entity.Customer;
import com.lithan.mow.entity.Order;
import com.lithan.mow.entity.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lithan.mow.constraint.EStatus;
import com.lithan.mow.payload.response.OrderResponse;
import com.lithan.mow.repository.OrderRepository;
import com.lithan.mow.repository.CustomerRepository;
import com.lithan.mow.repository.PartnerRepository;

@Service
@Transactional
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PartnerRepository partnerRepository;
    public List<Order> getOrderWithStatus(EStatus status) {
        List<OrderResponse>orderList = new ArrayList<>();
        orderRepository.findByStatus(status).forEach(data -> orderList.add(new OrderResponse(data)));
        return orderList;
    }

    public void deliverOrder(Long orderId, Long riderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        Partner rider = partnerRepository.findById(riderId)
                .orElseThrow(() -> new IllegalArgumentException("Rider not found"));

        order.setStatus(EStatus.ON_DELIVERY);
        order.setDriver(rider);

        orderRepository.save(order);
    }

    public void prepareOrder(Long orderId, Long partnerId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        Partner partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new IllegalArgumentException("Partner not found"));

        order.setStatus(EStatus.READY_TO_DELIVER);
        order.setPreparedBy(partner);

        orderRepository.save(order);
    }
}