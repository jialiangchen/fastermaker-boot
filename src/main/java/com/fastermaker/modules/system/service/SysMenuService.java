package com.fastermaker.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fastermaker.common.model.Option;
import com.fastermaker.modules.system.model.entity.SysMenu;
import com.fastermaker.modules.system.model.form.MenuForm;
import com.fastermaker.modules.system.model.query.MenuQuery;
import com.fastermaker.modules.system.model.vo.MenuVO;
import com.fastermaker.modules.system.model.vo.RouteVO;

import java.util.List;
import java.util.Set;

/**
 * 菜单业务接口
 *
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 获取菜单表格列表
     */
    List<MenuVO> listPage(MenuQuery queryParams);


    /**
     * 获取菜单下拉列表
     */
    List<Option> listOptions();

    /**
     * 新增菜单
     *
     * @param menuForm  菜单表单对象
     */
    boolean save(MenuForm menuForm);

    /**
     * 获取路由列表
     */
    List<RouteVO> listRoutes(Set<String> roles);

    /**
     * 修改菜单显示状态
     *
     * @param menuId 菜单ID
     * @param visible 是否显示(1-显示 2-隐藏)
     */
    boolean updateMenuVisible(Long menuId, Integer visible);

    /**
     * 获取菜单表单数据
     *
     * @param id 菜单ID
     */
    MenuForm getFormData(Long id);

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     */
    boolean delete(Long id);
}
