package com.fastermaker.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fastermaker.common.model.Option;
import com.fastermaker.modules.system.model.entity.SysDept;
import com.fastermaker.modules.system.model.form.DeptForm;
import com.fastermaker.modules.system.model.query.DeptQuery;
import com.fastermaker.modules.system.model.vo.DeptVO;

import java.util.List;

/**
 * 部门业务接口
 *
 */
public interface SysDeptService extends IService<SysDept> {
    /**
     * 部门列表
     *
     * @return
     */
    List<DeptVO> listPage(DeptQuery queryParams);

    /**
     * 部门树形下拉选项
     *
     * @return
     */
    List<Option> listOptions();

    /**
     * 新增部门
     *
     * @param formData
     * @return
     */
    Long save(DeptForm formData);

    /**
     * 修改部门
     *
     * @param deptId
     * @param formData
     * @return
     */
    Long update(Long deptId, DeptForm formData);

    /**
     * 删除部门
     *
     * @param ids 部门ID，多个以英文逗号,拼接字符串
     * @return
     */
    boolean deleteByIds(String ids);

    /**
     * 获取部门详情
     *
     * @param deptId
     * @return
     */
    DeptForm getFormData(Long deptId);
}
