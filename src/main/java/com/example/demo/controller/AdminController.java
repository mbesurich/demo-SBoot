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
import org.thymeleaf.TemplateEngine;

import java.util.Arrays;
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

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", userService.getAllRoles());
        return "admin";
    }


    @PostMapping("/add")
    public String saveCustomer(@ModelAttribute("user") User user, @RequestParam(value = "checkRoles") String[] checkRoles) {
        user.setRoleSet(userService.getRolesByNames(checkRoles));
        userService.addUser(user);
        return "redirect:/admin";
    }

  /*  @GetMapping("edit")
    public String getEdit(@RequestParam("id") Long id, Model model){
        model.addAttribute("allRoles", userService.getAllRoles());
    }*/

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
