package com.lithan.mow.controller;

import com.lithan.mow.entity.Customer;
import com.lithan.mow.entity.Partner;
import com.lithan.mow.payload.response.ProfileResponse;
import com.lithan.mow.service.CustomerService;
import com.lithan.mow.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")

public class ProfileController {

    @Autowired
    CustomerService customerService;

    @Autowired
    PartnerService partnerService;

    @GetMapping("profile")
    public ResponseEntity<?> getProfile(@RequestParam("email")String email,@RequestParam("role")String role){
        ProfileResponse res = new ProfileResponse();
        System.out.println(email);
        System.out.println(role);
        if(role.equals("ROLE_PARTNER")){
           Partner partner = partnerService.getPartnerByEmail(email).get();
            res.setName(partner.getName());
            res.setAddress(partner.getAddress());
            res.setEmail(partner.getEmail());
            res.setPicture(partner.getProfilePicture());
            res.setBackground(partner.getProfileBackground());

        }else {
            Customer customer = customerService.getCustomerByEmail(email).get();
            res.setName(customer.getName());
            res.setEmail(customer.getEmail());
            res.setGender(customer.getGender().toString());
            res.setAddress(customer.getAddress());
            res.setPicture(customer.getProfilePicture());
            res.setBackground(customer.getProfileBackground());
        }
        return ResponseEntity.ok(res);
    }

}
