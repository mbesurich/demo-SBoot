package com.example.demo.dao;

import org.springframework.stereotype.Repository;
import com.example.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDaoImp implements RoleDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Role getRoleByName(String name) {
        return (Role) em
                .createQuery("SELECT r FROM Role r WHERE r.name LIKE :role")
                .setParameter("role", name)
                .getSingleResult();
    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<Role>(em.createQuery("SELECT r FROM Role r").getResultList());
    }

}
