package com.investmentapplication.investmentapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectionTest {
    @GetMapping("/test")
    public String connectionTest(){
        return "Connection is working!";
    }


}
