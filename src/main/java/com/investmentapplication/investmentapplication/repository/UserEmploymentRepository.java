package com.investmentapplication.investmentapplication.repository;

import com.investmentapplication.investmentapplication.entity.UserContributionsEntity;
import com.investmentapplication.investmentapplication.entity.UserEmploymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEmploymentRepository extends JpaRepository<UserEmploymentEntity, Long> {

    UserEmploymentEntity findByEmail(String email);
}
