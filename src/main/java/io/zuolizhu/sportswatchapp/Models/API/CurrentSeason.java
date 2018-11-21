package io.zuolizhu.sportswatchapp.Models.API;

public class CurrentSeason {
    private String lastUpdatedOn;
    private Season season;

    public CurrentSeason() {
    }

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "CurrentSeason{" +
                "lastUpdatedOn='" + lastUpdatedOn + '\'' +
                ", season=" + season +
                '}';
    }
}
