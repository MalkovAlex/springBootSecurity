package spring.boot_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.boot_security.model.User;
import spring.boot_security.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // Create

    @GetMapping("/new")
    public String createForm(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:admin";
    }

    // Read

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.getList());
        return "admin";
    }

    @GetMapping("/user/{id}")
    public String readUser(Model model, @PathVariable(name = "id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "show";
    }

    // Update

    @GetMapping("user/{id}/edit")
    public String editUser(Model model, @PathVariable() long id) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("user/{id}")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    // Delete

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
