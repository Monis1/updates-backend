package com.meezotech.updatesbackend.controllers.v1;

import com.meezotech.updatesbackend.api.v1.model.StatsDTO;
import com.meezotech.updatesbackend.services.StatsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(StatsController.BASE_URL)
public class StatsController {

    public static final String BASE_URL = "/api/v1/stats";

    private StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/user/admin")
    @ResponseStatus(HttpStatus.OK)
    public StatsDTO getUserStats(@RequestParam("userId") Long userId) {
        return statsService.getUserStats(userId);
    }

    @GetMapping("/group/overall/admin")
    @ResponseStatus(HttpStatus.OK)
    public StatsDTO getOverAllGroupStats(@RequestParam("groupId") Long groupId) {
        return statsService.getOverAllGroupStats(groupId);
    }

    @GetMapping("/group/monthly/admin")
    @ResponseStatus(HttpStatus.OK)
    public StatsDTO getMonthlyGroupStats(@RequestParam("groupId") Long groupId) {
        return statsService.getMonthlyGroupStats(groupId);
    }

    @GetMapping("/overall/admin")
    @ResponseStatus(HttpStatus.OK)
    public StatsDTO getOverAllStats() {
        return statsService.getOverAllStats();
    }

    @GetMapping("/monthly/admin")
    @ResponseStatus(HttpStatus.OK)
    public StatsDTO getMonthlyStats() {
        return statsService.getMonthlyStats();
    }

}
