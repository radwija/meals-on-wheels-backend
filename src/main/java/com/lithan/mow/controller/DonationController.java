package com.lithan.mow.controller;

import com.lithan.mow.entity.Donation;
import com.lithan.mow.payload.request.DonationRequest;
import com.lithan.mow.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/donation")
public class DonationController {
    @Autowired
    private DonationService donationService;

    @PostMapping("/save-donation")
    public ResponseEntity<?> saveDonation(@RequestBody DonationRequest donationRequest) {
       Donation savedDonation = donationService.saveDonation(donationRequest);
       return ResponseEntity.ok(savedDonation);
    }
}
