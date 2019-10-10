package com.example.shiro;

import com.example.shiro.entity.Role;
import com.example.shiro.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    RoleService roleService;

    @Test
    public void getRoleByIdTest() {
        Role role = roleService.getRoleById(5);
        System.out.println(role);
    }
}
