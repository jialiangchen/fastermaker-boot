package com.fastermaker.modules.tool.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.fastermaker.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 代码生成实体
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Getter
@Setter
@ToString
@TableName("tool_generator")
public class Generator extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 基础包名
     */
    private String packageName;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表前缀
     */
    private String tablePrefix;

    /**
     * 作者
     */
    private String author;





}
