package com.lithan.mow.controller;

import com.lithan.mow.exception.UserNotActiveException;
import com.lithan.mow.payload.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserNotActiveException.class)
    public
    @ResponseBody
    MessageResponse handleException(UserNotActiveException ex) {
        return new MessageResponse(ex.getMessage());
    }
}
