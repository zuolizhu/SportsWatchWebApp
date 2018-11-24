package io.zuolizhu.sportswatchapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @PostMapping("/login")
    public String loginctrl(@ModelAttribute Object o) {
        System.out.println("Submit???");
        System.out.println(o.toString());
        return "redirect:";
    }
}
