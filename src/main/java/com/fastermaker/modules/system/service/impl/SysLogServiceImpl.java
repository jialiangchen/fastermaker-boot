package com.fastermaker.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastermaker.common.util.DateUtils;
import com.fastermaker.modules.system.mapper.SysLogMapper;
import com.fastermaker.modules.system.model.bo.VisitCount;
import com.fastermaker.modules.system.model.entity.SysLog;
import com.fastermaker.modules.system.model.query.LogPageQuery;
import com.fastermaker.modules.system.model.vo.LogPageVO;
import com.fastermaker.modules.system.model.vo.VisitTrendVO;
import com.fastermaker.modules.system.service.SysLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统日志 服务实现类
 *
 *
 *
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog>
        implements SysLogService {

    /**
     * 获取日志分页列表
     *
     * @param queryParams 查询参数
     * @return
     */
    @Override
    public Page<LogPageVO> listPage(LogPageQuery queryParams) {
        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startDate", "endDate");

        return this.baseMapper.listPage(new Page(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams);
    }

    /**
     * 获取访问趋势
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    @Override
    public VisitTrendVO getVisitTrend(LocalDate startDate, LocalDate endDate) {
        VisitTrendVO visitTrend= new VisitTrendVO();
        List<String> dates = new ArrayList<>();

        // 获取日期范围内的日期
        while (!startDate.isAfter(endDate)) {
            dates.add(startDate.toString());
            startDate = startDate.plusDays(1);
        }
        visitTrend.setDates(dates);

        // 获取访问量和访问 IP 数的统计数据
        List<VisitCount> pvCounts = this.baseMapper.getPvCounts(dates.get(0) + " 00:00:00", dates.get(dates.size() - 1) + " 23:59:59");
        List<VisitCount> ipCounts =  this.baseMapper.getIpCounts(dates.get(0) + " 00:00:00", dates.get(dates.size() - 1) + " 23:59:59");

        // 将统计数据转换为 Map
        Map<String, Integer> pvMap = pvCounts.stream().collect(Collectors.toMap(VisitCount::getDate, VisitCount::getCount));
        Map<String, Integer> ipMap = ipCounts.stream().collect(Collectors.toMap(VisitCount::getDate, VisitCount::getCount));

        // 匹配日期和访问量/访问 IP 数
        List<Integer> pvList = new ArrayList<>();
        List<Integer> ipList = new ArrayList<>();

        for (String date : dates) {
            pvList.add(pvMap.getOrDefault(date, 0));
            ipList.add(ipMap.getOrDefault(date, 0));
        }

        visitTrend.setPvList(pvList);
        visitTrend.setIpList(ipList);

        return visitTrend;
    }
}




