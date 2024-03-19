package com.investmentapplication.investmentapplication.controller;


import com.investmentapplication.investmentapplication.entity.InvestmentPlansEntity;
import com.investmentapplication.investmentapplication.services.InvestmentPlanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvestmentPlansController implements InvestmentPlanOperations {

    @Autowired
    private InvestmentPlanServices investmentPlanServices;

    public ResponseEntity<Object> getAllPlans(){

        List<InvestmentPlansEntity> investmentPlansEntity = investmentPlanServices.getAllPlans();

        return new ResponseEntity<Object>(investmentPlansEntity, HttpStatus.OK);


    }





}
