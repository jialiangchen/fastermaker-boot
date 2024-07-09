package com.fastermaker.modules.system.service.impl;

import com.fastermaker.modules.system.model.entity.Param;
import com.fastermaker.modules.system.mapper.ParamMapper;
import com.fastermaker.modules.system.service.ParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fastermaker.security.util.SecurityUtils;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.fastermaker.common.util.DateUtils;
import java.util.Arrays;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.time.LocalDateTime;
import com.fastermaker.modules.system.model.form.ParamForm;
import com.fastermaker.modules.system.model.query.ParamPageQuery;
import com.fastermaker.modules.system.model.bo.ParamBO;
import com.fastermaker.modules.system.model.vo.ParamPageVO;
import com.fastermaker.modules.system.converter.ParamConverter;

/**
 * 系统参数服务实现类
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Service
@RequiredArgsConstructor
public class ParamServiceImpl extends ServiceImpl<ParamMapper, Param> implements ParamService {

    private final ParamConverter paramConverter;

    /**
    * 获取系统参数分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<ParamPageVO>} 系统参数分页列表
    */
    @Override
    public IPage<ParamPageVO> listPage(ParamPageQuery queryParams) {

        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<ParamBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");

        // 查询数据
        Page<ParamBO> boPage = this.baseMapper.listPage(page, queryParams);

        // 实体转换
        return paramConverter.toPageVo(boPage);
    }

    /**
     * 获取系统参数表单数据
     *
     * @param id 系统参数ID
     * @return
     */
    @Override
    public ParamForm getFormData(Long id) {
        Param entity = this.getById(id);
        return paramConverter.convertToForm(entity);
    }

    /**
     * 新增系统参数
     *
     * @param formData 系统参数表单对象
     * @return
     */
    @Override
    public boolean save(ParamForm formData) {
        // 实体转换 form->entity
        Param entity = paramConverter.convertToEntity(formData);
        Long userId = SecurityUtils.getUserId();
        entity.setCreateBy(userId);
        return this.save(entity);
    }

    /**
     * 更新系统参数
     *
     * @param formData 系统参数表单对象
     * @return
     */
    @Override
    public boolean update(ParamForm formData) {
        Param entity = paramConverter.convertToEntity(formData);
        Long userId = SecurityUtils.getUserId();
        entity.setUpdateBy(userId);
        entity.setUpdateTime(LocalDateTime.now());
        return this.updateById(entity);
    }

    /**
     * 删除系统参数
     *
     * @param ids 系统参数ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean delete(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的系统参数数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }


}
