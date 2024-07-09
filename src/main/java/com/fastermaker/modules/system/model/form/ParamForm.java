package com.fastermaker.modules.system.model.form;

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
 * 系统参数 表单对象
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Getter
@Setter
@ToString
@Schema(description = "系统参数表单对象")
public class ParamForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID不能为空",groups = UpdateGroup.class)
    @Schema(description = "主键")
    private Long id;

    @Schema(description = "参数名称")
    @NotBlank(message = "参数名称不能为空")
    private String paramName;

    @Schema(description = "参数键")
    @NotBlank(message = "参数键不能为空")
    private String paramKey;

    @Schema(description = "参数值")
    @NotBlank(message = "参数值不能为空")
    private String paramValue;

    @Schema(description = "创建人ID")
    private Long createBy;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改人ID")
    private Long updateBy;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
