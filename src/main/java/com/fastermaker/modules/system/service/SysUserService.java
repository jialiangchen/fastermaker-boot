package com.fastermaker.modules.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fastermaker.modules.system.model.entity.SysUser;
import com.fastermaker.modules.system.model.form.UserForm;
import com.fastermaker.modules.system.model.dto.UserAuthInfo;
import com.fastermaker.modules.system.model.query.UserPageQuery;
import com.fastermaker.modules.system.model.vo.UserInfoVO;
import com.fastermaker.modules.system.model.vo.UserPageVO;

/**
 * 用户业务接口
 *
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 用户分页列表
     *
     * @return
     */
    IPage<UserPageVO> listPage(UserPageQuery queryParams);


    /**
     * 获取用户表单数据
     *
     * @param userId
     * @return
     */
    UserForm getFormData(Long userId);


    /**
     * 新增用户
     *
     * @param userForm 用户表单对象
     * @return
     */
    boolean save(UserForm userForm);

    /**
     * 修改用户
     *
     * @param userForm 用户表单对象
     * @return
     */
    boolean update(UserForm userForm);


    /**
     * 删除用户
     *
     * @param idsStr 用户ID，多个以英文逗号(,)分割
     * @return
     */
    boolean delete(String idsStr);


    /**
     * 修改用户密码
     *
     * @param userId   用户ID
     * @param password 用户密码
     * @return
     */
    boolean updatePassword(Long userId, String password);

    /**
     * 根据用户名获取认证信息
     *
     * @param username 用户名
     * @return {@link UserAuthInfo}
     */

    UserAuthInfo getUserAuthInfo(String username);



    /**
     * 获取登录用户信息
     *
     * @return
     */
    UserInfoVO getCurrentUserInfo();
}
