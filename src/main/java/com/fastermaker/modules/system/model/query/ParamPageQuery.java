package com.fastermaker.modules.system.model.query;

import com.fastermaker.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 系统参数分页查询对象
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Schema(description ="系统参数分页查询对象")
@Data
public class ParamPageQuery extends BasePageQuery {

    @Schema(description="关键字")
    private String keywords;

    @Schema(description="创建时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startTime;

    @Schema(description="创建时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endTime;


    @Schema(description = "参数名称")
    private String paramName;

    @Schema(description = "参数键")
    private String paramKey;

    @Schema(description = "参数值")
    private String paramValue;





}
