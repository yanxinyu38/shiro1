package com.example.shiro.controller;

import com.example.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(User user, Model model) {
        //获取subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据到token，token表示令牌
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
        //执行登录方法，如果没有任何异常则表示登录成功。否则会抛出AuthenticationException
        try {
            if(subject.isAuthenticated()) {
                return "index";
            }
            subject.login(token);
        }catch (UnknownAccountException ex) {
           //该异常表示用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch(IncorrectCredentialsException ex) {
          //表示密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        } catch (AuthenticationException ex) {
          //表示认证失败
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }
        return "index";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            subject.logout();
        }
        return "login";
    }

    /**
     * 未经授权的页面
     * @return
     */
    @RequestMapping("/unAuthor")
    public String unAuthor() {
        return "unAuthor";
    }

    /**
     * 设置首页
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 欢迎页面
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
