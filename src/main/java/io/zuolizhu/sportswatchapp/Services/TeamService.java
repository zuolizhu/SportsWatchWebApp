package io.zuolizhu.sportswatchapp.Services;

import io.zuolizhu.sportswatchapp.Models.Team;

import java.util.List;

public interface TeamService {
    List<Team> findAll();
    Team findById(Long teamID);
    Team create(Team team);
    void deleteById(Long teamID);
}
