package com.fastermaker.modules.tool.service;

import com.fastermaker.modules.tool.model.entity.Generator;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fastermaker.modules.tool.model.form.GeneratorForm;
import com.fastermaker.modules.tool.model.query.GeneratorPageQuery;
import com.fastermaker.modules.tool.model.vo.GeneratorPageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.IOException;

/**
 * 代码生成 服务类
 *
 * @author fastermaker
 * @since 2024-07-08
 */
public interface GeneratorService extends IService<Generator> {


    /**
     *代码生成分页列表
     *
     * @return
     */
    IPage<GeneratorPageVO> listPage(GeneratorPageQuery queryParams);


    /**
     * 获取代码生成表单数据
     *
     * @param id 代码生成ID
     * @return
     */
     GeneratorForm getFormData(Long id);


    /**
     * 新增代码生成
     *
     * @param formData 代码生成表单对象
     * @return
     */
    boolean save(GeneratorForm formData);

    /**
     * 修改代码生成
     *
     * @param formData 代码生成表单对象
     * @return
     */
    boolean update(GeneratorForm formData);


    /**
     * 删除代码生成
     *
     * @param ids 代码生成ID，多个以英文逗号(,)分割
     * @return
     */
    boolean delete(String ids);

    /**
     * 下载代码
     *
     * @param id 代码生成ID
     * @return
     */
    byte[]  download(String id) throws IOException;
}
