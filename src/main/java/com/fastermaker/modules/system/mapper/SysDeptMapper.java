package com.fastermaker.modules.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.fastermaker.plugin.mybatis.annotation.DataPermission;
import com.fastermaker.modules.system.model.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {

    @DataPermission(deptIdColumnName = "id")
    @Override
    List<SysDept> selectList(@Param(Constants.WRAPPER) Wrapper<SysDept> queryWrapper);
}
