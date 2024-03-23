package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.entity.EmployerMatchEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/employerMatchValue")
    ResponseEntity<Object> getEmployerMatchValue(@RequestParam String email);

    @GetMapping("/employerMatchDetails")
    ResponseEntity<Object> getEmployerMatchDetails(@RequestParam String email);

    @PostMapping("/employerMatchDetails")
    ResponseEntity<Object> updateEmployer(@RequestBody List<EmployerMatchEntity> employerMatchDetails);
}
