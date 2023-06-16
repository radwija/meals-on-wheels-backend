package com.lithan.mow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// This controller is for ensuring this backend works
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }
}
