package com.fastermaker.modules.system.controller;

import com.fastermaker.enums.LogModuleEnum;
import com.fastermaker.plugin.syslog.annotation.LogAnnotation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fastermaker.common.result.PageResult;
import com.fastermaker.common.result.Result;
import com.fastermaker.modules.system.model.form.ParamForm;
import com.fastermaker.modules.system.model.query.ParamPageQuery;
import com.fastermaker.modules.system.model.vo.ParamPageVO;
import com.fastermaker.modules.system.service.ParamService;
import com.fastermaker.common.validator.UpdateGroup;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 系统参数 前端控制器
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Tag(name = "系统参数接口")
@RestController
@RequestMapping("/api/v1/param")
@RequiredArgsConstructor
public class ParamController {

        private final ParamService paramService;

        @Operation(summary = "系统参数分页列表")
        @PostMapping("/listPage")
        @PreAuthorize("@permission.hasPerm('system:param:list')")
        @LogAnnotation( value = "系统参数分页列表",module = LogModuleEnum.OTHER)
        public PageResult<ParamPageVO> listPage(@RequestBody ParamPageQuery queryParams) {
            IPage<ParamPageVO> result = paramService.listPage(queryParams);
            return PageResult.success(result);
        }

        @Operation(summary = "新增系统参数")
        @PostMapping("/save")
        @PreAuthorize("@permission.hasPerm('system:param:save')")
        @LogAnnotation( value = "新增系统参数",module = LogModuleEnum.OTHER)
        public Result save(@RequestBody @Valid ParamForm formData ) {
            boolean result = paramService.save(formData);
            return Result.judge(result);
        }

        @Operation(summary = "系统参数表单数据")
        @GetMapping("/getFormData/{id}")
        @PreAuthorize("@permission.hasPerm('system:param:list')")
        @LogAnnotation( value = "系统参数表单数据",module = LogModuleEnum.OTHER)
        public Result<ParamForm> getFormData(@Parameter(description = "系统参数ID") @PathVariable Long id) {
            ParamForm formData = paramService.getFormData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改系统参数")
        @PutMapping(value = "/update")
        @PreAuthorize("@permission.hasPerm('system:param:update')")
        @LogAnnotation( value = "修改系统参数",module = LogModuleEnum.OTHER)
        public Result update(@RequestBody @Validated(UpdateGroup.class) ParamForm formData) {
            boolean result = paramService.update(formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除系统参数")
        @DeleteMapping("/delete/{ids}")
        @PreAuthorize("@permission.hasPerm('system:param:delete')")
        @LogAnnotation( value = "删除系统参数",module = LogModuleEnum.OTHER)
        public Result delete(@Parameter(description = "系统参数ID，多个以英文逗号(,)分割") @PathVariable String ids) {
            boolean result = paramService.delete(ids);
            return Result.judge(result);
        }
}
