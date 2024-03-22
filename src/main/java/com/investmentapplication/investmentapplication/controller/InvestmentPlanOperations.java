package com.investmentapplication.investmentapplication.controller;


import com.investmentapplication.investmentapplication.dto.InvestmentPlanDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")

public interface InvestmentPlanOperations {

    @PostMapping("/investmentPlans")
    public ResponseEntity<String> insertInvestmentPlans(@RequestBody List<InvestmentPlanDTO> investmentPlanDTO);

    @GetMapping("/investmentPlans")
    ResponseEntity<Object> getAllPlans();
}
