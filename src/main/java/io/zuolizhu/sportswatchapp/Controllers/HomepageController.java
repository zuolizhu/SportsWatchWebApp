package io.zuolizhu.sportswatchapp.Controllers;

import io.zuolizhu.sportswatchapp.Models.User;
import io.zuolizhu.sportswatchapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homepage(Model model) {

        User user = userService.findByUserID(1L);
        System.out.println(user.toString());
        model.addAttribute("user", user);
        model.addAttribute("secondUser", "is this would work??");
        return "homepage";
    }
}
