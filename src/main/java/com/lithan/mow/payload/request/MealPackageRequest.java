package com.lithan.mow.payload.request;

import com.lithan.mow.entity.MealPackage;

public class MealPackageRequest {
    private Long id;

    private String packageName;

    private String mainCourse;

    private String salad;

    private String soup;

    private String dessert;

    private String drink;

    private byte[] packageImage;

    private boolean frozen;

    public MealPackageRequest(MealPackage meal) {
        this.id = meal.getId();
        this.packageName = meal.getPackageName();
        this.mainCourse = meal.getMainCourse();
        this.salad = meal.getSalad();
        this.soup = meal.getSoup();
        this.dessert = meal.getDessert();
        this.drink = meal.getDrink();
        this.frozen =  meal.isFrozen();
        this.packageImage = meal.getPackageImage();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(String mainCourse) {
        this.mainCourse = mainCourse;
    }

    public String getSalad() {
        return salad;
    }

    public void setSalad(String salad) {
        this.salad = salad;
    }

    public String getSoup() {
        return soup;
    }

    public void setSoup(String soup) {
        this.soup = soup;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public byte[] getPackageImage() {
        return packageImage;
    }

    public void setPackageImage(byte[] packageImage) {
        this.packageImage = packageImage;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}