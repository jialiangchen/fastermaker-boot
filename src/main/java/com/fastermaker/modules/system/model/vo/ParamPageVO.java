package com.fastermaker.modules.system.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统参数 分页VO
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Getter
@Setter
@ToString
@Schema( description = "系统参数分页视图对象")
public class ParamPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "参数名称")
    private String paramName;

    @Schema(description = "参数键")
    private String paramKey;

    @Schema(description = "参数值")
    private String paramValue;

    @Schema(description = "创建人ID")
    private Long createBy;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "修改人ID")
    private Long updateBy;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
