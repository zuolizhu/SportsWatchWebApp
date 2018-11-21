package io.zuolizhu.sportswatchapp.Controllers;

import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Controller
public class feedingController {
    @GetMapping("/feed")
    public String feeding() {
        try {
            URL url = new URL("https://api.mysportsfeeds.com/v1.0/pull/nba/current_season.json?fordate=20181018");
            String token = "e44e8121-b4bd-4e4c-bb1e-71561c:rootpass";
            String encoding = Base64.getEncoder().encodeToString(token.getBytes(StandardCharsets.UTF_8));


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "feed";
    }
}
