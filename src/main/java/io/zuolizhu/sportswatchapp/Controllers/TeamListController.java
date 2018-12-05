package io.zuolizhu.sportswatchapp.Controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.zuolizhu.sportswatchapp.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

/**
 * Code below referenced from https://github.com/ninadpchaudhari/spring-boot-consume-rest-endpoint
 * Author of the code below is @ninadpchaudhari
 */

@Controller
public class TeamListController {
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/allteams")
    public ModelAndView getTeams() {
        ModelAndView allTeams = new ModelAndView("allteams");
        allTeams.addObject("name", "Human");

        // API endpoint
        String url = "https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/overall_team_standings.json";

        // API access config
        String encoding = Base64.getEncoder().encodeToString("e44e8121-b4bd-4e4c-bb1e-71561c:rootpass".getBytes());

        // Configure HTTP header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + encoding);
        HttpEntity<String> request = new HttpEntity<>(headers);

        // Calling the API and map the response json
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TeamStanding> response = restTemplate.exchange(url, HttpMethod.GET, request, TeamStanding.class);
        TeamStanding teamStanding = response.getBody();

        // Send the mapped teamStanding object to view
        allTeams.addObject("teamStandingEntries", teamStanding.getOverallteamstandings().getTeamstandingsentries());
        return allTeams;
    }

    /**
     * Code below referenced from https://github.com/ninadpchaudhari/spring-boot-consume-rest-endpoint
     * Author of the code below is @ninadpchaudhari
     */
    @GetMapping("/team")
    public ModelAndView getTeamInfo(@RequestParam("id") String teamID) {
        ModelAndView teamInfo = new ModelAndView("teamInfo");
        ArrayList<HashMap<String, String>> gameDetails = fetchGameDetails(teamID);




        teamInfo.addObject("gameDetails", gameDetails);
        teamInfo.addObject("teamDetail", teamRepository.findTeamByTeamID(Integer.parseInt(teamID)));
        return teamInfo;
    }

    private ArrayList<HashMap<String, String>> fetchGameDetails(@RequestParam("id") String teamID) {

        ArrayList<HashMap<String, String>> gameDetails = new ArrayList<>();
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
            JsonNode gamelogs = root.get("teamgamelogs").get("gamelogs");
            if (gamelogs.isArray()) {
                gamelogs.forEach(gamelog -> {
                    JsonNode game = gamelog.get("game");
                    HashMap<String, String> gameDetail = new HashMap<>();
                    gameDetail.put("id", game.get("id").asText());
                    gameDetail.put("date", game.get("date").asText());
                    gameDetail.put("time", game.get("time").asText());
                    gameDetail.put("awayTeam", game.get("awayTeam").get("Abbreviation").asText());
                    gameDetails.add(gameDetail);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameDetails;
    }
}
