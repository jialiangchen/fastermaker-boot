package com.fastermaker.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fastermaker.modules.system.model.query.UserPageQuery;
import com.fastermaker.plugin.syslog.annotation.LogAnnotation;
import com.fastermaker.modules.system.service.SysUserService;
import com.fastermaker.common.result.PageResult;
import com.fastermaker.common.result.Result;
import com.fastermaker.common.validator.UpdateGroup;
import com.fastermaker.enums.LogModuleEnum;
import com.fastermaker.modules.system.model.form.UserForm;
import com.fastermaker.modules.system.model.entity.SysUser;
import com.fastermaker.modules.system.model.vo.UserInfoVO;
import com.fastermaker.modules.system.model.vo.UserPageVO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 用户信息
 *
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService userService;

    @Operation(summary = "用户分页列表")
    @PostMapping("/listPage")
    @LogAnnotation( value = "用户分页列表",module = LogModuleEnum.USER)
    public PageResult<UserPageVO> listPage(@RequestBody UserPageQuery queryParams) {
        IPage<UserPageVO> result = userService.listPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增用户")
    @PostMapping("save")
    @PreAuthorize("@permission.hasPerm('sys:user:save')")
    @LogAnnotation( value = "新增用户",module = LogModuleEnum.USER)
    public Result save(
            @RequestBody @Valid UserForm userForm
    ) {
        boolean result = userService.save(userForm);
        return Result.judge(result);
    }

    @Operation(summary = "用户表单数据")
    @GetMapping("/getFormData/{userId}")
    @LogAnnotation( value = "用户表单数据",module = LogModuleEnum.USER)
    public Result<UserForm> getFormData(
            @Parameter(description = "用户ID") @PathVariable Long userId
    ) {
        UserForm formData = userService.getFormData(userId);
        return Result.success(formData);
    }

    @Operation(summary = "修改用户")
    @PutMapping(value = "/update")
    @PreAuthorize("@permission.hasPerm('sys:user:update')")
    @LogAnnotation( value = "修改用户",module = LogModuleEnum.USER)
    public Result update(
            @RequestBody @Validated(UpdateGroup.class) UserForm userForm) {
        boolean result = userService.update(userForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/delete/{ids}")
    @PreAuthorize("@permission.hasPerm('sys:user:delete')")
    @LogAnnotation( value = "删除用户",module = LogModuleEnum.USER)
    public Result delete(
            @Parameter(description = "用户ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = userService.delete(ids);
        return Result.judge(result);
    }

    @Operation(summary = "修改用户密码")
    @PutMapping(value = "/updatePassword")
    @PreAuthorize("@permission.hasPerm('sys:user:password:reset')")
    @LogAnnotation( value = "修改用户密码",module = LogModuleEnum.USER)
    public Result updatePassword(
            @Parameter(description = "用户ID") @RequestParam Long id,
            @RequestParam String password
    ) {
        boolean result = userService.updatePassword(id, password);
        return Result.judge(result);
    }

    @Operation(summary = "修改用户状态")
    @PutMapping(value = "/updateStatus")
    @LogAnnotation( value = "修改用户状态",module = LogModuleEnum.USER)
    public Result updateStatus(
            @Parameter(description = "用户ID") @RequestParam Long userId,
            @Parameter(description = "用户状态(1:启用;0:禁用)") @RequestParam Integer status
    ) {
        boolean result = userService.update(new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getId, userId)
                .set(SysUser::getStatus, status)
        );
        return Result.judge(result);
    }

    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/getCurrentUserInfo")
    @LogAnnotation( value = "获取当前登录用户信息",module = LogModuleEnum.USER)
    public Result<UserInfoVO> getCurrentUserInfo() {
        UserInfoVO userInfoVO = userService.getCurrentUserInfo();
        return Result.success(userInfoVO);
    }

}
