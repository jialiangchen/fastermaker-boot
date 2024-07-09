package com.fastermaker.modules.system.mapper;

/*
 * 菜单 访问层
 *
 *
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastermaker.modules.system.model.entity.SysMenu;
import com.fastermaker.modules.system.model.bo.RouteBO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<RouteBO> listRoutes(Set<String> roles);

}
