package com.fastermaker.modules.system.mapper;

import com.fastermaker.modules.system.model.entity.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.modules.system.model.bo.ParamBO;
import com.fastermaker.modules.system.model.query.ParamPageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统参数 Mapper 接口
 *
 * @author fastermaker
 * @since 2024-07-08
 */

@Mapper
public interface ParamMapper extends BaseMapper<Param> {

    /**
     * 获取系统参数分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<ParamBO> listPage(Page<ParamBO> page, ParamPageQuery queryParams);

}
