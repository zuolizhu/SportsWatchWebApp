package io.zuolizhu.sportswatchapp.Models.API;

public class SeasonResponse {
    private CurrentSeason currentseason;

    public SeasonResponse() {
    }

    public CurrentSeason getCurrentseason() {
        return currentseason;
    }

    public void setCurrentseason(CurrentSeason currentseason) {
        this.currentseason = currentseason;
    }

    @Override
    public String toString() {
        return "SeasonResponse{" +
                "currentseason=" + currentseason +
                '}';
    }
}
