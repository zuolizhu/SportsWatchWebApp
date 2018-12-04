package io.zuolizhu.sportswatchapp.Controllers;

import io.zuolizhu.sportswatchapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public ModelAndView userloginpage(HttpSession session) {
        if (session.getAttribute("userEmail") != null) {
            String accessEmail = session.getAttribute("userEmail").toString();
            if (userRepository.findByUserEmail(accessEmail).isPresent()) {
                return new ModelAndView("redirect:selectteams");
            }
        }
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView userloginrequest(
            @RequestParam("userEmail") String userEmail,
            HttpSession session,
            Model model
    ) {
        if(userRepository.findByUserEmail(userEmail).isPresent()) {
            session.setAttribute("userEmail", userEmail);
            return new ModelAndView("redirect:selectteams");
        }

        String errorMessage = "Please register before login";
        model.addAttribute("errorMessage", errorMessage);
        return new ModelAndView("error");
    }
}
