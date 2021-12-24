package com.example.demo.dao;

import com.example.demo.model.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> getAllRoles();
    Role getRoleByName(String name);
    Set<Role> getRolesByNames(String[] names);
}
