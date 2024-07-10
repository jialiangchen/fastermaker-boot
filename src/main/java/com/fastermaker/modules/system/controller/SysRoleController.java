package com.fastermaker.modules.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.common.model.Option;
import com.fastermaker.common.result.PageResult;
import com.fastermaker.common.result.Result;
import com.fastermaker.common.validator.UpdateGroup;
import com.fastermaker.enums.LogModuleEnum;
import com.fastermaker.modules.system.model.form.RoleForm;
import com.fastermaker.modules.system.model.query.RolePageQuery;
import com.fastermaker.modules.system.model.vo.RolePageVO;
import com.fastermaker.plugin.syslog.annotation.LogAnnotation;
import com.fastermaker.modules.system.service.SysRoleService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;


/**
 * 角色控制层
 *
 *
 *
 */
@Tag(name = "角色接口")
@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class SysRoleController {

    private final SysRoleService roleService;

    @Operation(summary = "角色分页列表")
    @PostMapping("/listPage")
    @PreAuthorize("@permission.hasPerm('sys:role:list')")
    @LogAnnotation( value = "角色分页列表",module = LogModuleEnum.ROLE)
    public PageResult<RolePageVO> listPage(
            @RequestBody RolePageQuery queryParams
    ) {
        Page<RolePageVO> result = roleService.listPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "角色下拉列表")
    @GetMapping("/listOptions")
    public Result<List<Option>> listOptions() {
        List<Option> list = roleService.listOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增角色")
    @PostMapping("/save")
    @PreAuthorize("@permission.hasPerm('sys:role:save')")
    @LogAnnotation( value = "新增角色",module = LogModuleEnum.ROLE)
    public Result save(@Valid @RequestBody RoleForm roleForm) {
        boolean result = roleService.save(roleForm);
        return Result.judge(result);
    }

    @Operation(summary = "角色表单数据")
    @GetMapping("/getFormData/{roleId}")
    @PreAuthorize("@permission.hasPerm('sys:role:list')")
    @LogAnnotation( value = "角色表单数据",module = LogModuleEnum.ROLE)
    public Result<RoleForm> getFormData(
            @Parameter(description = "角色ID") @PathVariable Long roleId
    ) {
        RoleForm roleForm = roleService.getFormData(roleId);
        return Result.success(roleForm);
    }

    @Operation(summary = "修改角色")
    @PutMapping(value = "/update")
    @PreAuthorize("@permission.hasPerm('sys:role:update')")
    @LogAnnotation( value = "修改角色",module = LogModuleEnum.ROLE)
    public Result update( @Validated(UpdateGroup.class) @RequestBody RoleForm roleForm) {
        boolean result = roleService.save(roleForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/delete/{ids}")
    @PreAuthorize("@permission.hasPerm('sys:role:delete')")
    @LogAnnotation( value = "删除角色",module = LogModuleEnum.ROLE)
    public Result delete(
            @Parameter(description = "删除角色，多个以英文逗号(,)拼接") @PathVariable String ids
    ) {
        boolean result = roleService.delete(ids);
        return Result.judge(result);
    }

    @Operation(summary = "修改角色状态")
    @PutMapping(value = "/updateStatus")
    @LogAnnotation( value = "修改角色状态",module = LogModuleEnum.ROLE)
    public Result updateStatus(
            @Parameter(description = "角色ID") @RequestParam Long roleId,
            @Parameter(description = "状态(1:启用;0:禁用)") @RequestParam Integer status
    ) {
        boolean result = roleService.updateStatus(roleId, status);
        return Result.judge(result);
    }

    @Operation(summary = "获取角色的菜单ID集合")
    @GetMapping("/getRoleMenuIds/{roleId}")
    @LogAnnotation( value = "获取角色的菜单ID集合",module = LogModuleEnum.ROLE)
    public Result<List<Long>> getRoleMenuIds(
            @Parameter(description = "角色ID") @PathVariable Long roleId
    ) {
        List<Long> menuIds = roleService.getRoleMenuIds(roleId);
        return Result.success(menuIds);
    }

    @Operation(summary = "分配菜单(包括按钮权限)给角色")
    @PutMapping("/updateRoleMenus/{roleId}")
    @LogAnnotation( value = "分配菜单(包括按钮权限)给角色",module = LogModuleEnum.ROLE)
    public Result updateRoleMenus(
            @PathVariable Long roleId,
            @RequestBody List<Long> menuIds
    ) {
        boolean result = roleService.updateRoleMenus(roleId, menuIds);
        return Result.judge(result);
    }
}
