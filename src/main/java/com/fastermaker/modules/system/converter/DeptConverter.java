package com.fastermaker.modules.system.converter;

import com.fastermaker.modules.system.model.form.DeptForm;
import com.fastermaker.modules.system.model.entity.SysDept;
import com.fastermaker.modules.system.model.vo.DeptVO;
import org.mapstruct.Mapper;

/**
 * 部门对象转换器
 *
 *
 *
 */
@Mapper(componentModel = "spring")
public interface DeptConverter {

    DeptForm convertToForm(SysDept entity);

    DeptVO convertToVo(SysDept entity);

    SysDept convertToEntity(DeptForm deptForm);

}
