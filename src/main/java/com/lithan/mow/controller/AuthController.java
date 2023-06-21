package com.lithan.mow.controller;

import com.lithan.mow.constraint.EGender;
import com.lithan.mow.constraint.ERole;
import com.lithan.mow.entity.Customer;
import com.lithan.mow.payload.request.RegistrationRequest;
import com.lithan.mow.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    CustomerService customerService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request){
        if(customerService.isCustomerExist(request.getEmail())){
            return ResponseEntity.badRequest().body("Email already exists.");
        }
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setGender(EGender.valueOf(request.getGender()));
        customer.setRole(ERole.valueOf(request.getRole()));
        customer.setPassword(request.getPassword());


        Customer newUser =  customerService.register(customer);

        if (newUser != null) {
            return ResponseEntity.ok("Registration successful.");
        } else {
            return ResponseEntity.status(500).body("Failed to register user.");
        }

    }
}
