package com.fastermaker.modules.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.modules.system.model.bo.UserBO;
import com.fastermaker.modules.system.model.entity.SysUser;
import com.fastermaker.modules.system.model.form.UserForm;
import com.fastermaker.modules.system.model.vo.UserInfoVO;
import com.fastermaker.modules.system.model.vo.UserPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 用户对象转换器
 *
 *
 *
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mappings({
            @Mapping(target = "genderLabel", expression = "java(com.fastermaker.common.base.IBaseEnum.getLabelByValue(bo.getGender(), com.fastermaker.enums.GenderEnum.class))")
    })
    UserPageVO toPageVo(UserBO bo);

    Page<UserPageVO> toPageVo(Page<UserBO> bo);

    UserForm convertToForm(SysUser entity);

    @InheritInverseConfiguration(name = "convertToForm")
    SysUser convertToEntity(UserForm entity);

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    UserInfoVO toUserInfoVo(SysUser entity);

}
