package com.fastermaker.modules.tool.model.form;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fastermaker.common.validator.UpdateGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 代码生成 表单对象
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Getter
@Setter
@ToString
@Schema(description = "代码生成表单对象")
public class GeneratorForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID不能为空",groups = UpdateGroup.class)
    @Schema(description = "ID")
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
