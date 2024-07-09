package com.fastermaker.modules.system.service;

import com.fastermaker.modules.system.model.dto.CaptchaResult;
import com.fastermaker.modules.system.model.dto.LoginResult;

/**
 * 认证服务接口
 *
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    LoginResult login(String username, String password);

    /**
     * 登出
     */
    void logout();

    /**
     * 获取验证码
     *
     * @return 验证码
     */
    CaptchaResult getCaptcha();

    /**
     *
     *
     * @return 刷新TOKEN结果
     */
    LoginResult refreshToken();
}
