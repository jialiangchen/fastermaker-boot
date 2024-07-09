package com.fastermaker.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastermaker.modules.system.mapper.SysUserRoleMapper;
import com.fastermaker.modules.system.model.entity.SysUserRole;
import com.fastermaker.modules.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    /**
     * 保存用户角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    @Override
    public boolean save(Long userId, List<Long> roleIds) {

        if (userId == null || CollectionUtil.isEmpty(roleIds)) {
            return false;
        }

        // 用户原角色ID集合
        List<Long> userRoleIds = this.list(new LambdaQueryWrapper<SysUserRole>()
                        .eq(SysUserRole::getUserId, userId))
                .stream()
                .map(SysUserRole::getRoleId)
                .collect(Collectors.toList());

        // 新增用户角色
        List<Long> saveIds;
        if (CollectionUtil.isEmpty(userRoleIds)) {
            saveIds = roleIds;
        } else {
            saveIds = roleIds.stream()
                    .filter(roleId -> !userRoleIds.contains(roleId))
                    .collect(Collectors.toList());
        }

        List<SysUserRole> saves = saveIds
                .stream()
                .map(roleId -> new SysUserRole(userId, roleId))
                .collect(Collectors.toList());
        this.saveBatch(saves);

        // 删除用户角色
        if (CollectionUtil.isNotEmpty(userRoleIds)) {
            List<Long> removeRoleIds = userRoleIds.stream()
                    .filter(roleId -> !roleIds.contains(roleId))
                    .collect(Collectors.toList());

            if (CollectionUtil.isNotEmpty(removeRoleIds)) {
                this.remove(new LambdaQueryWrapper<SysUserRole>()
                        .eq(SysUserRole::getUserId, userId)
                        .in(SysUserRole::getRoleId, removeRoleIds)
                );
            }
        }
        return true;

    }

    /**
     * 判断角色是否存在绑定的用户
     *
     * @param roleId 角色ID
     * @return true：已分配 false：未分配
     */
    @Override
    public boolean hasAssignedUsers(Long roleId) {
        int count = this.baseMapper.countUsersForRole(roleId);
        return count > 0;
    }
}
