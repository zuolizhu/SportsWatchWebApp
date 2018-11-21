package io.zuolizhu.sportswatchapp.Models.API.CurrentSeason;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SupportedPlayerStats {
    private ArrayList<PlayerStat> playerStat;

    public SupportedPlayerStats() {
    }

    public ArrayList<PlayerStat> getPlayerStat() {
        return playerStat;
    }

    public void setPlayerStat(ArrayList<PlayerStat> playerStat) {
        this.playerStat = playerStat;
    }

    @Override
    public String toString() {
        return "SupportedPlayerStats{" +
                "playerStat=" + playerStat +
                '}';
    }
}
