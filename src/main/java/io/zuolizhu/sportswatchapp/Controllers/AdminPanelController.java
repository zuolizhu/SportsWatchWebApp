package io.zuolizhu.sportswatchapp.Controllers;

import io.zuolizhu.sportswatchapp.Models.User;
import io.zuolizhu.sportswatchapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class AdminPanelController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admindash")
    public ModelAndView adminDashboard() {
        ModelAndView allUsers = new ModelAndView("admindash");
        allUsers.addObject("allusers", userRepository.findAll());
        return allUsers;
    }

    @GetMapping("/changeStatus")
    public ModelAndView changeUserStatus(
            @RequestParam("userEmail") String userEmail
    ) {
        if(userRepository.findByUserEmail(userEmail).isPresent()) {
            User user = userRepository.findByUserEmail(userEmail).get();
            user.setBlocked(!(user.isBlocked()));
            userRepository.save(user);
        }
        return new ModelAndView("redirect:admindash");
    }
}
