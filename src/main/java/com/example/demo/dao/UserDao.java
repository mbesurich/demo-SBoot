package com.example.demo.dao;



import com.example.demo.model.Role;
import com.example.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {

    void add(User user);
    List<User> show();
    User getUser(Long id);
    void delete(Long id);

    Role getRoleByName(String name);
    Set<Role> getAllRoles();

    User getByEmail(String email);
}
