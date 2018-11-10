package io.zuolizhu.sportswatchapp.Controllers;

import io.zuolizhu.sportswatchapp.Models.Team;
import io.zuolizhu.sportswatchapp.Repositories.TeamRepository;
import io.zuolizhu.sportswatchapp.Services.TeamService;
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
    private TeamRepository teamRepository;

    @GetMapping("/selectteams")
    public String teamsSelection(Model model) {
        List<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);
        return "selectteams";
    }

    @PostMapping("/selectteams")
    public String selectteamsFormHandle(@RequestParam String formData) {
        // clean existing record
//        teamRepository.deleteAll();

        // Handle returned value from form
        String selectedteamsID[] = formData.split(",");
        List<Team> selectedTeams = new ArrayList<>();

        for (String s: selectedteamsID) {
            Long teamID = Long.parseLong(s);
            selectedTeams.add(teamService.findById(teamID));
        }

        for (Team t : selectedTeams) {
            teamRepository.save(t);
        }
        System.out.println(selectedTeams.toString());
        return "redirect:";
    }
}
