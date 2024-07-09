package com.fastermaker.modules.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fastermaker.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色 实体
 *
 */
@Getter
@Setter
public class SysRole extends BaseEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 角色状态(1-正常 0-停用)
     */
    private Integer status;

    /**
     * 数据权限
     */
    private Integer dataScope;

    /**
     * 创建人 ID
     */
    private Long createBy;

    /**
     * 更新人 ID
     */
    private Long updateBy;
}
