package io.zuolizhu.sportswatchapp.Controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        // Save the selected teams into user's database
        if (session.getAttribute("userEmail") != null) {
            String accessEmail = session.getAttribute("userEmail").toString();
            if (userRepository.findByUserEmail(accessEmail).isPresent()) {
                User user = userRepository.findByUserEmail(accessEmail).get();
                user.setFavoriteTeams(selectedTeams);
                userRepository.save(user);
                return new ModelAndView("redirect:");
            }
        }

        String errorMessage = "Invalid POST request, please login before choosing teams!";
        model.addAttribute("errorMessage", errorMessage);
        return new ModelAndView("error");
    }
}
