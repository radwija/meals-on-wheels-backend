package com.lithan.mow.payload.request;

import org.springframework.web.multipart.MultipartFile;

public class RegistrationRequest {
    private String name;
    private String email;
    private String address;
    private String gender;
    private String role;
    private String password;

    private MultipartFile qualification;

    private MultipartFile photo;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultipartFile getQualification() {
        return qualification;
    }

    public void setQualification(MultipartFile qualification) {
        this.qualification = qualification;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
