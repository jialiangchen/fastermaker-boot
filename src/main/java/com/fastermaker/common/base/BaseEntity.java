package com.fastermaker.common.base;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体类
 *
 * <p>实体类的基类，包含了实体类的公共属性，如创建时间、更新时间、逻辑删除标识等</p>
 *
 *
 *
 */
@Data
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标识 (0-未删除 1-已删除)
     */
    @TableLogic(value = "0", delval = "1")
    private Integer isDeleted;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 修改人ID
     */
    private Long updateBy;
}
