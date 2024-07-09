package com.fastermaker.modules.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.fastermaker.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 部门 实体
 *
 *
 */
@Getter
@Setter
public class SysDept extends BaseEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门编码
     */
    private String code;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 父节点id路径
     */
    private String treePath;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 状态(1-正常 0-禁用)
     */
    private Integer status;

    /**
     * 创建人 ID
     */
    private Long createBy;

    /**
     * 更新人 ID
     */
    private Long updateBy;

}
