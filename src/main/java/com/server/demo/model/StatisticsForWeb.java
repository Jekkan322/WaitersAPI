package com.server.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class StatisticsForWeb {
    private List<Statistics> statistics;
    private List<MissionsOfRestaurant> missions;

    public StatisticsForWeb(){

    }

    public StatisticsForWeb(List<Statistics> statistics, List<MissionsOfRestaurant> missions) {
        this.statistics = statistics;
        this.missions = missions;
    }

    public List<Statistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Statistics> statistics) {
        this.statistics = statistics;
    }

    public List<MissionsOfRestaurant> getMissions() {
        return missions;
    }

    public void setMissions(List<MissionsOfRestaurant> missions) {
        this.missions = missions;
    }
}
