package com.fastermaker.modules.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.modules.system.model.form.RoleForm;
import com.fastermaker.modules.system.model.vo.RolePageVO;
import com.fastermaker.common.model.Option;
import com.fastermaker.modules.system.model.entity.SysRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 角色对象转换器
 *
 *
 *
 */
@Mapper(componentModel = "spring")
public interface RoleConverter {

    Page<RolePageVO> convertToPageVo(Page<SysRole> page);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(SysRole role);


    List<Option> entities2Options(List<SysRole> roles);

    SysRole convertToEntity(RoleForm roleForm);

    RoleForm convertToForm(SysRole entity);
}
