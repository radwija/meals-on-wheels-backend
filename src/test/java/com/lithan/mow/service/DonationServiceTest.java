package com.lithan.mow.service;

import com.lithan.mow.entity.Donation;
import com.lithan.mow.payload.request.DonationRequest;
import com.lithan.mow.payload.response.DonationResponse;
import com.lithan.mow.repository.DonationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class DonationServiceTest {

    @InjectMocks
    DonationService donationService;

    @Mock
    DonationRepository donationRepository;

    @Test
    public void saveDonationInfoTest() throws ParseException {
//        DonationRequest donation = new DonationRequest();
//        Date transactionDate = new Date("2023-06-24T13:30:31Z");
//        donation.setPayerName("User Test");
//        donation.setEmail("usertest@email.com");
//        donation.setPaymentSource("paypal");
//        donation.setAmount(BigDecimal.valueOf(10));
//        donation.setTransactionDate(transactionDate);
//
//        Mockito.when(donationRepository.save(Mockito.any(Donation.class))).thenReturn(new Donation());
//
//        DonationResponse<?> donationResponse = donationService.saveDonation(donation);
//        Assert.assertEquals("Donation information recorded successfully!", donationResponse.getMessage());

        DonationRequest donation = new DonationRequest();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date transactionDate = dateFormat.parse("2023-06-24T13:30:31Z");
        donation.setPayerName("User Test");
        donation.setEmail("user@test.com");
        donation.setPaymentSource("paypal");
        donation.setAmount(BigDecimal.valueOf(0.01));
        donation.setTransactionDate(transactionDate);

//        Mockito.when(donationRepository.save(Mockito.any(Donation.class))).thenReturn(new Donation());

        DonationResponse<?> donationResponse = donationService.saveDonation(donation);
        Assert.assertEquals("Donation information successfully recorded!", donationResponse.getMessage());
    }
}
