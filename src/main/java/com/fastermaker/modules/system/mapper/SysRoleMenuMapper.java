package com.fastermaker.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastermaker.modules.system.model.bo.RolePermsBO;
import com.fastermaker.modules.system.model.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * 角色菜单访问层
 *
 *
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 获取角色拥有的菜单ID集合
     *
     * @param roleId 角色ID
     * @return 菜单ID集合
     */
    List<Long> listMenuIdsByRoleId(Long roleId);

    /**
     * 获取权限和拥有权限的角色列表
     */
    List<RolePermsBO> getRolePermsList(String roleCode);


    /**
     * 获取角色权限集合
     *
     * @param roles
     * @return
     */
    Set<String> listRolePerms(Set<String> roles);
}
