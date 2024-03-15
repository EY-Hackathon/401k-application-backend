package com.investmentapplication.investmentapplication.repository;

import com.investmentapplication.investmentapplication.entity.DashboardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRepository extends JpaRepository<DashboardEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT U.TOTALBALANCE FROM USERDETAILS U WHERE USERID: userId")
    List<DashboardEntity> getUserData(String userId);
}
