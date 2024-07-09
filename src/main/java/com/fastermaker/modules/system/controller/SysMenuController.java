package com.fastermaker.modules.system.controller;

import com.fastermaker.common.result.Result;
import com.fastermaker.modules.system.model.form.MenuForm;
import com.fastermaker.modules.system.model.query.MenuQuery;
import com.fastermaker.modules.system.model.vo.MenuVO;
import com.fastermaker.modules.system.model.vo.RouteVO;
import com.fastermaker.plugin.syslog.annotation.LogAnnotation;
import com.fastermaker.security.util.SecurityUtils;
import com.fastermaker.common.validator.UpdateGroup;
import com.fastermaker.enums.LogModuleEnum;
import com.fastermaker.common.model.Option;
import com.fastermaker.modules.system.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜单控制层
 *
 *
 *
 */
@Tag(name = "菜单接口")
@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
@Slf4j
public class SysMenuController {

    private final SysMenuService menuService;

    @Operation(summary = "菜单列表")
    @PostMapping("/listPage")
    @PreAuthorize("@permission.hasPerm('sys:menu:list')")
    @LogAnnotation( value = "菜单列表",module = LogModuleEnum.MENU)
    public Result<List<MenuVO>> listPage(@RequestBody MenuQuery queryParams) {
        List<MenuVO> menuList = menuService.listPage(queryParams);
        return Result.success(menuList);
    }

    @Operation(summary = "菜单下拉列表")
    @GetMapping("/listOptions")
    public Result listOptions() {
        List<Option> menus = menuService.listOptions();
        return Result.success(menus);
    }

    @Operation(summary = "路由列表")
    @GetMapping("/listRoutes")
    public Result<List<RouteVO>> listRoutes() {
        Set<String> roles = SecurityUtils.getRoles();
        List<RouteVO> routeList = menuService.listRoutes(roles);
        return Result.success(routeList);
    }

    @Operation(summary = "菜单表单数据")
    @GetMapping("/getFormData/{id}")
    @PreAuthorize("@permission.hasPerm('sys:menu:list')")
    @LogAnnotation( value = "菜单表单数据",module = LogModuleEnum.MENU)
    public Result<MenuForm> getFormData(
            @Parameter(description = "菜单ID") @PathVariable Long id
    ) {
        MenuForm menu = menuService.getFormData(id);
        return Result.success(menu);
    }

    @Operation(summary = "新增菜单")
    @PostMapping("/save")
    @PreAuthorize("@permission.hasPerm('sys:menu:save')")
    @LogAnnotation( value = "新增菜单",module = LogModuleEnum.MENU)
    public Result save(@RequestBody MenuForm menuForm) {
        boolean result = menuService.save(menuForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改菜单")
    @PostMapping(value = "/update")
    @PreAuthorize("@permission.hasPerm('sys:menu:update')")
    @LogAnnotation( value = "修改菜单",module = LogModuleEnum.MENU)
    public Result update(
            @RequestBody @Validated(UpdateGroup.class) MenuForm menuForm
    ) {
        boolean result = menuService.save(menuForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除菜单")
    @GetMapping("/delete/{id}")
    @PreAuthorize("@permission.hasPerm('sys:menu:delete')")
    @LogAnnotation( value = "删除菜单",module = LogModuleEnum.MENU)
    public Result delete(
            @Parameter(description = "菜单ID，多个以英文(,)分割") @PathVariable("id") Long id
    ) {
        boolean result = menuService.delete(id);
        return Result.judge(result);
    }

    @Operation(summary = "修改菜单显示状态")
    @GetMapping("/updateMenuVisible")
    @LogAnnotation( value = "修改菜单显示状态",module = LogModuleEnum.MENU)
    public Result updateMenuVisible(
            @Parameter(description = "菜单ID") @RequestParam Long menuId,
            @Parameter(description = "显示状态(1:显示;0:隐藏)") @RequestParam Integer visible) {
        boolean result = menuService.updateMenuVisible(menuId, visible);
        return Result.judge(result);
    }

}

