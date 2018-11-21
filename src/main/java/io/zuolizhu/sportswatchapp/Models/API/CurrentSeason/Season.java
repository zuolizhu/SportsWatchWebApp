package io.zuolizhu.sportswatchapp.Models.API.CurrentSeason;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Season {
    private Details details;
    private SupportedTeamStats supportedTeamStats;
    private SupportedPlayerStats supportedPlayerStats;

    public Season() {
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public SupportedTeamStats getSupportedTeamStats() {
        return supportedTeamStats;
    }

    public void setSupportedTeamStats(SupportedTeamStats supportedTeamStats) {
        this.supportedTeamStats = supportedTeamStats;
    }

    public SupportedPlayerStats getSupportedPlayerStats() {
        return supportedPlayerStats;
    }

    public void setSupportedPlayerStats(SupportedPlayerStats supportedPlayerStats) {
        this.supportedPlayerStats = supportedPlayerStats;
    }

    @Override
    public String toString() {
        return "Season{" +
                "details=" + details +
                ", supportedTeamStats=" + supportedTeamStats +
                ", supportedPlayerStats=" + supportedPlayerStats +
                '}';
    }
}
