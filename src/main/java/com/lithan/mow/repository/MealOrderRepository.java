package com.lithan.mow.repository;

import com.lithan.mow.entity.MealOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealOrderRepository extends JpaRepository<MealOrder, Long> {
    List<MealOrder> findByStatus(String status);

    List<MealOrder> findByBuyerName(String buyerName);

    List<MealOrder> findByMealName(String mealName);

    List<MealOrder> findByCompany(String company);

    List<MealOrder> findByDeliveryRider(String deliveryRider);
}
