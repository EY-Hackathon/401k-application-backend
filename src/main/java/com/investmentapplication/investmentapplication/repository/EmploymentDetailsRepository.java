package com.investmentapplication.investmentapplication.repository;
import com.investmentapplication.investmentapplication.entity.EmploymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentDetailsRepository extends JpaRepository<EmploymentDetails, Long> {
        EmploymentDetails findByEmail(String email);

    }


