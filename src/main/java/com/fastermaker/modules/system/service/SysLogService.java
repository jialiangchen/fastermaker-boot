package com.fastermaker.modules.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.modules.system.model.entity.SysLog;
import com.fastermaker.modules.system.model.query.LogPageQuery;
import com.fastermaker.modules.system.model.vo.LogPageVO;
import com.fastermaker.modules.system.model.vo.VisitTrendVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;

/**
 * 系统日志 服务接口
 *
 *
 *
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 获取日志分页列表
     *
     * @param queryParams 查询参数
     * @return
     */
    Page<LogPageVO> listPage(LogPageQuery queryParams);


    /**
     * 获取访问趋势
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    VisitTrendVO getVisitTrend(LocalDate startDate, LocalDate endDate);
}
