package io.zuolizhu.sportswatchapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @GetMapping("/login")
    public ModelAndView loginpageAccess() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView userLogin(
            @RequestParam("userID") String userID,
            @RequestParam("userName") String userName
    ) {
        System.out.println("Backend get: " + userID + " -- " + userName);
        return new ModelAndView("redirect:");
    }
}
