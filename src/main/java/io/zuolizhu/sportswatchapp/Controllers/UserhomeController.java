package io.zuolizhu.sportswatchapp.Controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.zuolizhu.sportswatchapp.Models.Team;
import io.zuolizhu.sportswatchapp.Models.User;
import io.zuolizhu.sportswatchapp.Repositories.TeamRepository;
import io.zuolizhu.sportswatchapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

@Controller
public class UserhomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/userhome")
    public ModelAndView userHome(HttpSession session, Model model) {
        if (session.getAttribute("userEmail") != null) {
            String accessEmail = session.getAttribute("userEmail").toString();
            if (userRepository.findByUserEmail(accessEmail).isPresent()) {
                User currentUser = userRepository.findByUserEmail(accessEmail).get();
                ModelAndView userHome = new ModelAndView("userhome");
                userHome.addObject("userInfo", currentUser);
                if (getUserFavoriteTeams(currentUser.getFavoriteTeams()) != null) {
                    if (!getUserFavoriteTeams(currentUser.getFavoriteTeams()).isEmpty()) {
                        ArrayList<String> messages = updateMessages(getUserFavoriteTeams(currentUser.getFavoriteTeams()));
                        userHome.addObject("messages", messages);
                        userHome.addObject("teamsInfo", getUserFavoriteTeams(currentUser.getFavoriteTeams()));
                    }
                }
                return userHome;
            }
        }
        String errorMessage = "Invalid access, please login!";
        model.addAttribute("errorMessage", errorMessage);
        return new ModelAndView("error");
    }

    private ArrayList<Team> getUserFavoriteTeams(ArrayList<Integer> favoriteTeams) {
        ArrayList<Team> teams = new ArrayList<>();
        if (!(favoriteTeams == null)) {
            for (Integer i : favoriteTeams) {
                teams.add(teamRepository.findTeamByTeamID(i));
            }
            return teams;
        }
       return teams;
    }

    private ArrayList<String> updateMessages(ArrayList<Team> teams) {
        ArrayList<String> messages = new ArrayList<>();
        if (teams != null) {
            if (!teams.isEmpty()) {
                for (Team t : teams) {
                    messages.add(updateTeamWinsAndLosses(fetchGameDetails(t.getTeamID().toString()), t.getTeamID()));
                }
            }
        }
        return messages;
    }

    // Update team info in team repository
    private String updateTeamWinsAndLosses(ArrayList<HashMap<String, String>> gameDetails, int teamID) {
        int winsCounter = 0;
        int lossesCounter = 0;
        for (HashMap<String, String> entry: gameDetails) {
            for (String key : entry.keySet()) {
                if ((key.compareTo("wins") == 0) && entry.get(key).matches("1")) {
                    winsCounter++;
                } else if((key.compareTo("losses") == 0) && entry.get(key).matches("1")) {
                    lossesCounter++;
                }
            }
        }
        Team currentTeam = teamRepository.findTeamByTeamID(teamID);
        if ((currentTeam.getWins() == winsCounter) && (currentTeam.getLosses() == lossesCounter)) {
            return null;
        } else {
            currentTeam.setWins(winsCounter);
            currentTeam.setWins(lossesCounter);
            teamRepository.save(currentTeam);
            return currentTeam.getAbbreviation() + " has new update! Wins: " + winsCounter + " Losses: " + lossesCounter;
        }
    }

    private ArrayList<HashMap<String, String>> fetchGameDetails(String teamID) {
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
                gamelogs.forEach(gameLog -> {
                    JsonNode game = gameLog.get("game");
                    HashMap<String, String> gameDetail = new HashMap<>();
                    gameDetail.put("id", game.get("id").asText());
                    gameDetail.put("date", game.get("date").asText());
                    gameDetail.put("time", game.get("time").asText());
                    gameDetail.put("awayTeam", game.get("awayTeam").get("Abbreviation").asText());
                    gameDetail.put("wins", gameLog.get("stats").get("Wins").get("#text").asText());
                    gameDetail.put("losses", gameLog.get("stats").get("Losses").get("#text").asText());
                    gameDetails.add(gameDetail);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameDetails;
    }
}
