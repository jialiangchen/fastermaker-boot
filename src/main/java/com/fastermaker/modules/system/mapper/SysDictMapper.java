package com.fastermaker.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.modules.system.model.entity.SysDict;
import com.fastermaker.modules.system.model.query.DictTypePageQuery;
import com.fastermaker.modules.system.model.vo.DictPageVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典 访问层
 *
 *  Hao
 *
 */
@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     * 字典分页列表
     *
     * @param page 分页参数
     * @param queryParams 查询参数
     * @return
     */
    Page<DictPageVO> listPage(Page<DictPageVO> page, DictTypePageQuery queryParams);
}




