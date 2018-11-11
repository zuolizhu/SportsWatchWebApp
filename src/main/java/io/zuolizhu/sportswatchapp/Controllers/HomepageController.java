package io.zuolizhu.sportswatchapp.Controllers;

import io.zuolizhu.sportswatchapp.Models.Team;
import io.zuolizhu.sportswatchapp.Models.User;
import io.zuolizhu.sportswatchapp.Services.TeamService;
import io.zuolizhu.sportswatchapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomepageController {
    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @GetMapping("/")
    public String homepage(Model model) {

        // Find user by userID
        User user = userService.findByUserID(1L);
        model.addAttribute("user", user);

        // Get user's favorite teams id list
        List<Team> userFavoriteTeams = new ArrayList<>();
        ArrayList<Long> teamIDs = user.getFavoriteTeams();

        // Prevent empty list cause crash
        // There is no team with teamID 0L
        if (!(teamIDs.get(0) == 0L)) {
            for (Long i : teamIDs) {
                userFavoriteTeams.add(teamService.findByTeamID(i));
            }
        }

        model.addAttribute("teams", userFavoriteTeams);
        return "homepage";
    }
}
