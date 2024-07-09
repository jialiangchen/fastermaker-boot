package com.fastermaker.modules.tool.model.query;

import com.fastermaker.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 代码生成分页查询对象
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Schema(description ="代码生成分页查询对象")
@Data
public class GeneratorPageQuery extends BasePageQuery {

    @Schema(description="创建时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startTime;

    @Schema(description="创建时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endTime;


    @Schema(description = "基础包名")
    private String packageName;

    @Schema(description = "模块名称")
    private String moduleName;

    @Schema(description = "表名称")
    private String tableName;

    @Schema(description = "表前缀")
    private String tablePrefix;

    @Schema(description = "作者")
    private String author;





}
