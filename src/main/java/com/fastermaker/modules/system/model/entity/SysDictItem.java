package com.fastermaker.modules.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;

/**
 * 字典项实体
 *

 */
@Data
public class SysDictItem implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 字典类ID
     */
    private Long dictId;

    /**
     * 字典项名称
     */
    private String name;

    /**
     * 字典项值
     */
    private String value;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态（1-正常，0-禁用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}
