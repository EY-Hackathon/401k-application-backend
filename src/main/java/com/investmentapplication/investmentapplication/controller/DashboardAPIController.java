package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.entity.DashboardEntity;
import com.investmentapplication.investmentapplication.repository.DashboardRepository;
import com.investmentapplication.investmentapplication.services.DashboardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashboardAPIController implements DashboardAPIOperations {
    @Autowired
    DashboardRepository dashboardRepository;

    @Autowired
    DashboardServices dashboardServices;

    public ResponseEntity<Object> getTotalBalance(String userId){
        long response = dashboardServices.getTotalBalance(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
