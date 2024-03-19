package com.investmentapplication.investmentapplication.repository;

import com.investmentapplication.investmentapplication.entity.UserContributionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserContributionsRepository extends JpaRepository<UserContributionsEntity, Long> {

    List<UserContributionsEntity> findByEmail(String email);
}
