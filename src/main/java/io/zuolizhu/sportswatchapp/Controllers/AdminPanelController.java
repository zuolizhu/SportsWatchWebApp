package io.zuolizhu.sportswatchapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AdminPanelController {
    @GetMapping("/admindash")
    public ModelAndView adminDashboard() {
        return new ModelAndView("admindash");
    }
}
