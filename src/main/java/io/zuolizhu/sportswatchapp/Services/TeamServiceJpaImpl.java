package io.zuolizhu.sportswatchapp.Services;

import io.zuolizhu.sportswatchapp.Models.Team;
import io.zuolizhu.sportswatchapp.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TeamServiceJpaImpl implements TeamService{
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Team> findAll() {
        return this.teamRepository.findAll();
    }

    @Override
    public Team findByTeamID(Long teamID) {
        return this.teamRepository.findAll().stream()
                .filter(p -> Objects.equals(p.getTeamID(), teamID))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void create(Team team) {
        this.teamRepository.save(team);
    }

    @Override
    public void deleteById(Long teamID) {
        this.teamRepository.deleteById(teamID);
    }
}
