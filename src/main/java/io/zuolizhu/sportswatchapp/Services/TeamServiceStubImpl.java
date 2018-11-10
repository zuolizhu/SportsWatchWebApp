package io.zuolizhu.sportswatchapp.Services;

import io.zuolizhu.sportswatchapp.Models.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TeamServiceStubImpl implements TeamService {
    private List<Team> teams = new ArrayList<>();

    public TeamServiceStubImpl() {
        teams.add(new Team(1L, "Washington Wizards", "WW"));
        teams.add(new Team(3L, "Los Angeles Clippers", "LAC"));
        teams.add(new Team(2L, "Miami Heat", "MH"));
    }

    @Override
    public List<Team> findAll() {
        return this.teams;
    }

    @Override
    public Team findById(Long teamID) {
        return this.teams.stream()
                .filter(p -> Objects.equals(p.getTeamID(), teamID))
                .findFirst().orElse(null);
    }

    @Override
    public Team create(Team team) {
        team.setTeamID(this.teams.stream().mapToLong(
                p -> p.getTeamID()).max().getAsLong() + 1);
        this.teams.add(team);
        return team;
    }

    @Override
    public void deleteById(Long teamID) {
        for (int i = 0; i < this.teams.size(); i++) {
            if (Objects.equals(this.teams.get(i).getTeamID(), teamID)) {
                this.teams.remove(i);
                return;
            }
        }
        throw new RuntimeException("TeamID not found: " + teamID);
    }
}
