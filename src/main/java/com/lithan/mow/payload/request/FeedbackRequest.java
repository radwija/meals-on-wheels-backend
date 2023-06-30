package com.lithan.mow.payload.request;

import javax.validation.constraints.Email;

import com.lithan.mow.entity.Feedback;


public class FeedbackRequest {

    private long id;

    private String name;

    @Email
    private String email;

    private int mealPackageId;

    private String feedback;

    public FeedbackRequest() {

    }

    public FeedbackRequest(Feedback feedback) {
        this.id = feedback.getId();
        this.name = feedback.getName();
        this.email = feedback.getEmail();
        this.mealPackageId = feedback.getMealPackageId();
        this.feedback = feedback.getFeedback();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMealPackageId() {
        return mealPackageId;
    }

    public void setMealPackageId(int mealPackageId) {
        this.mealPackageId = mealPackageId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
