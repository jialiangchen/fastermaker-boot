package com.fastermaker.modules.system.service;

import com.fastermaker.modules.system.model.entity.Param;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fastermaker.modules.system.model.form.ParamForm;
import com.fastermaker.modules.system.model.query.ParamPageQuery;
import com.fastermaker.modules.system.model.vo.ParamPageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 系统参数 服务类
 *
 * @author fastermaker
 * @since 2024-07-08
 */
public interface ParamService extends IService<Param> {


    /**
     *系统参数分页列表
     *
     * @return
     */
    IPage<ParamPageVO> listPage(ParamPageQuery queryParams);


    /**
     * 获取系统参数表单数据
     *
     * @param id 系统参数ID
     * @return
     */
     ParamForm getFormData(Long id);


    /**
     * 新增系统参数
     *
     * @param formData 系统参数表单对象
     * @return
     */
    boolean save(ParamForm formData);

    /**
     * 修改系统参数
     *
     * @param formData 系统参数表单对象
     * @return
     */
    boolean update(ParamForm formData);


    /**
     * 删除系统参数
     *
     * @param ids 系统参数ID，多个以英文逗号(,)分割
     * @return
     */
    boolean delete(String ids);

}
