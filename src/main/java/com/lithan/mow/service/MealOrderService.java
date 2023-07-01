package com.lithan.mow.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lithan.mow.constraint.EStatus;
import com.lithan.mow.payload.response.MealOrderResponse;
import com.lithan.mow.repository.MealOrderRepository;

@Service
@Transactional
public class MealOrderService {

    @Autowired
    private MealOrderRepository mealOrderRepository;

    public List<MealOrderResponse> getMealOrdersWithStatus(EStatus status) {
        List<MealOrderResponse> mealOrderList = new ArrayList<>();
        mealOrderRepository.findByStatus(String.valueOf(status)).forEach(data -> mealOrderList.add(new MealOrderResponse(data)));
        return mealOrderList;
    }
}
