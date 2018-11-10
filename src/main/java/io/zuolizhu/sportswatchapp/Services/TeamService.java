package io.zuolizhu.sportswatchapp.Services;

import io.zuolizhu.sportswatchapp.Models.Team;

import java.util.List;

public interface TeamService {
    List<Team> findAll();
    Team findByTeamID(Long teamID);
    void create(Team team);
    void deleteById(Long teamID);
}
