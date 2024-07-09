package com.fastermaker.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastermaker.common.constant.SecurityConstants;
import com.fastermaker.config.CacheConfig;
import com.fastermaker.modules.system.mapper.SysRoleMenuMapper;
import com.fastermaker.modules.system.model.bo.RolePermsBO;
import com.fastermaker.modules.system.model.entity.SysRoleMenu;
import com.fastermaker.modules.system.service.SysRoleMenuService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


/**
 * 角色菜单业务实现
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

  private final CacheConfig cacheConfig;

    /**
     * 初始化权限缓存
     */
    @PostConstruct
    public void initRolePermsCache() {
        log.info("初始化权限缓存... ");
        refreshRolePermsCache();
    }

    /**
     * 刷新权限缓存
     */
    @Override
    public void refreshRolePermsCache() {
        // 清理权限缓存
        cacheConfig.removeByPrefix(SecurityConstants.ROLE_PERMS_PREFIX);

        List<RolePermsBO> list = this.baseMapper.getRolePermsList(null);
        if (CollectionUtil.isNotEmpty(list)) {
            list.forEach(item -> {
                String roleCode = item.getRoleCode();
                Set<String> perms = item.getPerms();
                cacheConfig.set(SecurityConstants.ROLE_PERMS_PREFIX+roleCode, perms);
            });
        }
    }

    /**
     * 刷新权限缓存
     */
    @Override
    public void refreshRolePermsCache(String roleCode) {
        // 清理权限缓存
        cacheConfig.remove(SecurityConstants.ROLE_PERMS_PREFIX+roleCode);

        List<RolePermsBO> list = this.baseMapper.getRolePermsList(roleCode);
        if (CollectionUtil.isNotEmpty(list)) {
            RolePermsBO rolePerms = list.get(0);
            if (rolePerms == null) {
                return;
            }

            Set<String> perms = rolePerms.getPerms();
            cacheConfig.set(SecurityConstants.ROLE_PERMS_PREFIX+roleCode, perms);
        }
    }

    /**
     * 刷新权限缓存 (角色编码变更时调用)
     */
    @Override
    public void refreshRolePermsCache(String oldRoleCode, String newRoleCode) {
        // 清理旧角色权限缓存
        cacheConfig.remove(SecurityConstants.ROLE_PERMS_PREFIX+oldRoleCode);

        // 添加新角色权限缓存
        List<RolePermsBO> list = this.baseMapper.getRolePermsList(newRoleCode);
        if (CollectionUtil.isNotEmpty(list)) {
            RolePermsBO rolePerms = list.get(0);
            if (rolePerms == null) {
                return;
            }

            Set<String> perms = rolePerms.getPerms();
            cacheConfig.set(SecurityConstants.ROLE_PERMS_PREFIX+newRoleCode, perms);
        }
    }

    /**
     * 获取角色权限集合
     *
     * @param roles 角色编码集合
     * @return 权限集合
     */
    @Override
    public Set<String> getRolePermsByRoleCodes(Set<String> roles) {
        return this.baseMapper.listRolePerms(roles);
    }

    /**
     * 获取角色拥有的菜单ID集合
     *
     * @param roleId 角色ID
     * @return 菜单ID集合
     */
    @Override
    public List<Long> listMenuIdsByRoleId(Long roleId) {
        return this.baseMapper.listMenuIdsByRoleId(roleId);
    }

}
