package io.zuolizhu.sportswatchapp.Controllers;

import io.zuolizhu.sportswatchapp.Models.Team;
import io.zuolizhu.sportswatchapp.Models.User;
import io.zuolizhu.sportswatchapp.Repositories.UserRepository;
import io.zuolizhu.sportswatchapp.Services.TeamService;
import io.zuolizhu.sportswatchapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SelectteamspageController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/selectteams")
    public String teamsSelection(Model model, HttpSession session) {
        if (session.getAttribute("userID") == null) {
            return "redirect:/login";
        }
        // Generate a team list for user to pickup
        teamService.create(new Team(1L, "Washington Wizards", "WW"));
        teamService.create(new Team(2L, "Miami Heat", "MH"));
        teamService.create(new Team(3L, "Los Angeles Clippers", "LAC"));

        List<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);
        return "selectteams";
    }

    @PostMapping("/selectteams")
    public String selectteamsFormHandle(@RequestParam String formData) {
        // Handle returned value from form
        String selectedteamsID[] = formData.split(",");
        List<Team> favoriteTeams = new ArrayList<>();
        // Save selected teams into a temp list
        for (String s: selectedteamsID) {
            Long teamID = Long.parseLong(s);
            favoriteTeams.add(teamService.findByTeamID(teamID));
        }
        // Update selected teams in user repo
        userService.updateUserFavoriteTeams(1L, favoriteTeams);
      return "redirect:";
    }
}
