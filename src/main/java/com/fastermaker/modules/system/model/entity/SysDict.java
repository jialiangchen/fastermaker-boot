package com.fastermaker.modules.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fastermaker.common.base.BaseEntity;
import lombok.Data;

/**
 * 字典实体
 *

 */
@Data
public class SysDict extends BaseEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 类型编码
     */
    private String code;

    /**
     * 状态(0:正常;1:禁用)
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

}
