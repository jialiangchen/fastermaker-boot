package com.fastermaker.modules.tool.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 代码生成 DTO
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Getter
@Setter
@ToString
@Schema( description = "代码生成传输层对象")
public class GeneratorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @NotNull(message = "ID不能为空")
    private Long id;

    @Schema(description = "基础包名")
    @NotBlank(message = "基础包名不能为空")
    private String packageName;

    @Schema(description = "模块名称")
    @NotBlank(message = "模块名称不能为空")
    private String moduleName;

    @Schema(description = "表名称")
    @NotBlank(message = "表名称不能为空")
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
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
