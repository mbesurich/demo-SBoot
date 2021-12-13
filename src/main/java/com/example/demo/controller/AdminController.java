package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Role;
import com.example.demo.model.User;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        return "admin";
    }


    @PostMapping("/add")
    public String saveCustomer(@ModelAttribute("user") User user, @RequestParam("role") String role) {
        user.setRoleSet(setNewRoles(role));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") Long id, @RequestParam("name") String name,
                       @RequestParam("lastName") String lastName, @RequestParam("email") String email,
                       @RequestParam("password") String password, @RequestParam("role") String role) {
        User user = userService.getUserById(id);
        user.setName(name);
        user.setSurName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoleSet(setNewRoles(role));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @RequestMapping("/delete")
    public String deleteUserForm(@RequestParam("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }


/*    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        Set<Role> roles = userService.getAllRoles();
        model.addAttribute("roles", roles);
        return "new";
    }

    @PostMapping("/save")
    public String newUserPost(@ModelAttribute("user") User user, @RequestParam(value = "checkRoles") String[] checkRoles) {
        user.setRoleSet(ConvertStringToRolesFromDB(checkRoles));
        userService.addUser(user);
        return "redirect:/admin";
    }*/

/*    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "update";
    }

    @PostMapping(value = "/update/{id}")
    public String editUserPost(@PathVariable("id") Long id, @ModelAttribute("user") User user, @RequestParam(value = "checkRoles") String[] checkRoles) {
        user.setRoleSet(ConvertStringToRolesFromDB(checkRoles));
        userService.addUser(user);
        return "redirect:/admin";
    }*/

    @GetMapping("/delete/{id}")
    public String deleteUserForm(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    private Set<Role> setNewRoles(String newRoles) {
        Set<Role> roles = new HashSet<>();
        if (newRoles.contains(", ")) {
            roles.add(userService.getRoleByName("ROLE_USER"));
            roles.add(userService.getRoleByName("ROLE_ADMIN"));
        } else {
            roles.add(userService.getRoleByName(newRoles));
        }
        return roles;
    }
}
