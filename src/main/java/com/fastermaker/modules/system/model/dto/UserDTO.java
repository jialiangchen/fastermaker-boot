package com.fastermaker.modules.system.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 用户信息表 DTO
 *
 * @author fastermaker
 * @since 2024-07-06
 */
@Getter
@Setter
@Schema( description = "用户信息表传输层对象")
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;


private Long id;

        @Schema(description = "用户名")

private String username;

        @Schema(description = "昵称")

private String nickname;

        @Schema(description = "性别((1-男 2-女 0-保密)")

private Boolean gender;

        @Schema(description = "密码")

private String password;

        @Schema(description = "部门ID")

private Integer deptId;

        @Schema(description = "用户头像")

private String avatar;

        @Schema(description = "联系方式")

private String mobile;

        @Schema(description = "状态((1-正常 0-禁用)")

private Boolean status;

        @Schema(description = "用户邮箱")

private String email;

        @Schema(description = "创建时间")

private LocalDateTime createTime;

        @Schema(description = "创建人ID")

private Long createBy;

        @Schema(description = "更新时间")

private LocalDateTime updateTime;

        @Schema(description = "修改人ID")

private Long updateBy;

        @Schema(description = "逻辑删除标识(0-未删除 1-已删除)")

private Boolean deleted;
}
