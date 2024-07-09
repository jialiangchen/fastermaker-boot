package com.fastermaker.modules.system.model.dto;


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
 * 系统参数 DTO
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Getter
@Setter
@ToString
@Schema( description = "系统参数传输层对象")
public class ParamDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "主键不能为空")
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
