package com.fastermaker.modules.system.model.query;

import com.fastermaker.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 权限分页查询对象
 *
 *
 */
@Data
@Schema
public class PermPageQuery extends BasePageQuery {

    @Schema(description="权限名称")
    private String name;

    @Schema(description="菜单ID")
    private Long menuId;

}
