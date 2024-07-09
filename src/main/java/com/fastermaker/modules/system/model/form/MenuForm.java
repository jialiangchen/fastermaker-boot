package com.fastermaker.modules.system.model.form;

import com.fastermaker.common.validator.UpdateGroup;
import com.fastermaker.enums.MenuTypeEnum;
import com.fastermaker.common.model.KeyValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 菜单表单对象
 *
 *
 */
@Schema(description = "菜单表单对象")
@Data
public class MenuForm {

    @Schema(description = "菜单ID")
    @NotNull(message = "菜单ID不能为空",groups = UpdateGroup.class)
    private Long id;

    @Schema(description = "父菜单ID")
    private Long parentId;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "菜单类型（1-菜单 2-目录 3-外链 4-按钮）")
    private MenuTypeEnum type;

    @Schema(description = "路由名称")
    private String routeName;

    @Schema(description = "路由路径")
    private String routePath;

    @Schema(description = "组件路径(vue页面完整路径，省略.vue后缀)")
    private String component;

    @Schema(description = "权限标识")
    private String perm;

    @Schema(description = "显示状态(1:显示;0:隐藏)")
    private Integer visible;

    @Schema(description = "排序(数字越小排名越靠前)")
    private Integer sort;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "跳转路径")
    private String redirect;

    @Schema(description = "【菜单】是否开启页面缓存", example = "1")
    private Integer keepAlive;

    @Schema(description = "【目录】只有一个子路由是否始终显示", example = "1")
    private Integer alwaysShow;

    @Schema(description = "路由参数")
    private List<KeyValue> params;

}
