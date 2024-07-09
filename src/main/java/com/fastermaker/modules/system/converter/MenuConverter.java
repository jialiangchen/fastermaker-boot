package com.fastermaker.modules.system.converter;

import com.fastermaker.modules.system.model.form.MenuForm;
import com.fastermaker.modules.system.model.entity.SysMenu;
import com.fastermaker.modules.system.model.vo.MenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 菜单对象转换器
 *
 *
 *
 */
@Mapper(componentModel = "spring")
public interface MenuConverter {

    MenuVO convertToVo(SysMenu entity);

    @Mapping(target = "params", ignore = true)
    MenuForm convertToForm(SysMenu entity);

    @Mapping(target = "params", ignore = true)
    SysMenu convertToEntity(MenuForm menuForm);

}
