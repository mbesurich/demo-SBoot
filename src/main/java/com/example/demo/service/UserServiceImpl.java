package com.example.demo.service;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;

@Service
//@EnableTransactionManagement
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDao userDao;
    private RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

//    @Transactional
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

//    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

//    @Transactional
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

//    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }

//    @Transactional
    @Override
    public Set<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

//    @Transactional
    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    public Set<Role> getRolesByNames(String[] names) {
        return roleDao.getRolesByNames(names);
    }

//    @Transactional
    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

//    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.getUserByEmail(email);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException(email + "is not found");
        }
        return user;
    }
}
