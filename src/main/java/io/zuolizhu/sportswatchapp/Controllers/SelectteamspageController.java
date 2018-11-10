package io.zuolizhu.sportswatchapp.Controllers;

import io.zuolizhu.sportswatchapp.Models.Team;
import io.zuolizhu.sportswatchapp.Models.User;
import io.zuolizhu.sportswatchapp.Services.TeamService;
import io.zuolizhu.sportswatchapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SelectteamspageController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @GetMapping("/selectteams")
    public String teamsSelection(Model model) {

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
        List<Team> selectedTeams = new ArrayList<>();

        // Save selected teams into a temp list
        for (String s: selectedteamsID) {
            Long teamID = Long.parseLong(s);
            selectedTeams.add(teamService.findByTeamID(teamID));
        }
        System.out.println("Selected :" + selectedTeams.toString());

        // Update selected teams in user repo
        User user = userService.findByUserID(1L);
        userService.updateFavoriteTeams(user, selectedTeams);

        System.out.println(user.toString());

        return "redirect:";
    }
}
