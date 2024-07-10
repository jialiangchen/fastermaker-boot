package com.fastermaker.modules.system.controller;

import com.fastermaker.common.result.Result;
import com.fastermaker.modules.system.model.form.DeptForm;
import com.fastermaker.modules.system.model.query.DeptQuery;
import com.fastermaker.modules.system.model.vo.DeptVO;
import com.fastermaker.plugin.syslog.annotation.LogAnnotation;
import com.fastermaker.enums.LogModuleEnum;
import com.fastermaker.common.model.Option;
import com.fastermaker.modules.system.service.SysDeptService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * 部门控制器
 *
 *
 *
 */
@Tag(name = "部门接口")
@RestController
@RequestMapping("/api/v1/dept")
@RequiredArgsConstructor
public class SysDeptController {

    private final SysDeptService deptService;

    @Operation(summary = "部门列表")
    @PostMapping("listPage")
    @PreAuthorize("@permission.hasPerm('sys:dept:list')")
    @LogAnnotation( value = "部门列表",module = LogModuleEnum.DEPT)
    public Result<List<DeptVO>> listPage(
           @RequestBody DeptQuery queryParams
    ) {
        List<DeptVO> list = deptService.listPage(queryParams);
        return Result.success(list);
    }

    @Operation(summary = "部门下拉列表")
    @GetMapping("/listOptions")
    public Result<List<Option>> listOptions() {
        List<Option> list = deptService.listOptions();
        return Result.success(list);
    }

    @Operation(summary = "新增部门")
    @PostMapping("/save")
    @PreAuthorize("@permission.hasPerm('sys:dept:save')")
    @LogAnnotation( value = "新增部门",module = LogModuleEnum.DEPT)
    public Result save(
            @Valid @RequestBody DeptForm formData
    ) {
        Long id = deptService.save(formData);
        return Result.success(id);
    }

    @Operation(summary = "获取部门表单数据")
    @GetMapping("/getFormData/{deptId}")
    @PreAuthorize("@permission.hasPerm('sys:dept:list')")
    @LogAnnotation( value = "获取部门表单数据",module = LogModuleEnum.DEPT)
    public Result<DeptForm> getFormData(
            @Parameter(description ="部门ID") @PathVariable Long deptId
    ) {
        DeptForm deptForm = deptService.getFormData(deptId);
        return Result.success(deptForm);
    }

    @Operation(summary = "修改部门")
    @PutMapping(value = "/update")
    @PreAuthorize("@permission.hasPerm('sys:dept:update')")
    @LogAnnotation( value = "修改部门",module = LogModuleEnum.DEPT)
    public Result update(
            @Valid @RequestBody DeptForm formData
    ) {
        long deptId = deptService.update(formData.getId(), formData);
        return Result.success(deptId);
    }

    @Operation(summary = "删除部门")
    @DeleteMapping("/delete/{ids}")
    @PreAuthorize("@permission.hasPerm('sys:dept:delete')")
    @LogAnnotation( value = "删除部门",module = LogModuleEnum.DEPT)
    public Result delete(
            @Parameter(description ="部门ID，多个以英文逗号(,)分割") @PathVariable("ids") String ids
    ) {
        boolean result = deptService.deleteByIds(ids);
        return Result.judge(result);
    }

}
