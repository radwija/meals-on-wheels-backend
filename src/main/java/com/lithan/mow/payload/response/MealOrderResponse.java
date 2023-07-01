package com.lithan.mow.payload.response;

import com.lithan.mow.entity.MealOrder;
import com.lithan.mow.constraint.EStatus;

import lombok.Data;

@Data
public class MealOrderResponse {
    private Long id;
    private String orderNumber;
    private String buyerName;
    private String mealName;
    private String deliveryAddress;
    private String orderDate;
    private String company;
    private String deliveryRider;
    private EStatus status;

    public MealOrderResponse() {
    }

    public MealOrderResponse(MealOrder mealOrder) {
        this.id = mealOrder.getId();
        this.orderNumber = mealOrder.getOrderNumber();
        this.buyerName = mealOrder.getBuyerName();
        this.mealName = mealOrder.getMealName();
        this.deliveryAddress = mealOrder.getDeliveryAddress();
        this.orderDate = mealOrder.getOrderDate();
        this.company = mealOrder.getCompany();
        this.deliveryRider = mealOrder.getDeliveryRider();
        this.status = EStatus.valueOf(mealOrder.getStatus());
    }
}
