package com.fastermaker.modules.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.common.result.PageResult;
import com.fastermaker.enums.LogModuleEnum;
import com.fastermaker.modules.system.model.query.LogPageQuery;
import com.fastermaker.modules.system.model.vo.LogPageVO;
import com.fastermaker.modules.system.service.SysLogService;
import com.fastermaker.plugin.syslog.annotation.LogAnnotation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 日志控制层
 *
 *
 *
 */
@Tag(name = "日志接口")
@RestController
@RequestMapping("/api/v1/log")
@RequiredArgsConstructor
public class SysLogController {

    private final SysLogService logService;

    @Operation(summary = "日志分页列表")
    @PostMapping("/listPage")
    @PreAuthorize("@permission.hasPerm('sys:log:list')")
    public PageResult<LogPageVO> listPage(
           @RequestBody LogPageQuery queryParams
    ) {
        Page<LogPageVO> result = logService.listPage(queryParams);
        return PageResult.success(result);
    }

}
