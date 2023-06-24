package com.lithan.mow.service;

import com.lithan.mow.entity.Donation;
import com.lithan.mow.payload.request.DonationRequest;
import com.lithan.mow.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    public Donation saveDonation(DonationRequest donationRequest) {
        Donation donation = new Donation();

        donation.setPayerName(donationRequest.getPayerName());
        donation.setEmail(donationRequest.getEmail());
        donation.setSource(donationRequest.getSource());
        donation.setAmount(donationRequest.getAmount());
        donation.setTransactionDate(donationRequest.getTransactionDate());

        donationRepository.save(donation);
        return donation;
    }
}
