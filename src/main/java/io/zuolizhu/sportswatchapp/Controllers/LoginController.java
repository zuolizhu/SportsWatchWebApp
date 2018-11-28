package io.zuolizhu.sportswatchapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping("/login")
    public ModelAndView loginpageAccess(HttpSession session) {
        System.out.println("Wait WAT??" + session.getAttribute("userID"));
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView userLogin(
            @RequestParam("userID") String userID,
            @RequestParam("userName") String userName,
            HttpSession session
    ) {
        System.out.println("Backend get: " + userID + " -- " + userName);
        session.setAttribute("userID", userID);
        System.out.println(session.getAttribute("userID"));
        return new ModelAndView("redirect:");
    }
}
