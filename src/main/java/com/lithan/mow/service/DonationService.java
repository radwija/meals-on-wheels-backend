package com.lithan.mow.service;

import com.lithan.mow.entity.Donation;
import com.lithan.mow.payload.request.DonationRequest;
import com.lithan.mow.payload.response.DonationResponse;
import com.lithan.mow.repository.DonationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    public DonationResponse<?> saveDonation(DonationRequest donationRequest) {
        DonationResponse<Donation> response = new DonationResponse<>();

        BigDecimal minAmount = new BigDecimal("0.01");
        BigDecimal maxAmount = new BigDecimal("9999999.99");

        if (donationRequest.getAmount().compareTo(minAmount) < 0) {
            response.setCode(400);
            response.setMessage("Amount of money is under minimum of amount ($ 0.01)");
            return response;
        }
        else if (donationRequest.getAmount().compareTo(maxAmount) > 0) {
            response.setCode(400);
            response.setMessage("Amount of money is over maximum of amount ($ 9,999,999.99)");
            return response;
        }

        Donation donation = new Donation();
        BeanUtils.copyProperties(donationRequest, donation);

        donationRepository.save(donation);

        response.setCode(200);
        response.setMessage("Donation information successfully recorded!");
        response.setResult(donation);

        return response;
    }
}
