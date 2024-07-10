package com.fastermaker.modules.tool.controller;

import com.fastermaker.enums.LogModuleEnum;
import com.fastermaker.plugin.syslog.annotation.LogAnnotation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fastermaker.common.result.PageResult;
import com.fastermaker.common.result.Result;
import com.fastermaker.modules.tool.model.form.GeneratorForm;
import com.fastermaker.modules.tool.model.query.GeneratorPageQuery;
import com.fastermaker.modules.tool.model.vo.GeneratorPageVO;
import com.fastermaker.modules.tool.service.GeneratorService;
import com.fastermaker.common.validator.UpdateGroup;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.io.IOException;


/**
 * 代码生成 前端控制器
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Tag(name = "代码生成接口")
@RestController
@RequestMapping("/api/v1/generator")
@RequiredArgsConstructor
public class GeneratorController {

    private final GeneratorService generatorService;

    @Operation(summary = "代码生成分页列表")
    @PostMapping("/listPage")
    @PreAuthorize("@permission.hasPerm('tool:generator:list')")
    @LogAnnotation( value = "代码生成分页列表",module = LogModuleEnum.OTHER)
    public PageResult<GeneratorPageVO> listPage(@RequestBody GeneratorPageQuery queryParams) {
        IPage<GeneratorPageVO> result = generatorService.listPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增代码生成")
    @PostMapping("/save")
    @PreAuthorize("@permission.hasPerm('tool:generator:save')")
    @LogAnnotation( value = "新增代码生成",module = LogModuleEnum.OTHER)
    public Result save(@RequestBody @Valid GeneratorForm formData) {
        boolean result = generatorService.save(formData);
        return Result.judge(result);
    }

    @Operation(summary = "代码生成表单数据")
    @GetMapping("/getFormData/{id}")
    @PreAuthorize("@permission.hasPerm('tool:generator:list')")
    @LogAnnotation( value = "代码生成表单数据",module = LogModuleEnum.OTHER)
    public Result<GeneratorForm> getFormData(@Parameter(description = "代码生成ID") @PathVariable Long id) {
        GeneratorForm formData = generatorService.getFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改代码生成")
    @PutMapping(value = "/update")
    @PreAuthorize("@permission.hasPerm('tool:generator:update')")
    @LogAnnotation( value = "修改代码生成",module = LogModuleEnum.OTHER)
    public Result update(@RequestBody @Validated(UpdateGroup.class) GeneratorForm formData) {
        boolean result = generatorService.update(formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除代码生成")
    @DeleteMapping("/delete/{ids}")
    @PreAuthorize("@permission.hasPerm('tool:generator:delete')")
    @LogAnnotation( value = "删除代码生成",module = LogModuleEnum.OTHER)
    public Result delete(@Parameter(description = "代码生成ID，多个以英文逗号(,)分割") @PathVariable String ids) {
        boolean result = generatorService.delete(ids);
        return Result.judge(result);
    }

    @Operation(summary = "下载代码")
    @GetMapping("/download/{id}")
    @LogAnnotation( value = "下载代码",module = LogModuleEnum.OTHER)
    public void download(@Parameter(description = "代码生成ID") @PathVariable String id, HttpServletResponse response) throws IOException {
        byte[] data = generatorService.download(id);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"fastermaker.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
