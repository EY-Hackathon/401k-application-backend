package com.investmentapplication.investmentapplication.repository;
import com.investmentapplication.investmentapplication.entity.EmploymentDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentDetailsRepository extends JpaRepository<EmploymentDetailsEntity, Long> {
        EmploymentDetailsEntity findByEmail(String email);

    }


