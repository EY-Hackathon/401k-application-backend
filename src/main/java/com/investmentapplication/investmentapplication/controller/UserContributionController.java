package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.dto.UserContributionUpdateDTO;
import com.investmentapplication.investmentapplication.entity.UserContributionsEntity;
import com.investmentapplication.investmentapplication.services.UserContributionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class UserContributionController implements UserContributionOperations{

    @Autowired
    UserContributionServices userContributionServices;

    public ResponseEntity<Object> getUserContribution (String email){
        List<UserContributionsEntity> userContribution = userContributionServices.getUserContribution(email);
        return new ResponseEntity<>(userContribution, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateUserContribution(String email, List<UserContributionUpdateDTO> userContribution){
        String response = userContributionServices.updateUserContribution(email, userContribution);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
