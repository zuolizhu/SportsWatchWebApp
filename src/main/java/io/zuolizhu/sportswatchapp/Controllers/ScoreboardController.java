package io.zuolizhu.sportswatchapp.Controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

@Controller
public class ScoreboardController {
    @GetMapping("/scoreboard")
    public ModelAndView scoreboard() {
        ArrayList<HashMap<String, String>> scores = fetchScoreboard();
        ModelAndView scoreboard = new ModelAndView("scoreboard");
        scoreboard.addObject("scoreboard", scores);
        System.out.println(scores.toString());
        return scoreboard;
    }

    private ArrayList<HashMap<String, String>> fetchScoreboard() {
        // Fetch current time
        // The date is hardcoded, purchase account required to get real time update
//        LocalDateTime currentTime = LocalDateTime.now();
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//        String currentDate =dateTimeFormatter.format(currentTime);
        String currentDate = "20181112";
        // API endpoint
        String url = "https://api.mysportsfeeds.com/v1.2/pull/nba/2018-2019-regular/scoreboard.json?fordate=" + currentDate;
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
        ArrayList<HashMap<String, String>> gameDetails = new ArrayList<>();
        try {
            JsonNode root = mapper.readTree(str);
            JsonNode gameScores = root.get("scoreboard").get("gameScore");
            if (gameScores.isArray()) {
                gameScores.forEach(gameLog -> {
                    JsonNode game = gameLog.get("game");
                    HashMap<String, String> gameDetail = new HashMap<>();
                    gameDetail.put("ID", game.get("ID").asText());
                    gameDetail.put("date", game.get("date").asText());
                    gameDetail.put("time", game.get("time").asText());
                    gameDetail.put("awayTeam", game.get("awayTeam").get("Abbreviation").asText());
                    gameDetail.put("homeTeam", game.get("homeTeam").get("Abbreviation").asText());
                    gameDetail.put("homeCity", game.get("location").asText());
                    if (gameLog.get("isUnplayed").asBoolean()) {
                        gameDetail.put("status", "not started");
                        gameDetail.put("awayScore", "0");
                        gameDetail.put("homeScore", "0");
                    } else {
                        gameDetail.put("awayScore", gameLog.get("awayScore").asText());
                        gameDetail.put("homeScore", gameLog.get("homeScore").asText());
                        if (gameLog.get("isInProgress").asBoolean()) {
                            gameDetail.put("status", "in progress");
                        } else gameDetail.put("status", "completed");
                    }
                    gameDetails.add(gameDetail);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameDetails;
    }
}
