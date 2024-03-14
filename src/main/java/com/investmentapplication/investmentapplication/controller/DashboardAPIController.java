package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.entity.DashboardEntity;
import com.investmentapplication.investmentapplication.services.DashboardServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashboardAPIController implements DashboardAPIOperations {

    public ResponseEntity<Object> getTotalBalance(String userId){
        List<DashboardEntity> response = DashboardServices.getTotalBalance(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
