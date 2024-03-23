package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.entity.EmployerMatchEntity;
import com.investmentapplication.investmentapplication.services.DashboardServices;
import com.investmentapplication.investmentapplication.services.EmployerMatchServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashboardAPIController implements DashboardAPIOperations {

    @Autowired
    DashboardServices dashboardServices;

    @Autowired
    EmployerMatchServices employerMatchServices;

    @Override
    public ResponseEntity<Object> getTotalBalance(String email) {
        double response = dashboardServices.getTotalBalance(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getTotalContribution(String email) {
        double totalContribution = dashboardServices.getTotalContribution(email);
        return new ResponseEntity<>(totalContribution, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getYTDBalance(String email) {
        double ytdBalance = dashboardServices.getYTDContribution(email);
        return new ResponseEntity<>(ytdBalance, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getTotalEarnings(String email) {
        double totalEarnings = dashboardServices.getTotalEarnings(email);
        return new ResponseEntity<>(totalEarnings, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getEmployerMatchValue(String email) {
        double employerMatchValue = employerMatchServices.GetEmployerMatchValue(email);
        return new ResponseEntity<>(employerMatchValue, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getEmployerMatchDetails(String email) {
        List<EmployerMatchEntity> response = employerMatchServices.GetEmployerMatchDetails(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateEmployer(List<EmployerMatchEntity> employerMatchDetails) {
        String response = String.valueOf(employerMatchServices.UpdateEmployerMatchDetails(employerMatchDetails));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
