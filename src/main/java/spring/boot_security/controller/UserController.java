package spring.boot_security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.boot_security.model.User;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String userView (Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }
}
