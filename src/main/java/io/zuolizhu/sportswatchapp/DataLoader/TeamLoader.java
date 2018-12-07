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
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

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
        System.out.println("All Teams info loaded!");
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

    private void loadWinsAndLosses() {
        for (Team t: teamRepository.findAll()) {
            ArrayList<HashMap<String, String>> winsAndLosses = fetchTeamWinsAndLosses(t.getTeamID().toString());
            int winsCounter= 0;
            int lossesCounter = 0;
            for (HashMap<String, String> entry: winsAndLosses) {
                for (String key : entry.keySet()) {
                    if ((key.compareTo("wins") == 0) && entry.get(key).matches("1")) {
                        winsCounter++;
                    } else if((key.compareTo("losses") == 0) && entry.get(key).matches("1")) {
                        lossesCounter++;
                    }
                }
            }
            t.setWins(winsCounter);
            t.setLosses(lossesCounter);
        }
    }

    private ArrayList<HashMap<String, String>> fetchTeamWinsAndLosses(String teamID) {
        ArrayList<HashMap<String, String>> winsAndLosses = new ArrayList<>();
        // API endpoint
        String url = "https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/team_gamelogs.json?team=" + teamID;
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
            JsonNode gameLogs = root.get("teamgamelogs").get("gamelogs");
            if (gameLogs.isArray()) {
                gameLogs.forEach(gameLog -> {
                    HashMap<String, String> gameDetail = new HashMap<>();
                    gameDetail.put("wins", gameLog.get("stats").get("Wins").get("#text").asText());
                    gameDetail.put("losses", gameLog.get("stats").get("Losses").get("#text").asText());
                    winsAndLosses.add(gameDetail);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(winsAndLosses.toString());
        return winsAndLosses;
    }
}
