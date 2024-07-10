package com.fastermaker.modules.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.common.model.Option;
import com.fastermaker.common.result.PageResult;
import com.fastermaker.common.result.Result;
import com.fastermaker.common.validator.UpdateGroup;
import com.fastermaker.enums.LogModuleEnum;
import com.fastermaker.modules.system.model.form.DictForm;
import com.fastermaker.modules.system.model.query.DictTypePageQuery;
import com.fastermaker.modules.system.model.vo.DictPageVO;
import com.fastermaker.plugin.syslog.annotation.LogAnnotation;
import com.fastermaker.modules.system.service.SysDictService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典控制层
 *
 *
 *
 */
@Tag(name = "字典接口")
@RestController
@RequestMapping("/api/v1/dict")
@RequiredArgsConstructor
public class SysDictController {

    private final SysDictService dictService;

    @Operation(summary = "字典分页列表")
    @PostMapping("/listPage")
    @PreAuthorize("@permission.hasPerm('sys:dict:list')")
    @LogAnnotation( value = "字典分页列表",module = LogModuleEnum.DICT)
    public PageResult<DictPageVO> listPage(
        @RequestBody DictTypePageQuery queryParams
    ) {
        Page<DictPageVO> result = dictService.listPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "字典数据项列表")
    @GetMapping("/listOptions/{code}")
    public Result<List<Option>> listOptions(
            @Parameter(description = "字典编码") @PathVariable String code
    ) {
        List<Option> options = dictService.listOptions(code);
        return Result.success(options);
    }

    @Operation(summary = "字典表单")
    @GetMapping("/getFormData/{id}")
    @PreAuthorize("@permission.hasPerm('sys:dict:list')")
    @LogAnnotation( value = "字典表单",module = LogModuleEnum.DICT)
    public Result<DictForm> getFormData(
            @Parameter(description = "字典ID") @PathVariable Long id
    ) {
        DictForm formData = dictService.getFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "新增字典")
    @PostMapping("/save")
    @PreAuthorize("@permission.hasPerm('sys:dict:save')")
    @LogAnnotation( value = "新增字典",module = LogModuleEnum.DICT)
    public Result save(@RequestBody DictForm formData) {
        boolean result = dictService.save(formData);
        return Result.judge(result);
    }

    @Operation(summary = "修改字典")
    @PutMapping("/update")
    @PreAuthorize("@permission.hasPerm('sys:dict:update')")
    @LogAnnotation( value = "修改字典",module = LogModuleEnum.DICT)
    public Result update(
            @RequestBody @Validated(UpdateGroup.class)  DictForm dictForm
    ) {
        boolean status = dictService.update(dictForm);
        return Result.judge(status);
    }

    @Operation(summary = "删除字典")
    @DeleteMapping("/delete/{ids}")
    @PreAuthorize("@permission.hasPerm('sys:dict:delete')")
    @LogAnnotation( value = "删除字典",module = LogModuleEnum.DICT)
    public Result delete(
            @Parameter(description = "字典ID，多个以英文逗号(,)拼接") @PathVariable String ids
    ) {
        dictService.delete(ids);
        return Result.success();
    }


}
