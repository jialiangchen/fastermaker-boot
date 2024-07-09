package com.fastermaker.modules.system.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fastermaker.common.model.Option;
import com.fastermaker.modules.system.model.entity.SysRole;
import com.fastermaker.modules.system.model.form.RoleForm;
import com.fastermaker.modules.system.model.query.RolePageQuery;
import com.fastermaker.modules.system.model.vo.RolePageVO;

import java.util.List;
import java.util.Set;

/**
 * 角色业务接口层
 *
 *
 *
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 角色分页列表
     *
     * @param queryParams
     * @return
     */
    Page<RolePageVO> listPage(RolePageQuery queryParams);


    /**
     * 角色下拉列表
     *
     * @return
     */
    List<Option> listOptions();

    /**
     *
     * @param roleForm
     * @return
     */
    boolean save(RoleForm roleForm);

    /**
     * 获取角色表单数据
     *
     * @param roleId 角色ID
     * @return  {@link RoleForm} – 角色表单数据
     */
    RoleForm getFormData(Long roleId);

    /**
     * 修改角色状态
     *
     * @param roleId 角色ID
     * @param status 角色状态(1:启用；0:禁用)
     * @return {@link Boolean}
     */
    boolean updateStatus(Long roleId, Integer status);

    /**
     * 批量删除角色
     *
     * @param ids 角色ID，多个使用英文逗号(,)分割
     * @return
     */
    boolean delete(String ids);


    /**
     * 获取角色的菜单ID集合
     *
     * @param roleId 角色ID
     * @return 菜单ID集合(包括按钮权限ID)
     */
    List<Long> getRoleMenuIds(Long roleId);


    /**
     * 修改角色的资源权限
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    boolean updateRoleMenus(Long roleId, List<Long> menuIds);

    /**
     * 获取最大范围的数据权限
     *
     * @param roles
     * @return
     */
    Integer getMaximumDataScope(Set<String> roles);


}
