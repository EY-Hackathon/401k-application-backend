package com.investmentapplication.investmentapplication.services;

import com.investmentapplication.investmentapplication.entity.EmployerMatchEntity;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public interface EmployerMatchServices {

    double GetEmployerMatchValue (String email);

    List<EmployerMatchEntity> GetEmployerMatchDetails(String email);

    AtomicReference<String> UpdateEmployerMatchDetails(List<EmployerMatchEntity> employerMatchDetails);
}
