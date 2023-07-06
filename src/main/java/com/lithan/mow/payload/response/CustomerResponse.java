package com.lithan.mow.payload.response;

import java.util.Comparator;

import com.lithan.mow.entity.Customer;
import com.lithan.mow.constraint.EGender;
import com.lithan.mow.constraint.ERole;
import com.lithan.mow.constraint.EStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerResponse {
    private Long id;
    private String name;
    private boolean active;
    private String email;
    private String address;
    private EGender gender;
    private EStatus status;
    private byte[] profilePicture;
    private ERole role;
    private byte[] qualification;


    public CustomerResponse(Customer user) {
        this.id = user.getId();
        this.name = user.getName();
        this.active = user.isActive();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.gender = user.getGender();
        this.status = user.getStatus();
        this.profilePicture = user.getProfilePicture();
        this.role = user.getRole();
        this.qualification = user.getQualification();
    }

    public static final Comparator<CustomerResponse> comparatorByIdDesc = new Comparator<CustomerResponse>() {

        public int compare(CustomerResponse s1, CustomerResponse s2) {
            Long customer1 = s1.getId();
            Long customer2 = s2.getId();

            return customer2.compareTo(customer1);

        }
    };

}