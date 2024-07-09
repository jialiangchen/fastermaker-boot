package com.fastermaker.modules.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.modules.system.model.vo.DictPageVO;
import com.fastermaker.common.model.Option;
import com.fastermaker.modules.system.model.entity.SysDictItem;
import com.fastermaker.modules.system.model.form.DictForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 字典项 对象转换器
 *
 *
 *
 */
@Mapper(componentModel = "spring")
public interface DictItemConverter {

    Page<DictPageVO> convertToPageVo(Page<SysDictItem> page);

    DictForm convertToForm(SysDictItem entity);

    SysDictItem convertToEntity(DictForm.DictItem dictFormDictItems);
    List<SysDictItem> convertToEntity(List<DictForm.DictItem> dictFormDictItems);

    DictForm.DictItem convertToDictFormDictItem(SysDictItem entity);
    List<DictForm.DictItem> convertToDictFormDictItem(List<SysDictItem> entities);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option convertToOption(SysDictItem dictItem);
    List<Option> convertToOption(List<SysDictItem> dictItems);
}
