package com.fastermaker.modules.tool.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 代码生成 分页VO
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Data
@Schema( description = "代码生成分页视图对象")
public class GeneratorPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

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

    @Schema(description = "创建人ID")
    private Long createBy;

    @Schema(description = "修改人ID")
    private Long updateBy;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
