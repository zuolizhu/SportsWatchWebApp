package io.zuolizhu.sportswatchapp.Controllers;

import io.zuolizhu.sportswatchapp.Models.Team;
import io.zuolizhu.sportswatchapp.Models.User;
import io.zuolizhu.sportswatchapp.Repositories.TeamRepository;
import io.zuolizhu.sportswatchapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class UserhomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/userhome")
    public ModelAndView userHome(HttpSession session, Model model) {
        if (session.getAttribute("userEmail") != null) {
            String accessEmail = session.getAttribute("userEmail").toString();
            if (userRepository.findByUserEmail(accessEmail).isPresent()) {
                User currentUser = userRepository.findByUserEmail(accessEmail).get();
                ModelAndView userHome = new ModelAndView("userhome");
                userHome.addObject("userInfo", currentUser);
                if (getUserFavoriteTeams(currentUser.getFavoriteTeams()) != null) {
                    userHome.addObject("teamsInfo", getUserFavoriteTeams(currentUser.getFavoriteTeams()));
                }
                return userHome;
            }
        }
        String errorMessage = "Invalid access, please login!";
        model.addAttribute("errorMessage", errorMessage);
        return new ModelAndView("error");
    }

    private ArrayList<Team> getUserFavoriteTeams(ArrayList<Integer> favoriteTeams) {
        ArrayList<Team> teams = new ArrayList<>();
        if (!(favoriteTeams == null)) {
            for (Integer i : favoriteTeams) {
                teams.add(teamRepository.findTeamByTeamID(i));
            }
            return teams;
        }
       return teams;
    }
}
