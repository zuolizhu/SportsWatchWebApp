package io.zuolizhu.sportswatchapp.Controllers;

import io.zuolizhu.sportswatchapp.Models.APIModel.TeamStanding;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;

@Controller
public class TeamListController {
    @GetMapping("/teams")
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

        // Calling the API
        RestTemplate restTemplate = new RestTemplate();

        // response object mapping
        ResponseEntity<TeamStanding> response = restTemplate.exchange(url, HttpMethod.GET, request, TeamStanding.class);
        TeamStanding teamStanding = response.getBody();

        // debug purpose
        System.out.println(teamStanding.getOverallteamstandings());

        // Send the mapped teamStanding object to view
//        allTeams.addObject("teamStandingEntries", teamStanding.getOverallteamstandings());
        return allTeams;
    }
}
