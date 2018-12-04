package io.zuolizhu.sportswatchapp.Controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.zuolizhu.sportswatchapp.Models.Team;
import io.zuolizhu.sportswatchapp.Repositories.UserRepository;
import io.zuolizhu.sportswatchapp.Services.TeamService;
import io.zuolizhu.sportswatchapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Controller
public class SelectteamspageController {
    @Autowired
    private TeamService teamService;

//    @Autowired
//    private UserService userService;

//    @Autowired
//    private UserRepository userRepository;

    @GetMapping("/selectteams")
    public ModelAndView allTeams() {
        //TODO User login required

        ModelAndView allTeams = new ModelAndView("selectteams");

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

        ArrayList<HashMap<String, String>> teams = new ArrayList<>();

        try {
            JsonNode root = mapper.readTree(str);
            JsonNode unwrappedRoot = root.get("overallteamstandings").get("teamstandingsentry");
            if (unwrappedRoot.isArray()) {
                unwrappedRoot.forEach(teamDetails -> {
                    JsonNode team = teamDetails.get("team");
                    HashMap<String,String> teamDetail = new HashMap<>();
                    teamDetail.put("City",team.get("City").asText());
                    teamDetail.put("Name",team.get("Name").asText());
                    teamDetail.put("teamID",team.get("ID").asText());
                    teams.add(teamDetail);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        allTeams.addObject("teams", teams);
        return allTeams;
    }

    @PostMapping("/selectteams")
    public String selectteamsFormHandle(@RequestParam String formData) {
        // Handle returned value from form
//        String selectedteamsID[] = formData.split(",");
//        List<Team> favoriteTeams = new ArrayList<>();
//        // Save selected teams into a temp list
//        for (String s: selectedteamsID) {
//            Long teamID = Long.parseLong(s);
//            favoriteTeams.add(teamService.findByTeamID(teamID));
//        }
//        // Update selected teams in user repo
//        userService.updateUserFavoriteTeams(1L, favoriteTeams);
      return "redirect:";
    }
}
