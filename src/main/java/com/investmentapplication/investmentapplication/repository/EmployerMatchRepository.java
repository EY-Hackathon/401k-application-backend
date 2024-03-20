package com.investmentapplication.investmentapplication.repository;

import com.investmentapplication.investmentapplication.entity.EmployerMatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerMatchRepository extends JpaRepository<EmployerMatchEntity, Long> {

    List<EmployerMatchEntity> findByEmployerName(String employerName);
}
