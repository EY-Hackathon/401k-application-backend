package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.entity.UserContributionsEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserContributionOperations {

    @GetMapping("/userContribution")
    ResponseEntity<Object> getUserContribution(@RequestParam String email);

    @PostMapping("/userContribution")
    ResponseEntity<Object> updateUserContribution(@RequestParam String email, @RequestBody List<UserContributionsEntity> userContribution);


}
