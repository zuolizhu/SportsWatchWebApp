package io.zuolizhu.sportswatchapp.Controllers;

import io.zuolizhu.sportswatchapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AdminLoginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/adminlogin")
    public ModelAndView loginpageAccess(HttpSession session) {
        return new ModelAndView("adminlogin");
    }

    @PostMapping("/adminlogin")
    public ModelAndView userLogin(
            @RequestParam("userName") String userName,
            @RequestParam("userName") String userEmail,
            HttpSession session
    ) {
        if(userRepository.findByUserEmail(userEmail).isPresent()) {
            if (userRepository.findByUserEmail(userEmail).get().isAdmin()) {
                session.setAttribute("adminEmail", userEmail);
                return new ModelAndView("redirect:admindash");
            }
            System.out.println("User [" + userName + "] tried to login to admin panel...");
        }

        return new ModelAndView("redirect:login");
    }
}
