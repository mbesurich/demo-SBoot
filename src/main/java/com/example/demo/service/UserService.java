package com.example.demo.service;




import com.example.demo.model.Role;
import com.example.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void add(User user);
    List<User> show();
    User getUser(Long id);
    void delete(Long id);
    Set<Role> getAllRoles();
    Role getRoleByName(String name);
    User getByEmail(String email);
}
