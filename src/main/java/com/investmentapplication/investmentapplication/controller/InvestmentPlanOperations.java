package com.investmentapplication.investmentapplication.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

 public interface InvestmentPlanOperations {

     @GetMapping("/investmentplans")
     ResponseEntity<Object> getAllPlans();


}
