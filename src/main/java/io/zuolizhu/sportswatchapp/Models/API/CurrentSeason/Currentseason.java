package io.zuolizhu.sportswatchapp.Models.API.CurrentSeason;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currentseason {
    private String lastUpdatedOn;
    private ArrayList<Season> season;

    public Currentseason() {
    }

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public ArrayList<Season> getSeason() {
        return season;
    }

    public void setSeason(ArrayList<Season> season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "Currentseason{" +
                "lastUpdatedOn='" + lastUpdatedOn + '\'' +
                ", season=" + season +
                '}';
    }
}
