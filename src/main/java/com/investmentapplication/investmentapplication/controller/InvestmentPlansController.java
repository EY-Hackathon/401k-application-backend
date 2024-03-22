package com.investmentapplication.investmentapplication.controller;


import com.investmentapplication.investmentapplication.dto.InvestmentPlanDTO;
import com.investmentapplication.investmentapplication.entity.InvestmentPlansEntity;
import com.investmentapplication.investmentapplication.repository.InvestmentPlanRepository;
import com.investmentapplication.investmentapplication.services.InvestmentPlanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api")
public class InvestmentPlansController implements InvestmentPlanOperations {

    @Autowired
    private InvestmentPlanRepository investmentPlanRepository;

    @Autowired
    private InvestmentPlanServices investmentPlanServices;

    // This method overrides the insertInvestmentPlans method defined in the InvestmentPlanOperations interface
    @Override
    public ResponseEntity<String> insertInvestmentPlans(@RequestBody List<InvestmentPlanDTO> investmentPlanDTO) {
        // Call the service to insert investment plans based on the provided DTOs
        String insertInvestmentPlans = investmentPlanServices.insertInvestmentPlans(investmentPlanDTO);
        // Return a success response
        return ResponseEntity.ok("Investment plans inserted successfully");
    }

    // This method overrides the getAllPlans method defined in the InvestmentPlanOperations interface
    @Override
    public ResponseEntity<Object> getAllPlans() {
        // Retrieve all investment plans from the service
        List<InvestmentPlansEntity> investmentPlansEntity = investmentPlanServices.getAllPlans();
        // Return a response containing the retrieved investment plans
        return new ResponseEntity<Object>(investmentPlansEntity, HttpStatus.OK);
    }
}