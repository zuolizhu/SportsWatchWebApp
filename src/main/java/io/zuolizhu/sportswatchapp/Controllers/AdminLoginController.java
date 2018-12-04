package io.zuolizhu.sportswatchapp.Controllers;

import io.zuolizhu.sportswatchapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ModelAndView adminloginpageAccess(HttpSession session) {
        if (session.getAttribute("userEmail") != null) {
            String accessEmail = session.getAttribute("userEmail").toString();
            if (userRepository.findByUserEmail(accessEmail).isPresent()) {
                if (userRepository.findByUserEmail(accessEmail).get().isAdmin()) {
                    return new ModelAndView("redirect:admindash");
                }
            }
        }
        return new ModelAndView("adminlogin");
    }

    @PostMapping("/adminlogin")
    public ModelAndView adminlogin(
            @RequestParam("userName") String userName,
            @RequestParam("userEmail") String userEmail,
            HttpSession session,
            Model model
    ) {
        if(userRepository.findByUserEmail(userEmail).isPresent()) {
            if (userRepository.findByUserEmail(userEmail).get().isAdmin()) {
                session.setAttribute("userEmail", userEmail);
                return new ModelAndView("redirect:admindash");
            }
            System.out.println("User [" + userName + "] tried to login to admin panel ...");
        }
        String errorMessage = userName + " is not a valid admin ...";
        model.addAttribute("errorMessage", errorMessage);
        return new ModelAndView("error");
    }
}
