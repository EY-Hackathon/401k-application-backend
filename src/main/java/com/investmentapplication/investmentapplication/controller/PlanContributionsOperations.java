package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.entity.PlanContributionsEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
public interface PlanContributionsOperations {

    @GetMapping("/planContributions")
    ResponseEntity<Object> getPlanContribution(@RequestParam String email);

    @PostMapping("/planContributions")
    ResponseEntity<Object> updatePlanContribution(@RequestParam String email, @RequestBody PlanContributionsEntity planContribution);
}