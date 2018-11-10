package io.zuolizhu.sportswatchapp.Controllers;

import io.zuolizhu.sportswatchapp.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {
    @Autowired
    private TeamRepository teamRepository;
    @GetMapping("/")
    public String homepage(Model model) {
        System.out.println("Home Controller : " + teamRepository.findAll());
        model.addAttribute("teams", teamRepository.findAll());
        return "homepage";
    }
}
