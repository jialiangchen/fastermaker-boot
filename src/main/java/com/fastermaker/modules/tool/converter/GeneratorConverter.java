package com.fastermaker.modules.tool.converter;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.fastermaker.modules.tool.model.dto.GeneratorDTO;
import com.fastermaker.modules.tool.model.entity.Generator;
import com.fastermaker.modules.tool.model.vo.GeneratorPageVO;
import com.fastermaker.modules.tool.model.form.GeneratorForm;
import com.fastermaker.modules.tool.model.bo.GeneratorBO;

/**
 * 代码生成转换器
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Mapper(componentModel = "spring")
public interface GeneratorConverter{

    GeneratorPageVO toPageVo(GeneratorBO bo);

    Page<GeneratorPageVO> toPageVo(Page<GeneratorBO> bo);

    GeneratorForm convertToForm(Generator entity);

    @InheritInverseConfiguration(name = "convertToForm")
    Generator convertToEntity(GeneratorForm entity);
}