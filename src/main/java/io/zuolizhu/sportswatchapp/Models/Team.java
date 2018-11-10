package io.zuolizhu.sportswatchapp.Models;

import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamID;
    private String teamName;
    private String teamNameAbbreviation;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    public Team() {
    }

    public Team(Long teamID, String teamName, String teamNameAbbreviation) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.teamNameAbbreviation = teamNameAbbreviation;
    }

    public Long getTeamID() {
        return teamID;
    }

    public void setTeamID(Long teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamNameAbbreviation() {
        return teamNameAbbreviation;
    }

    public void setTeamNameAbbreviation(String teamNameAbbreviation) {
        this.teamNameAbbreviation = teamNameAbbreviation;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamID=" + teamID +
                ", teamName='" + teamName + '\'' +
                ", teamNameAbbreviation='" + teamNameAbbreviation + '\'' +
                '}';
    }
}
