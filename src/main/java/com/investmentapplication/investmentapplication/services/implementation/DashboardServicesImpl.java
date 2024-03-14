package com.investmentapplication.investmentapplication.services.implementation;

import com.investmentapplication.investmentapplication.entity.DashboardEntity;
import com.investmentapplication.investmentapplication.repository.DashboardRepository;
import com.investmentapplication.investmentapplication.services.DashboardServices;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


public class DashboardServicesImpl implements DashboardServices {

    @Autowired
    DashboardRepository dashboardRepository;
    public List<DashboardEntity> getTotalBalance(String userId) {
        return DashboardRepository.findTotalbalance(userId);
    }
}
