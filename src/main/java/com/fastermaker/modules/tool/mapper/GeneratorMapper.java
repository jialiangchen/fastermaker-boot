package com.fastermaker.modules.tool.mapper;

import com.fastermaker.modules.tool.model.entity.Generator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.modules.tool.model.bo.GeneratorBO;
import com.fastermaker.modules.tool.model.query.GeneratorPageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 代码生成 Mapper 接口
 *
 * @author fastermaker
 * @since 2024-07-08
 */

@Mapper
public interface GeneratorMapper extends BaseMapper<Generator> {

    /**
     * 获取代码生成分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<GeneratorBO> listPage(Page<GeneratorBO> page, GeneratorPageQuery queryParams);

}
