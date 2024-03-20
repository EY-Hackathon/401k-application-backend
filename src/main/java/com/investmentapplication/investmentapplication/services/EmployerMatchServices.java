package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.entity.EmployerMatchEntity;

import java.util.List;

public interface EmployerMatchServices {

    double GetEmployerMatchValue (String email);

    List<EmployerMatchEntity> GetEmployerMatchDetails(String email);
}
