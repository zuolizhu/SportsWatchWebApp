package io.zuolizhu.sportswatchapp.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.zuolizhu.sportswatchapp.Models.API.CurrentSeason.Season;
import io.zuolizhu.sportswatchapp.Models.API.CurrentSeason.SeasonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

@Controller
public class feedingController {
    @GetMapping("/feed")
    public String feeding(Model model) {
        try {
            URL url = new URL("https://api.mysportsfeeds.com/v1.0/pull/nba/current_season.json?fordate=20181018");
            String token = "e44e8121-b4bd-4e4c-bb1e-71561c:rootpass";
            String encoding = Base64.getEncoder().encodeToString(token.getBytes(StandardCharsets.UTF_8));


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = connection.getInputStream();

            // JSON Mapper
            ObjectMapper mapper = new ObjectMapper();
            SeasonResponse seasonResponse = mapper.readValue(content, SeasonResponse.class);
            ArrayList<Season> season = seasonResponse.getCurrentseason().getSeason();
            model.addAttribute("tests", season.get(0).getSupportedPlayerStats().getPlayerStat());


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "feed";
    }
}
