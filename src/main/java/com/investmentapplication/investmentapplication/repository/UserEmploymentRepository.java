package com.investmentapplication.investmentapplication.repository;

import com.investmentapplication.investmentapplication.entity.UserEmploymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface UserEmploymentRepository extends JpaRepository<UserEmploymentEntity, Long> {

    UserEmploymentEntity findByEmail(String email);
}
