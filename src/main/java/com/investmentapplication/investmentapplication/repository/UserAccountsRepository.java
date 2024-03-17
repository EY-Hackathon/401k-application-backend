package com.investmentapplication.investmentapplication.repository;

import com.investmentapplication.investmentapplication.entity.UserAccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountsRepository extends JpaRepository<UserAccountsEntity, Long> {

    List<UserAccountsEntity> findByEmail(String email);
}
