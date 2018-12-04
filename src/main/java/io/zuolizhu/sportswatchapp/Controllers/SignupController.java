package io.zuolizhu.sportswatchapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class SignupController {
    @GetMapping("/signup")
    public ModelAndView loginpageAccess(HttpSession session) {
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ModelAndView userLogin(
            @RequestParam("userID") String userID,
            @RequestParam("userName") String userName
    ) {
//        System.out.println("Backend get: " + userID + " -- " + userName);


        return new ModelAndView("redirect:login");

    }
}