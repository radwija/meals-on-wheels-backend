package com.lithan.mow.controller;

import com.lithan.mow.entity.Donation;
import com.lithan.mow.payload.request.DonationRequest;
import com.lithan.mow.payload.response.DonationResponse;
import com.lithan.mow.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/donation")
@CrossOrigin(origins = "http://localhost:3000")
public class DonationController {
    @Autowired
    private DonationService donationService;

    @GetMapping
    public void isActive() {

    }

    @PostMapping
    public ResponseEntity<?> saveDonation(@RequestBody DonationRequest donationRequest) {
       DonationResponse<?> result = donationService.saveDonation(donationRequest);
       if (result.getResult() != null) {
           return ResponseEntity.ok(result);
       }
       return ResponseEntity.badRequest().body(result);
    }
}
