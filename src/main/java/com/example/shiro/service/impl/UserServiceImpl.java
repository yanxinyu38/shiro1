package com.example.shiro.service.impl;

import com.example.shiro.entity.Authority;
import com.example.shiro.entity.Role;
import com.example.shiro.entity.User;
import com.example.shiro.exception.CustomException;
import com.example.shiro.mapper.UserMapper;
import com.example.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    @Override
    public List<Authority> findAuthorities(String name) {
        return userMapper.gerAuthoritiesByUserName(name);
    }

    /**
     * 添加用户和用户角色
     * @param user
     * @param roleId
     * @return
     */
    @Override
    public int addUser(User user,String[] roleId) {
        userMapper.addUser(user);
        return userMapper.addUserRole(user.getUserName(),roleId);
    }

    @Override
    public void updUsers(String name, String[] roleId) {
        //修改角色名
        //删除该角色的所有权限
        userMapper.delUserRole(name);
        //重新添加该角色的所有权限
        userMapper.addUserRole(name,roleId);
    }

    @Override
    public void delUser(String name) {
        int id=userMapper.findUsrRole(name);
        if(id==1){
            throw new CustomException(2000,"管理员不能删除");
        }else {

            userMapper.delUserRole(name);
            userMapper.delUser(name);
        }
    }

    @Override
    public int addUserRole(String userName, String[] roles) {
        return userMapper.addUserRole(userName,roles);
    }

    @Override
    public void delUserRole(String name) {
         userMapper.delUserRole(name);
    }

    @Override
    public int findUsrRole(String name) {
        return userMapper.findUsrRole(name);
    }

}
