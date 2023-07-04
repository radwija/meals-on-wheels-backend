package com.lithan.mow.payload.response;

public class UserCountResponse {
    private int totalUser;
    private int totalVolunteer;
    private int totalPartner;
    private int totalDriver;

    public UserCountResponse() {

    }

    public UserCountResponse(int totalUser, int totalVolunteer, int totalPartner, int totalDriver) {
        this.totalUser = totalUser;
        this.totalVolunteer = totalVolunteer;
        this.totalPartner = totalPartner;
        this.totalDriver = totalDriver;
    }

    public int getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(int totalUser) {
        this.totalUser = totalUser;
    }

    public int getTotalVolunteer() {
        return totalVolunteer;
    }

    public void setTotalVolunteer(int totalVolunteer) {
        this.totalVolunteer = totalVolunteer;
    }

    public int getTotalPartner() {
        return totalPartner;
    }

    public void setTotalPartner(int totalPartner) {
        this.totalPartner = totalPartner;
    }

    public int getTotalDriver() {
        return totalDriver;
    }

    public void setTotalDriver(int totalDriver) {
        this.totalDriver = totalDriver;
    }

    @Override
    public String toString() {
        return "UserCountResponse [totalUser=" + totalUser + ", totalVolunteer=" + totalVolunteer + ", totalPartner="
                + totalPartner + ", totalDriver=" + totalDriver + "]";
    }
}
