package com.lithan.mow.payload.response;

import com.lithan.mow.entity.Partner;
import com.lithan.mow.constraint.EStatus;

public class PartnerResponse {
    private Long id;
    private String name;
    private String email;
    private String address;
    private EStatus status;
    private byte[] profilePicture;

    public PartnerResponse(Partner partner){
        this.id = partner.getId();
        this.name = partner.getName();
        this.address = partner.getAddress();
        this.email = partner.getEmail();
        this.status = partner.getStatus();
        this.profilePicture= partner.getProfilePicture();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

}
