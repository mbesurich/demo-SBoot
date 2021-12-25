package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.User;

import java.util.Arrays;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

/*    @ModelAttribute
    public void addAttributes(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("currentUser", user);
        model.addAttribute("allRoles", userService.getAllRoles());
        model.addAttribute("newUser", new User());
    }*/

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", userService.getAllRoles());
        return "admin";
    }
/*
    @GetMapping("/add")
    public String addUser(Model model){
        model.addAttribute("newUser", new User());
        return "redirect:/admin";
    }*/

    @PostMapping("/add")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam(value = "checkRoles") String[] checkRoles) {
        user.setRoleSet(userService.getRolesByNames(checkRoles));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") Long id, @RequestParam("name") String name,
                       @RequestParam("lastName") String lastName, @RequestParam("age") int age, @RequestParam("email") String email,
                       @RequestParam("password") String password, @RequestParam(value = "checkRoles") String[] checkRoles) {
        System.out.println("+++++++++++++length"+checkRoles.length);
        Arrays.stream(checkRoles).forEach(s -> System.out.println(s));
        userService.update(id, name, lastName, age, email, password, userService.getRolesByNames(checkRoles));
        return "redirect:/admin";
    }

    @RequestMapping("/delete")
    public String deleteUserForm(@RequestParam("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
