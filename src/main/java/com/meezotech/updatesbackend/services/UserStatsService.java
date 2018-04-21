package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.model.UserStatsDTO;

public interface UserStatsService {

    UserStatsDTO getUserStats(Long userId);

}
