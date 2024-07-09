package com.fastermaker.modules.system.model.dto;

import lombok.Data;

import java.util.Set;

/**
 * 用户认证信息
 *
 *
 *
 */
@Data
public class UserAuthInfo {

    private Long userId;

    private String username;

    private String nickname;

    private Long deptId;

    private String password;

    private Integer status;

    private Set<String> roles;

    private Set<String> perms;

    private Integer dataScope;

}
