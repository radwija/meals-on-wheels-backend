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
    public void saveDonationInfoMinAmountTest() throws ParseException {
        DonationRequest donationRequest = new DonationRequest();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date transactionDate = dateFormat.parse("2023-06-24T13:30:31Z");

        donationRequest.setPayerName("User Unit Test");
        donationRequest.setEmail("user@unittest.com");
        donationRequest.setPaymentSource("paypal");
        donationRequest.setAmount(BigDecimal.valueOf(5));
        donationRequest.setTransactionDate(transactionDate);

        DonationResponse<?> donationResponse = donationService.saveDonation(donationRequest);
        Assert.assertEquals("Donation information successfully recorded!", donationResponse.getMessage());
    }

    @Test
    public void saveDonationInfoMaxAmountTest() throws ParseException {
        DonationRequest donationRequest = new DonationRequest();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date transactionDate = dateFormat.parse("2023-06-24T13:30:31Z");

        donationRequest.setPayerName("User Unit Test");
        donationRequest.setEmail("user@unittest.com");
        donationRequest.setPaymentSource("paypal");
        donationRequest.setAmount(BigDecimal.valueOf(9999999.99));
        donationRequest.setTransactionDate(transactionDate);

        DonationResponse<?> donationResponse = donationService.saveDonation(donationRequest);
        Assert.assertEquals("Donation information successfully recorded!", donationResponse.getMessage());
    }

    @Test
    public void saveDonationInfoZeroAmountTest() throws ParseException {
        DonationRequest donationRequest = new DonationRequest();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date transactionDate = dateFormat.parse("2023-06-24T13:30:31Z");

        donationRequest.setPayerName("User Unit Test");
        donationRequest.setEmail("user@unittest.com");
        donationRequest.setPaymentSource("paypal");
        donationRequest.setAmount(BigDecimal.valueOf(0));
        donationRequest.setTransactionDate(transactionDate);

        DonationResponse<?> donationResponse = donationService.saveDonation(donationRequest);
        Assert.assertEquals("Amount of money is under minimum of amount ($ 5)", donationResponse.getMessage());
    }

    @Test
    public void saveDonationInfoOverMaxAmountTest() throws ParseException {
        DonationRequest donationRequest = new DonationRequest();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date transactionDate = dateFormat.parse("2023-06-24T13:30:31Z");

        donationRequest.setPayerName("User Unit Test");
        donationRequest.setEmail("user@unittest.com");
        donationRequest.setPaymentSource("paypal");
        donationRequest.setAmount(BigDecimal.valueOf(10000000));
        donationRequest.setTransactionDate(transactionDate);

        DonationResponse<?> donationResponse = donationService.saveDonation(donationRequest);
        Assert.assertEquals("Amount of money is over maximum of amount ($ 9,999,999.99)", donationResponse.getMessage());
    }
}
