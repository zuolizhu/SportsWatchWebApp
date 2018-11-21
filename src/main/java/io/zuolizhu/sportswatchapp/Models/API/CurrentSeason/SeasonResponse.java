package io.zuolizhu.sportswatchapp.Models.API.CurrentSeason;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SeasonResponse {
    private Currentseason currentseason;

    public SeasonResponse() {
    }

    public Currentseason getCurrentseason() {
        return currentseason;
    }

    public void setCurrentseason(Currentseason currentseason) {
        this.currentseason = currentseason;
    }

    @Override
    public String toString() {
        return "SeasonResponse{" +
                "currentseason=" + currentseason +
                '}';
    }
}
