package io.zuolizhu.sportswatchapp.Controllers;

import io.zuolizhu.sportswatchapp.Models.User;
import io.zuolizhu.sportswatchapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class SignupController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/signup")
    public ModelAndView loginpageAccess(HttpSession session) {
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ModelAndView userLogin(
            @RequestParam("userID") String userID,
            @RequestParam("userName") String userName,
            @RequestParam("userEmail") String userEmail
    ) {
        // If this user does not exist in the database
        // Add this user into database
        if (!userRepository.findByUserEmail(userEmail).isPresent()) {
            Long longUserID = new Long(userID);
            User newRegisteredUser = new User(longUserID, userName, userEmail);
            userRepository.save(newRegisteredUser);
            return new ModelAndView("redirect:login");
        }

        return new ModelAndView("redirect:login");
    }
}