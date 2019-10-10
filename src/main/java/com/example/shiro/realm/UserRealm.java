package com.example.shiro.realm;

import com.example.shiro.entity.Authority;
import com.example.shiro.entity.User;
import com.example.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //设置授权字符串，注意这里要与前面的map.put("/user/add","perms[user:add]");当中的user:add保持一致。
        //info.addStringPermission("user:add");
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //此处取出的user就是之前在认证时向SimpleAuthenticationInfo里面添加的参数
        User user = (User)subject.getPrincipal();
        List<Authority> authorityList = userService.findAuthorities(user.getUserName());
        for(Authority authority:authorityList) {
            info.addStringPermission(authority.getPerms());
        }
        return info;
    }

    /**
     * 认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        User user = userService.findUserByName((token.getUsername()));
        //验证用户名
        if(user==null || !token.getUsername().equals(user.getUserName())) {
            //返回空则内部会报UnknownAccountException异常
            return null;
        }
        //判断密码
        /*
        参数1：在shiro当中保存的一个值，在这里保存的是user对象。此后在认证的时候会用到。
        参数2：代表密码
        参数3：代表realm的名字
         */
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
