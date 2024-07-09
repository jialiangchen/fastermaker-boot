package com.fastermaker.modules.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.modules.system.model.vo.DictPageVO;
import com.fastermaker.modules.system.model.entity.SysDict;
import com.fastermaker.modules.system.model.form.DictForm;
import org.mapstruct.Mapper;

/**
 * 字典 对象转换器
 *
 *
 *
 */
@Mapper(componentModel = "spring")
public interface DictConverter {

    Page<DictPageVO> convertToPageVo(Page<SysDict> page);

    DictForm convertToForm(SysDict entity);

    SysDict convertToEntity(DictForm entity);
}
