package com.fastermaker.modules.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fastermaker.common.model.Option;
import com.fastermaker.modules.system.model.entity.SysDict;
import com.fastermaker.modules.system.model.form.DictForm;
import com.fastermaker.modules.system.model.query.DictTypePageQuery;
import com.fastermaker.modules.system.model.vo.DictPageVO;

import java.util.List;

/**
 * 数据字典业务接口
 *
 *
 */
public interface SysDictService extends IService<SysDict> {

    /**
     * 字典分页列表
     *
     * @param queryParams 分页查询对象
     * @return
     */
    Page<DictPageVO> listPage(DictTypePageQuery queryParams);


    /**
     * 获取字典表单详情
     *
     * @param id 字典ID
     * @return
     */
    DictForm getFormData(Long id);


    /**
     * 新增字典
     *
     * @param dictForm 字典表单
     * @return
     */
    boolean save(DictForm dictForm);


    /**
     * 修改字典
     *
     * @param dictForm 字典表单
     * @return
     */
    boolean update( DictForm dictForm);

    /**
     * 删除字典
     *
     * @param idsStr 字典ID，多个以英文逗号(,)分割
     * @return
     */
    void delete(String idsStr);


    /**
     * 获取字典的数据项
     *
     * @param code 字典编码
     * @return
     */
    List<Option> listOptions(String code);


}
