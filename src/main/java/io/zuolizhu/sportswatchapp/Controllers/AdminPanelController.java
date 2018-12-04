package io.zuolizhu.sportswatchapp.Controllers;

import io.zuolizhu.sportswatchapp.Models.User;
import io.zuolizhu.sportswatchapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
@Controller
public class AdminPanelController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admindash")
    public ModelAndView adminDashboard(HttpSession session, Model model) {
        if (session.getAttribute("userEmail") != null) {
            String accessEmail = session.getAttribute("userEmail").toString();
            if (userRepository.findByUserEmail(accessEmail).isPresent()) {
                if (userRepository.findByUserEmail(accessEmail).get().isAdmin()) {
                    ModelAndView allUsers = new ModelAndView("admindash");
                    allUsers.addObject("allusers", userRepository.findAll());
                    return allUsers;
                }
            }
        }
        String errorMessage = "Invalid access, please login as an administrator!";
        model.addAttribute("errorMessage", errorMessage);
        return new ModelAndView("error");
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
