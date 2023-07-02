package com.lithan.mow.controller;

import com.lithan.mow.entity.Customer;
import com.lithan.mow.entity.Feedback;
import com.lithan.mow.entity.MealPackage;
import com.lithan.mow.payload.request.FeedbackRequest;
import com.lithan.mow.payload.request.MealPackageRequest;
import com.lithan.mow.payload.response.MessageResponse;
import com.lithan.mow.repository.FeedbackRepository;
import com.lithan.mow.repository.MealPackageRepository;
import com.lithan.mow.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {


    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    MealPackageRepository mealPackageRepository;

    @Autowired
    CustomerService customerService;


    @GetMapping("/mealcount")
    public List<MealPackage> postFeedback() {

         List<MealPackage> allMeals = mealPackageRepository.findAll();

        return allMeals;
    }

    @PostMapping("/feedback")
    public MessageResponse postFeedback(@Valid @RequestBody FeedbackRequest feedbackRequest) {

        Feedback feedback = new Feedback();
        feedback.setName(feedbackRequest.getName());
        feedback.setEmail(feedbackRequest.getEmail());
        feedback.setMealPackageId(feedbackRequest.getMealPackageId());
        feedback.setFeedback(feedbackRequest.getFeedback());

        feedbackRepository.save(feedback);

        return new MessageResponse("thank for you feedback");
    }

    @GetMapping("/menu")
    public List<MealPackageRequest> getAllMenu(@RequestParam("email") String email) {
        Customer customer = customerService.getCustomerByEmail(email).get();

        // get day name
        Format f = new SimpleDateFormat("EEEE");
        String today = f.format(new Date());

        List<MealPackageRequest> mealPackageList = new ArrayList<>();

        // return frozen meal on weekend and more than 10 km
        if (today.equalsIgnoreCase("sunday") || today.equalsIgnoreCase("saturday") || customer.getDistance() > 10) {

            mealPackageRepository.findByFrozenAndActive(true, true).forEach(data -> mealPackageList.add(new MealPackageRequest(data)));

            return mealPackageList;
        }
        mealPackageRepository.findByFrozenAndActive(false,true).forEach(data -> mealPackageList.add(new MealPackageRequest(data)));

        return mealPackageList;
    }


    @GetMapping("menu/{id}")
    public MealPackageRequest getMenu(@PathVariable Long id) {

        return new MealPackageRequest(mealPackageRepository.findById(id).get());
    }

}
