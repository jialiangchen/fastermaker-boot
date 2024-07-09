package com.fastermaker.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fastermaker.modules.system.mapper.SysDictItemMapper;
import com.fastermaker.modules.system.model.entity.SysDictItem;
import com.fastermaker.modules.system.service.SysDictItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 数据字典 服务实现类
 *
 */
@Service
@RequiredArgsConstructor
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {

    /**
     * 根据字典ID删除字典项
     *
     * @param dictId 字典ID
     */
    @Override
    public void removeByDictId(Long dictId) {

    }
}




