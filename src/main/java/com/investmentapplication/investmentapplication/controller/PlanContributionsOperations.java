package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.entity.PlanContributionsEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface PlanContributionsOperations {

    @GetMapping("/plancontributions")
    ResponseEntity<Object> getPlanContribution(@RequestParam String email);

    @PostMapping("/plancontributions")
    ResponseEntity<Object> updatePlanContribution(@RequestParam String email, @RequestBody PlanContributionsEntity planContribution);

}
