package com.fastermaker.common.base;

import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * 视图对象基类
 *
 *
 *
 */
@Data
@ToString
public class BaseVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
