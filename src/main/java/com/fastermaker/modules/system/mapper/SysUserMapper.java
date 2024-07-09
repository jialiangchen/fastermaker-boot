package com.fastermaker.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.modules.system.model.form.UserForm;
import com.fastermaker.plugin.mybatis.annotation.DataPermission;
import com.fastermaker.modules.system.model.bo.UserBO;
import com.fastermaker.modules.system.model.entity.SysUser;
import com.fastermaker.modules.system.model.dto.UserAuthInfo;
import com.fastermaker.modules.system.model.query.UserPageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户持久层
 *
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    @DataPermission(deptAlias = "u")
    Page<UserBO> listPage(Page<UserBO> page, UserPageQuery queryParams);

    /**
     * 获取用户表单详情
     *
     * @param userId 用户ID
     * @return
     */
    UserForm getFormData(Long userId);

    /**
     * 根据用户名获取认证信息
     *
     * @param username
     * @return
     */
    UserAuthInfo getUserAuthInfo(String username);


}
