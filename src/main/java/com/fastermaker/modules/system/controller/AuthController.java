package com.fastermaker.modules.system.controller;

import com.fastermaker.common.result.Result;
import com.fastermaker.enums.LogModuleEnum;
import com.fastermaker.modules.system.model.dto.CaptchaResult;
import com.fastermaker.modules.system.model.dto.LoginResult;
import com.fastermaker.plugin.syslog.annotation.LogAnnotation;
import com.fastermaker.modules.system.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制层
 *
 *
 *
 */
@Tag(name = "认证中心")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    @LogAnnotation(value = "登录", module = LogModuleEnum.LOGIN)
    public Result<LoginResult> login(
            @Parameter(description = "用户名", example = "admin") @RequestParam String username,
            @Parameter(description = "密码", example = "123456") @RequestParam String password
    ) {
        LoginResult loginResult = authService.login(username, password);
        return Result.success(loginResult);
    }

    @Operation(summary = "注销")
    @DeleteMapping("/logout")
    @LogAnnotation(value = "注销", module = LogModuleEnum.LOGIN)
    public Result logout() {
        authService.logout();
        return Result.success();
    }

    @Operation(summary = "获取验证码")
    @GetMapping("/captcha")
    @LogAnnotation(value = "获取验证码", module = LogModuleEnum.LOGIN)
    public Result<CaptchaResult> getCaptcha() {
        CaptchaResult captcha = authService.getCaptcha();
        return Result.success(captcha);
    }
    @Operation(summary = "刷新Token")
    @PostMapping("/refreshToken")
    @LogAnnotation(value = "刷新Token", module = LogModuleEnum.LOGIN)
    public Result<LoginResult> refreshToken() {
        LoginResult loginResult = authService.refreshToken();
        return Result.success(loginResult);
    }
}
