package com.example.shiro.mapper;

import com.example.shiro.entity.Authority;
import com.example.shiro.entity.Role;
import com.example.shiro.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    User findUserByName(String userName);

    /**
     * 获得该用户的所有权限
     *
     * @param userName
     * @return
     */
    List<Authority> gerAuthoritiesByUserName(String userName);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 添加用户角色
     *
     * @return
     */
    int addUserRole(@Param("name") String name, @Param("roles") String[] roles);

    /**
     * 查询全部用户
     *
     * @return
     */
    public List<User> findAll();

    int delUser(String name);

    int updUser(User user);

    int delUserRole(String name);

    int findUsrRole(String name);
}
