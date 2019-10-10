package com.example.shiro.mapper;

import com.example.shiro.entity.Authority;
import com.example.shiro.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    /**
     * 添加角色
     * @param roleName
     * @return
     */
    int addRole(String roleName);

    /**
     * 根据角色名称获取这个角色的数量
     * @param roleName
     * @return
     */
    int getRoleCountByName(String roleName);

    /**
     * 添加角色权限，修改角色的时候也会用到，思路是先删除这个角色对应的所有权限。然后再批量添加
     * @param roleId
     * @param auths
     * @return
     */
    int addRoleAuthor(@Param("roleId") int roleId,@Param("auths") String[] auths);

    /**
     * 根据角色名称获取角色的id，角色名必须唯一
     * @param roleName
     * @return
     */
    Integer getRoleIdByName(String roleName);

    /**
     * 查询所有的角色
     * @return
     */
    public List<Role> getAllRoles();

    /**
     * 根据id查询到角色对象
     * @param id
     * @return
     */
    Role getRoleById(int id);

    List<Authority> getAuthoritiesByRoleId(int id);

    /**
     * 修改角色名称
     * @param role
     * @return
     */
    int updRole(Role role);

    /**
     * 删除该角色对应的权限
     * @param id
     * @return
     */
    int delRoleAutho(int id);

    /**
     * 删除角色
     * @param id
     * @return
     */
    int delRole(int id);

    /**
     * 删除用户对应的角色
     * @param id
     * @return
     */
    int delUserRole(int id);
}
