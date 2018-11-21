package io.zuolizhu.sportswatchapp.Models.API.CurrentSeason;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SupportedTeamStats {
    private ArrayList<TeamStat> teamStat;

    public SupportedTeamStats() {
    }

    public ArrayList<TeamStat> getTeamStat() {
        return teamStat;
    }

    public void setTeamStat(ArrayList<TeamStat> teamStat) {
        this.teamStat = teamStat;
    }

    @Override
    public String toString() {
        return "SupportedTeamStats{" +
                "teamStat=" + teamStat +
                '}';
    }
}
