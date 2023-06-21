package com.lithan.mow.entity;

import com.lithan.mow.constraint.EGender;
import com.lithan.mow.constraint.ERole;
import com.lithan.mow.constraint.EStatus;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String address;

    private String password;

    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;

    @Lob
    @Column(name = "profile_background")
    private byte[] profileBackground;

    @Lob
    @Column(name = "qualification")
    private byte[] qualification;

    @Enumerated(EnumType.STRING)
    private EGender gender;

    @Enumerated(EnumType.STRING)
    private ERole role;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    private boolean active;

    private double distance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public byte[] getProfileBackground() {
        return profileBackground;
    }

    public void setProfileBackground(byte[] profileBackground) {
        this.profileBackground = profileBackground;
    }

    public byte[] getQualification() {
        return qualification;
    }

    public void setQualification(byte[] qualification) {
        this.qualification = qualification;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
