package com.fastermaker.modules.system.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Schema(description = "访问趋势VO")
@Getter
@Setter
public class VisitTrendVO {

    @Schema(description = "日期列表")
    private List<String> dates;

    @Schema(description = "浏览量(PV)")
    private List<Integer> pvList;

    @Schema(description = "访客数(UV)")
    private List<Integer> uvList;

    @Schema(description = "IP数")
    private List<Integer> ipList;

}
