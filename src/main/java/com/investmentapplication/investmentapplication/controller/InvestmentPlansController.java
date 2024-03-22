package com.investmentapplication.investmentapplication.controller;


import com.investmentapplication.investmentapplication.dto.InvestmentPlanDTO;
import com.investmentapplication.investmentapplication.entity.InvestmentPlansEntity;
import com.investmentapplication.investmentapplication.repository.InvestmentPlanRepository;
import com.investmentapplication.investmentapplication.services.InvestmentPlanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")

public class InvestmentPlansController implements InvestmentPlanOperations {

    @Autowired
    private InvestmentPlanRepository investmentPlanRepository;

    @Autowired
    private InvestmentPlanServices investmentPlanServices;

    public ResponseEntity<String> insertInvestmentPlans(@RequestBody List<InvestmentPlanDTO> investmentPlanDTO) {

        String insertInvestmentPlans = investmentPlanServices.insertInvestmentPlans(investmentPlanDTO);
        return ResponseEntity.ok("Investment plans inserted successfully");
    }

    public ResponseEntity<Object> getAllPlans() {

        List<InvestmentPlansEntity> investmentPlansEntity = investmentPlanServices.getAllPlans();

        return new ResponseEntity<Object>(investmentPlansEntity, HttpStatus.OK);


    }


}
