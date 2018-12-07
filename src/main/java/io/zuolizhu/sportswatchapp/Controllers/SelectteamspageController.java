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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

@Controller
public class SelectteamspageController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/selectteams")
    public ModelAndView allTeams(HttpSession session) {
        if (session.getAttribute("userEmail") != null) {
            String accessEmail = session.getAttribute("userEmail").toString();
            if (userRepository.findByUserEmail(accessEmail).isPresent()) {
                ModelAndView allTeams = new ModelAndView("selectteams");
                allTeams.addObject("teams", teamRepository.findAll());
                return allTeams;
            }
        }
        System.out.println();
        return new ModelAndView("redirect:login");
    }

    @PostMapping("/selectteams")
    public ModelAndView selectteamsFormHandle(@RequestParam String formData, HttpSession session, Model model) {
        // Handle returned value from form
        String dataSplit[] = formData.split(",");
        ArrayList<Integer> selectedTeams = new ArrayList<>();
        for (String s: dataSplit) {
            selectedTeams.add(Integer.parseInt(s));
        }

        // Update selected teams wins and losses
        if (!selectedTeams.isEmpty()) {
            loadSelectedTeamsWinsAndLosses(selectedTeams);
        }

        // Save the selected teams into user's database
        if (session.getAttribute("userEmail") != null) {
            String accessEmail = session.getAttribute("userEmail").toString();
            if (userRepository.findByUserEmail(accessEmail).isPresent()) {
                User user = userRepository.findByUserEmail(accessEmail).get();
                user.setFavoriteTeams(selectedTeams);
                userRepository.save(user);
                return new ModelAndView("redirect:userhome");
            }
        }
        String errorMessage = "Invalid POST request, please login before choosing teams!";
        model.addAttribute("errorMessage", errorMessage);
        return new ModelAndView("error");
    }

    @GetMapping("/addtofavorite")
    public ModelAndView addTeamToFavorite(@RequestParam("id") String teamID, HttpSession session, Model model) {
        if (session.getAttribute("userEmail") != null) {
            String accessEmail = session.getAttribute("userEmail").toString();
            if (userRepository.findByUserEmail(accessEmail).isPresent()) {
                User currentUser = userRepository.findByUserEmail(accessEmail).get();
                if (currentUser.getFavoriteTeams() != null) {
                    ArrayList<Integer> oldFavTeams = currentUser.getFavoriteTeams();
                    loadWinsAndLosses(Integer.parseInt(teamID));
                    oldFavTeams.add(Integer.parseInt(teamID));
                    currentUser.setFavoriteTeams(oldFavTeams);
                    userRepository.save(currentUser);
                } else {
                    ArrayList<Integer> newFavTeams = new ArrayList<>();
                    newFavTeams.add(Integer.parseInt(teamID));
                    currentUser.setFavoriteTeams(newFavTeams);
                    userRepository.save(currentUser);
                }
                return new ModelAndView("redirect:userhome");
            }
        }

        String errorMessage = "Invalid request, please login before adding teams!";
        model.addAttribute("errorMessage", errorMessage);
        return new ModelAndView("error");
    }

    private void loadSelectedTeamsWinsAndLosses(ArrayList<Integer> selectedTeams) {
        for (int i : selectedTeams) {
            loadWinsAndLosses(i);
        }
    }

    private void loadWinsAndLosses(int teamID) {
        Team t = teamRepository.findTeamByTeamID(teamID);
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
