package com.fastermaker.modules.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.modules.system.model.bo.VisitCount;
import com.fastermaker.modules.system.model.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fastermaker.modules.system.model.query.LogPageQuery;
import com.fastermaker.modules.system.model.vo.LogPageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 系统日志 数据库访问层
 *
 *
 *
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 获取日志分页列表
     *
     * @param page
     * @param queryParams
     * @return
     */
    Page<LogPageVO> listPage(Page page, LogPageQuery queryParams);

    /**
     * 统计浏览数(PV)
     *
     * @param startDate 开始日期 yyyy-MM-dd
     * @param endDate   结束日期 yyyy-MM-dd
     * @return
     */
    List<VisitCount> getPvCounts(String startDate, String endDate);

    /**
     * 统计IP数
     *
     * @param startDate 开始日期 yyyy-MM-dd
     * @param endDate   结束日期 yyyy-MM-dd
     * @return
     */
    List<VisitCount> getIpCounts(String startDate, String endDate);
}




