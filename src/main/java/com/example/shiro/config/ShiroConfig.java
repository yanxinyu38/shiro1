package com.example.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.shiro.realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //1、设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //2、添加shiro的过滤器
        //a、anon：无需认证，直接就可以访问
        //b、authc：必须认证才可以访问
        //c、user：如果使用rememberMe的功能可以直接访问
        //d、perms：必须有资源权限才可以访问
        //e、 role：必须得到角色权限才可以访问
        Map<String,String> map = new LinkedHashMap<>();

        //以下路径必须授权才可访问
        map.put("/basic/info","perms[basic:info]");
        map.put("/basic/page","perms[basic:page]");
        map.put("/basic/adv","perms[basic:adv]");
        map.put("/basic/book","perms[basic:book]");
        map.put("/basic/column","perms[basic:column]");
        map.put("/basic/add/adv","perms[basic:adv:add]");
        map.put("/basic/del/adv","perms[basic:adv:del]");
        map.put("/basic/upd/adv","perms[basic:adv:upd]");
        map.put("/column/list","perms[column:list]");
        map.put("/column/add","perms[column:add]");
        map.put("/column/cate","perms[column:cate]");
        map.put("/system/addRole","perms[system:addRole]");
        map.put("/system/updRole","perms[system:updRole]");
        map.put("/system/roleManage","perms[system:roleManage]");
        map.put("/system/addUser","perms[system:addUser]");
        map.put("/system/updUser","perms[system:updUser]");
        map.put("/system/userManage","perms[system:userManage]");

        //以下路径必须经过认证才可以访问
        map.put("/index","authc");
        map.put("/basic/*","authc");
        map.put("/column/*","authc");
        map.put("/system/*","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //设置登录页面，也就是未经认证时要跳转到的登录页面让用户去登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置未经授权要访问的页面，也就是说虽然已经登录了。但是你还没有权限访问这个资源，那么九会 跳转到这个页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuthor");
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     * @return
     */
    @Bean("defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联Realm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 创建Realm
     * @return
     */
    @Bean("userRealm")
    public UserRealm getReal() {
        return new UserRealm();
    }

    /**
     * 创建ShiroDialect对象，用与thymeleaf和shiro标签配合使用
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect() {
        ShiroDialect shiroDialect = new ShiroDialect();
        return shiroDialect;
    }
}
