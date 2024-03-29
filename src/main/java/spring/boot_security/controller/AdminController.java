package spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.boot_security.model.Role;
import spring.boot_security.model.User;
import spring.boot_security.service.RoleService;
import spring.boot_security.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    // Create

    @GetMapping("/new")
    public String createForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roleList", roles);
        return "new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) { //todo
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> list = user.getRoles();
        for (Role role : list){
            role.setId(Long.valueOf(role.getName()));
            role.setName(roleService.getRoleById(role.getId()).getName());
        }
        user.setRoles(list);

        userService.createUser(user);
        return "redirect:/admin";
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
        model.addAttribute("roleList",roleService.getAllRoles());
        return "edit";
    }

    @PatchMapping("edit/{id}")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        Set<Role> list = user.getRoles();
        for (Role role : list){
            role.setId(Long.valueOf(role.getName()));
            role.setName(roleService.getRoleById(role.getId()).getName());
        }
        user.setRoles(list);
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    // Delete

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
