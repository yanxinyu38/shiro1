package com.example.shiro.service;

import com.example.shiro.entity.Authority;
import com.example.shiro.entity.Role;
import com.example.shiro.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findUserByName(String name);
    List<Authority> findAuthorities(String name);
    int addUser(User user,String[] roleId);
    void updUsers(String name, String[] roles);
    void delUser(String name);
    int addUserRole(String name,String[] roles);
    void delUserRole(String name);
    int findUsrRole(String name);
}
