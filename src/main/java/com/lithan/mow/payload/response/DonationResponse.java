package com.lithan.mow.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DonationResponse<DataTypeRoot> {
    int code = 400;
    String message = "Donation information not recorded!";
    DataTypeRoot result = null;

    public static <DataType> DonationResponse<DataType> ok(DataType result) {
        DonationResponse<DataType> response = new DonationResponse<>();

        response.code = 200;
        response.message = "Donation information recorded successfully!";
        response.result = result;

        return response;
    }

    public static <DataType> DonationResponse<DataType> badRequest(String msg) {
        DonationResponse<DataType> response = new DonationResponse<>();

        response.code = 200;
        response.message = msg;

        return response;
    }
}
