package com.fastermaker.enums;

import com.fastermaker.common.base.IBaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 性别枚举
 *
 *
 *
 */
@Getter
@Schema(enumAsRef = true)
public enum GenderEnum implements IBaseEnum<Integer> {

    MALE(1, "男"),
    FEMALE (2, "女");

    private final Integer value;

    private final String label;

    GenderEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
