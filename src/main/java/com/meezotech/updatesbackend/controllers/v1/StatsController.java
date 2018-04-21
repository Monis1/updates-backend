package com.meezotech.updatesbackend.controllers.v1;

import com.meezotech.updatesbackend.api.v1.model.UserStatsDTO;
import com.meezotech.updatesbackend.services.UserStatsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(StatsController.BASE_URL)
public class StatsController {

    public static final String BASE_URL = "/api/v1/stats";

    private UserStatsService userStatsService;

    public StatsController(UserStatsService userStatsService) {
        this.userStatsService = userStatsService;
    }

    @GetMapping("/user/admin")
    @ResponseStatus(HttpStatus.OK)
    public UserStatsDTO getUserStats(@RequestParam("userId") Long userId) {
        return userStatsService.getUserStats(userId);
    }

}
