package io.zuolizhu.sportswatchapp.DataLoader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.zuolizhu.sportswatchapp.Models.Team;
import io.zuolizhu.sportswatchapp.Repositories.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Base64;

@Component
public class TeamLoader implements CommandLineRunner {
    private TeamRepository teamRepository;

    public TeamLoader(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Preload API into database
        System.out.println("Loading all teams data, please wait ... ");
        loadTeamFromAPI();
        System.out.println("Team info loaded!");
    }

    private void loadTeamFromAPI() {
        //API end point
        String url ="https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/overall_team_standings.json";
        // API access config
        String encoding = Base64.getEncoder().encodeToString("e44e8121-b4bd-4e4c-bb1e-71561c:rootpass".getBytes());

        // Configure HTTP header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + encoding);
        HttpEntity<String> request = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String str = response.getBody();
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode root = mapper.readTree(str);
            JsonNode unwrappedRoot = root.get("overallteamstandings").get("teamstandingsentry");
            if (unwrappedRoot.isArray()) {
                unwrappedRoot.forEach(teamDetails -> {
                    JsonNode teamNode = teamDetails.get("team");
                    Team team = new Team();
                    team.setTeamID(Integer.parseInt(teamNode.get("ID").asText()));
                    team.setTeamName(teamNode.get("Name").asText());
                    team.setCity(teamNode.get("City").asText());
                    team.setAbbreviation(teamNode.get("Abbreviation").asText());
                    teamRepository.save(team);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
