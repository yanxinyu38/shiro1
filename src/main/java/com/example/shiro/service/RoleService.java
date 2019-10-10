package com.example.shiro.service;

import com.example.shiro.entity.Role;

import java.util.List;

public interface RoleService {
    int addRole(String roleName);

    int addRoleAutho(String roleName,String[] autho);

    List<Role> getAllRoles();

    Role getRoleById(int id);

    void updRole(Role role,String[] autho);

    void delRole(int roleId);
}
