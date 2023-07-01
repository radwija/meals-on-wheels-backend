package com.lithan.mow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meal_orders")
public class MealOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "meal_name")
    private String mealName;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "order_date")
    private String orderDate;

    private String company;

    @Column(name = "delivery_rider")
    private String deliveryRider;

    private String status;

    public MealOrder() {
    }

    public MealOrder(String orderNumber, String buyerName, String mealName, String deliveryAddress,
                     String orderDate, String company, String deliveryRider, String status) {
        this.orderNumber = orderNumber;
        this.buyerName = buyerName;
        this.mealName = mealName;
        this.deliveryAddress = deliveryAddress;
        this.orderDate = orderDate;
        this.company = company;
        this.deliveryRider = deliveryRider;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDeliveryRider() {
        return deliveryRider;
    }

    public void setDeliveryRider(String deliveryRider) {
        this.deliveryRider = deliveryRider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
