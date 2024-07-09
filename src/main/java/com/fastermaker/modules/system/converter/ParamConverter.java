package com.fastermaker.modules.system.converter;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.fastermaker.modules.system.model.dto.ParamDTO;
import com.fastermaker.modules.system.model.entity.Param;
import com.fastermaker.modules.system.model.vo.ParamPageVO;
import com.fastermaker.modules.system.model.form.ParamForm;
import com.fastermaker.modules.system.model.bo.ParamBO;

/**
 * 系统参数转换器
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Mapper(componentModel = "spring")
public interface ParamConverter{

    ParamPageVO toPageVo(ParamBO bo);

    Page<ParamPageVO> toPageVo(Page<ParamBO> bo);

    ParamForm convertToForm(Param entity);

    @InheritInverseConfiguration(name = "convertToForm")
    Param convertToEntity(ParamForm entity);
}