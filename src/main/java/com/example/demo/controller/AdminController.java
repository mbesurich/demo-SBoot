package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "admin";
    }

    @GetMapping("/new")
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
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "update";
    }

    @PostMapping(value = "/update/{id}")
    public String editUserPost(@PathVariable("id") Long id, @ModelAttribute("user") User user, @RequestParam(value = "checkRoles") String[] checkRoles) {
        user.setRoleSet(ConvertStringToRolesFromDB(checkRoles));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserForm(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    private Set<Role> ConvertStringToRolesFromDB(String[] checkRoles) {
        Set<Role> allRoles = userService.getAllRoles();
        Set<Role> roleHashSet = new HashSet<>();
        for (String rolesFromParam : checkRoles) {
            roleHashSet.add(
                    allRoles
                            .stream()
                            .filter(r -> r.getName().equals(rolesFromParam))
                            .findFirst()
                            .get()
            );
        }
        return roleHashSet;
    }
}
