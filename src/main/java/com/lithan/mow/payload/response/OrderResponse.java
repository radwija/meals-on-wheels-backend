package com.lithan.mow.payload.response;

import java.util.Date;
import java.util.Optional;

import com.lithan.mow.entity.Order;
import com.lithan.mow.constraint.EStatus;

import lombok.Data;

@Data
public class OrderResponse {
    private Long id;
    private CustomerResponse orderBy;
    private PartnerResponse preparedBy;
    private CustomerResponse deliveredBy;
    private MealPackage mealPackage;
    private Date orderOn;
    private EStatus orderStatus;

    public OrderResponse(Order order) {
        if (Optional.ofNullable(order.getPreparedBy()).isPresent()) {
            this.preparedBy = new PartnerResponse(order.getPreparedBy());
        }
        if (Optional.ofNullable(order.getDeliveredBy()).isPresent()) {

            this.deliveredBy = new CustomerResponse(order.getDeliveredBy());
        }
        this.id = order.getId();
        this.orderBy = new CustomerResponse(order.getOrderedBy());
        this.orderOn = order.getOrderedOn();
        this.orderStatus = order.getStatus();
        this.mealPackage = order.getMealPackage();
    }
}