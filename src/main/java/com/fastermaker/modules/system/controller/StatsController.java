package com.fastermaker.modules.system.controller;

import com.fastermaker.common.result.Result;
import com.fastermaker.enums.LogModuleEnum;
import com.fastermaker.modules.system.model.vo.VisitTrendVO;
import com.fastermaker.modules.system.service.SysLogService;
import com.fastermaker.plugin.syslog.annotation.LogAnnotation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


/**
 * 统计数据控制层
 *
 *
 *
 */
@Tag(name = "统计数据")
@RestController
@RequestMapping("/api/v1/stats")
@RequiredArgsConstructor
public class StatsController {

    private final SysLogService logService;

    @Operation(summary = "获取访问趋势")
    @GetMapping("/visit-trend")
    @LogAnnotation( value = "获取访问趋势",module = LogModuleEnum.OTHER)
    public Result getVisitTrend(
            @Parameter(description = "开始时间", example = "yyyy-MM-dd") @RequestParam String startDate,
            @Parameter(description = "结束时间", example = "yyyy-MM-dd") @RequestParam String endDate
    ) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        VisitTrendVO data = logService.getVisitTrend(start, end);
        return Result.success(data);
    }

}
