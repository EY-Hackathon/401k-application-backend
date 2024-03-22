package com.investmentapplication.investmentapplication.controller;

import com.investmentapplication.investmentapplication.entity.PlanContributionsEntity;
import com.investmentapplication.investmentapplication.services.PlanContributionsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

@RestController
public class PlanContributionsController implements PlanContributionsOperations {

    @Autowired
    PlanContributionsServices planContributionsServices;

    public ResponseEntity<Object> getPlanContribution(String email) {
        PlanContributionsEntity planContribution = planContributionsServices.getPlanContribution(email);
        return new ResponseEntity<>(planContribution, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updatePlanContribution(String email, PlanContributionsEntity planContribution) {

        double sum = 0.0;
        Class<?> clazz = planContribution.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(planContribution);
                if (value instanceof Number && !field.getName().equals("email")) {
                    sum += ((Number) value).doubleValue();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if(sum == 100){
            String response = planContributionsServices.updatePlanContribution(email, planContribution);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>("The investments do not sum up to 100%", HttpStatus.BAD_REQUEST);
    }
}


