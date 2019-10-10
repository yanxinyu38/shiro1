package com.example.shiro.controller;

import com.example.shiro.entity.Authority;
import com.example.shiro.entity.Role;
import com.example.shiro.entity.User;
import com.example.shiro.exception.CustomException;
import com.example.shiro.service.AuthoService;
import com.example.shiro.service.RoleService;
import com.example.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    AuthoService authoService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @RequestMapping(value="/addRole",method = RequestMethod.GET)
    public String addRole(Model model) {
        List<Authority> authorityList = authoService.getAllAuthorities();
        model.addAttribute("authorities",authorityList);
        return "system/addRole";
    }

    @RequestMapping(value="/addRole",method = RequestMethod.POST)
    public String addRole(String roleName,String[] perms,Model model) {
        try {
            roleService.addRole(roleName);
            roleService.addRoleAutho(roleName,perms);
        }catch (CustomException ex) {
            model.addAttribute("msg","该角色已经存在");
            return "error";
        }catch (Exception ex) {
            model.addAttribute("msg","出现未知错误");
            return "error";
        }
        model.addAttribute("msg","添加角色成功");
        return "success";
    }

    @RequestMapping("/roleManage")
    public String roleManage(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles",roles);
        return "system/roleManage";
    }

    @RequestMapping("/userManage")
    public String userManage(Model model) {
        List<User> users =userService.findAll() ;
        System.out.println(users);
        model.addAttribute("users",users);
        return "system/userManage";
    }

    @RequestMapping(value="/addUser",method = RequestMethod.GET)
    public String addUser(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles",roles);
        return "system/addUser";
    }

    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    public String addUser(User user, String[] role,Model model) {
        System.out.println(user.getUserName());
        int result = userService.addUser(user,role);
        if(result>0) {
            model.addAttribute("msg", "添加用户成功");
            return "success";
        }else {
            model.addAttribute("msg","添加用户失败");
        }
        return "error";
    }

    @RequestMapping(value = "/updUser", method = RequestMethod.GET)
    public String updUser(String name, Model model) {
        User user=userService.findUserByName(name);
        model.addAttribute("user",user);
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles",roles);
        return "system/updUser";
    }

    @RequestMapping(value="/updUser",method = RequestMethod.POST)
    public String updUser(String userName,String[] role,Model model) {
        System.out.println(userName);
        System.out.println(role);
            userService.delUserRole(userName);
            int result=userService.addUserRole(userName,role);
        if(result>0) {
            model.addAttribute("msg", "修改成功");
            return "success";
        }else {
            model.addAttribute("msg","修改失败");
        }
        return "error";
    }

    @RequestMapping("/updPass")
    public String updPass() {
        return "system/updPass";
    }

    @RequestMapping(value = "/updRole", method = RequestMethod.GET)
    public String updRole(int id,Model model) {
        List<Authority> authorityList = authoService.getAllAuthorities();
        Role role = roleService.getRoleById(id);
        List<Authority> list = role.getAuthorityList();
        //判断该角色有哪些权限，并把权限设置在权限集合的每个isSelected属性上
        for (Authority authority:authorityList) {
            for(Authority authority1 : list) {
                if(authority.getName().equals(authority1.getName())) {
                    authority.setSelected(true);
                }
            }
        }
        model.addAttribute("authorities",authorityList);
        model.addAttribute("role",role);
        return "system/updRole";
    }

    @RequestMapping(value="/updRole",method = RequestMethod.POST)
    public String updRole(Role role,String[] perms,Model model) {
        try {
            roleService.updRole(role,perms);
        }catch (CustomException ex) {
            model.addAttribute("msg","该角色已经存在");
            return "error";
        }catch (Exception ex) {
            model.addAttribute("msg","出现未知错误");
            return "error";
        }
        model.addAttribute("msg","修改角色成功");
        return "success";
    }

    @RequestMapping("/delRole")
    public String delRole(int roleId) {
        roleService.delRole(roleId);
        return "redirect:roleManage";
    }
    @RequestMapping("/delUser")
    public String delUser(String name) {
        userService.delUser(name);
        return "redirect:userManage";
    }
}
