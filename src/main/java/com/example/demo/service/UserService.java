package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void addUser(User user);
    public void update(Long id, String name, String lastName, int age, String email, String password, Set<Role> roles);
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUserById(Long id);
    Set<Role> getAllRoles();
    Role getRoleByName(String name);
    Set<Role> getRolesByNames(String[] names);
    User getUserByEmail(String email);
}
