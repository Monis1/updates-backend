package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.model.StatsDTO;

public interface StatsService {

    StatsDTO getUserStats(Long userId);

    StatsDTO getGroupStats(Long groupId);

    StatsDTO getOverAllStats();

    StatsDTO getMonthlyStats();
}
