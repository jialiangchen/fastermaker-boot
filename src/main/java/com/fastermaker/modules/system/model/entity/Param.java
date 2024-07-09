package com.fastermaker.modules.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.fastermaker.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统参数实体
 *
 * @author fastermaker
 * @since 2024-07-08
 */
@Getter
@Setter
@ToString
@TableName("sys_param")
public class Param extends BaseEntity {

    private static final long serialVersionUID = 1L;


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





}
