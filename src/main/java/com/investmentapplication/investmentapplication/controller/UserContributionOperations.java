package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.dto.UserContributionUpdateDTO;
import com.investmentapplication.investmentapplication.entity.UserContributionsEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api")

public interface UserContributionOperations {

    @GetMapping("/userContribution")
    ResponseEntity<Object> getUserContribution(@RequestParam String email);

    @PostMapping("/userContribution")
    ResponseEntity<Object> updateUserContribution(@RequestParam String email, @RequestBody List<UserContributionUpdateDTO> userContribution);
}
