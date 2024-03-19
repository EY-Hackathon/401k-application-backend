package com.investmentapplication.investmentapplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RequestMapping("/api")

public interface DashboardAPIOperations {

    @GetMapping("/totalBalance")
    ResponseEntity<Object> getTotalBalance(@RequestParam String email);

    @GetMapping("/totalContribution")
    ResponseEntity<Object> getTotalContribution(@RequestParam String email);

    @GetMapping("/YTDBalance")
    ResponseEntity<Object> getYTDBalance(@RequestParam String email);

    @GetMapping("/totalEarnings")
    ResponseEntity<Object> getTotalEarnings(@RequestParam String email);
}
