package io.zuolizhu.sportswatchapp.Controllers;

import io.zuolizhu.sportswatchapp.Services.TeamService;
import io.zuolizhu.sportswatchapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomepageController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/")
    public String homepage(Model model, HttpSession session) {

        // Find user by userID
//        User user = userService.findByUserID(1L);
//        model.addAttribute("user", user);

        // Get user's favorite teams id list
//        List<Team> userFavoriteTeams = userService.findByUserID(1L).getFavoriteTeams();
//        model.addAttribute("teams", userFavoriteTeams);
//        System.out.println(session.getAttribute("userID"));
        return "homepage";
    }
}
