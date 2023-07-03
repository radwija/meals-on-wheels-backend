package com.lithan.mow.controller;

import com.lithan.mow.entity.Partner;
import com.lithan.mow.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/partner/")

public class PartnershipController {

    @Autowired
    PartnerService partnerService;

    @PostMapping("apply")
    public ResponseEntity<?> applyPartnership(@RequestParam("companyName") String companyName,
                                              @RequestParam("companyEmail") String companyEmail,
                                              @RequestParam("companyAddress") String companyAddress,
                                              @RequestParam("password") String password,
                                              @RequestParam("photo")MultipartFile file){
        if(partnerService.isPartnerExist(companyEmail)){
            return ResponseEntity.badRequest().body("Email already exists, please use different email");
        }
        if (file.getSize() > 304857 ) {
            return ResponseEntity.badRequest().body("File size exceeds the allowed limit. File must be under 300 KB.");
        }

        byte[] photo;
        Partner partner = new Partner();
        partner.setName(companyName);
        partner.setEmail(companyEmail);
        partner.setAddress(companyAddress);
        partner.setPassword(password);
        try {
            photo = file.getBytes();
            partner.setProfilePicture(photo);
            Partner newPartner =  partnerService.save(partner);
            if (newPartner != null) {
                return ResponseEntity.ok("Registration successful.");
            } else {
                return ResponseEntity.status(500).body("Failed to register partner.");
            }
        }catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Something wrong with the file or photo");
        }
    }

}
