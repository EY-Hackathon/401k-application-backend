package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.repository.UserAccountsRepository;
import com.investmentapplication.investmentapplication.services.DashboardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardAPIController implements DashboardAPIOperations {
    @Autowired
    UserAccountsRepository dashboardRepository;

    @Autowired
    DashboardServices dashboardServices;

    public ResponseEntity<Object> getTotalBalance(String email){
        double response = dashboardServices.getTotalBalance(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Object> getTotalContribution(String email){
        double totalContribution = dashboardServices.getTotalContribution(email);
        return new ResponseEntity<>(totalContribution, HttpStatus.OK);
    }

    public ResponseEntity<Object> getYTDBalance(String email){
        double ytdBalance = dashboardServices.getYTDContribution(email);
        return new ResponseEntity<>(ytdBalance, HttpStatus.OK);
    }

    public ResponseEntity<Object> getTotalEarnings(String email){
        double totalEarnings = dashboardServices.getTotalEarnings(email);
        return new ResponseEntity<>(totalEarnings, HttpStatus.OK);
    }
}
