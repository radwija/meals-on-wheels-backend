package com.lithan.mow.entity;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MealPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "main_course")
    private String mainCourse;

    private String salad;
    private String soup;
    private String dessert;
    private String drink;

    @Column(name = "frozen")
    private boolean frozen;

    @Column(name = "active")
    private boolean active;

    @Column(name = "package_image")
    private String packageImage;

    public MealPackage() {
    }

    public MealPackage(String packageName, String mainCourse, String salad, String soup, String dessert,
                       String drink, boolean frozen, boolean active, String packageImage) {
        this.packageName = packageName;
        this.mainCourse = mainCourse;
        this.salad = salad;
        this.soup = soup;
        this.dessert = dessert;
        this.drink = drink;
        this.frozen = frozen;
        this.active = active;
        this.packageImage = packageImage;
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

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public String getPackageImage() {
        return packageImage;
    }

    public void setPackageImage(String packageImage) {
        this.packageImage = packageImage;
    }
}
