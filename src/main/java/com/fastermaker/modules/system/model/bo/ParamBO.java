package com.fastermaker.modules.system.model.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

/**
 * 系统参数
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Getter
@Setter
@ToString
public class ParamBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 参数键
     */
    private String paramKey;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人ID
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
