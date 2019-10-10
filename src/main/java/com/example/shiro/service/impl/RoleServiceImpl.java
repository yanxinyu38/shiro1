package com.example.shiro.service.impl;

import com.example.shiro.entity.Authority;
import com.example.shiro.entity.Role;
import com.example.shiro.exception.CustomException;
import com.example.shiro.mapper.RoleMapper;
import com.example.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public int addRole(String roleName) {
        int cnt = roleMapper.getRoleCountByName(roleName);
        if(cnt > 0) {
            throw new CustomException(1000,"该角色已经存在");
        }
        return roleMapper.addRole(roleName);
    }

    /**
     * 添加角色权限
     * @param roleName
     * @param autho
     * @return
     */
    @Override
    public int addRoleAutho(String roleName, String[] autho) {
        int id = roleMapper.getRoleIdByName(roleName);
        return roleMapper.addRoleAuthor(id,autho);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    @Override
    public Role getRoleById(int id) {
        Role role = roleMapper.getRoleById(id);
        List<Authority> authorities = roleMapper.getAuthoritiesByRoleId(id);
        role.setAuthorityList(authorities);
        return role;
    }

    @Override
    public void updRole(Role role,String[] autho) {
        /*修改后的角色名称不能与其他角色名重名，因此在这里需要进行验证*/
        Integer id = roleMapper.getRoleIdByName(role.getRoleName());
        //根据角色名称查询角色id后如果这个id不为空并且不与当前角色id一致则表示有其他角色名与修改后的角色名冲突
        if(id != null && id.intValue()!=role.getId()) {
            throw new CustomException(2000,"该角色已经存在");
        }
        //修改角色名
        roleMapper.updRole(role);
        //删除该角色的所有权限
        roleMapper.delRoleAutho(role.getId());
        //重新添加该角色的所有权限
        roleMapper.addRoleAuthor(role.getId(),autho);
    }

    /**
     * 删除角色
     * @param roleId
     */
    @Override
    public void delRole(int roleId) {
        //删除角色权限表的数据
        roleMapper.delRoleAutho(roleId);
        //删除用户角色表的数据
        roleMapper.delUserRole(roleId);
        //删除角色
        roleMapper.delRole(roleId);
    }
}
