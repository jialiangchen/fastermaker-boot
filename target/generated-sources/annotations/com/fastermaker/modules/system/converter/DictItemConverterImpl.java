package com.fastermaker.modules.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.common.model.Option;
import com.fastermaker.modules.system.model.entity.SysDictItem;
import com.fastermaker.modules.system.model.form.DictForm;
import com.fastermaker.modules.system.model.vo.DictPageVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-09T12:03:33+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class DictItemConverterImpl implements DictItemConverter {

    @Override
    public Page<DictPageVO> convertToPageVo(Page<SysDictItem> page) {
        if ( page == null ) {
            return null;
        }

        Page<DictPageVO> page1 = new Page<DictPageVO>();

        page1.setPages( page.getPages() );
        page1.setRecords( sysDictItemListToDictPageVOList( page.getRecords() ) );
        page1.setTotal( page.getTotal() );
        page1.setSize( page.getSize() );
        page1.setCurrent( page.getCurrent() );

        return page1;
    }

    @Override
    public DictForm convertToForm(SysDictItem entity) {
        if ( entity == null ) {
            return null;
        }

        DictForm dictForm = new DictForm();

        dictForm.setId( entity.getId() );
        dictForm.setName( entity.getName() );
        dictForm.setStatus( entity.getStatus() );

        return dictForm;
    }

    @Override
    public SysDictItem convertToEntity(DictForm.DictItem dictFormDictItems) {
        if ( dictFormDictItems == null ) {
            return null;
        }

        SysDictItem sysDictItem = new SysDictItem();

        sysDictItem.setId( dictFormDictItems.getId() );
        sysDictItem.setName( dictFormDictItems.getName() );
        sysDictItem.setValue( dictFormDictItems.getValue() );
        sysDictItem.setSort( dictFormDictItems.getSort() );
        sysDictItem.setStatus( dictFormDictItems.getStatus() );

        return sysDictItem;
    }

    @Override
    public List<SysDictItem> convertToEntity(List<DictForm.DictItem> dictFormDictItems) {
        if ( dictFormDictItems == null ) {
            return null;
        }

        List<SysDictItem> list = new ArrayList<SysDictItem>( dictFormDictItems.size() );
        for ( DictForm.DictItem dictItem : dictFormDictItems ) {
            list.add( convertToEntity( dictItem ) );
        }

        return list;
    }

    @Override
    public DictForm.DictItem convertToDictFormDictItem(SysDictItem entity) {
        if ( entity == null ) {
            return null;
        }

        DictForm.DictItem dictItem = new DictForm.DictItem();

        dictItem.setId( entity.getId() );
        dictItem.setName( entity.getName() );
        dictItem.setValue( entity.getValue() );
        dictItem.setSort( entity.getSort() );
        dictItem.setStatus( entity.getStatus() );

        return dictItem;
    }

    @Override
    public List<DictForm.DictItem> convertToDictFormDictItem(List<SysDictItem> entities) {
        if ( entities == null ) {
            return null;
        }

        List<DictForm.DictItem> list = new ArrayList<DictForm.DictItem>( entities.size() );
        for ( SysDictItem sysDictItem : entities ) {
            list.add( convertToDictFormDictItem( sysDictItem ) );
        }

        return list;
    }

    @Override
    public Option convertToOption(SysDictItem dictItem) {
        if ( dictItem == null ) {
            return null;
        }

        Option option = new Option();

        option.setValue( dictItem.getId() );
        option.setLabel( dictItem.getName() );

        return option;
    }

    @Override
    public List<Option> convertToOption(List<SysDictItem> dictItems) {
        if ( dictItems == null ) {
            return null;
        }

        List<Option> list = new ArrayList<Option>( dictItems.size() );
        for ( SysDictItem sysDictItem : dictItems ) {
            list.add( convertToOption( sysDictItem ) );
        }

        return list;
    }

    protected DictPageVO sysDictItemToDictPageVO(SysDictItem sysDictItem) {
        if ( sysDictItem == null ) {
            return null;
        }

        DictPageVO dictPageVO = new DictPageVO();

        dictPageVO.setId( sysDictItem.getId() );
        dictPageVO.setName( sysDictItem.getName() );
        dictPageVO.setStatus( sysDictItem.getStatus() );

        return dictPageVO;
    }

    protected List<DictPageVO> sysDictItemListToDictPageVOList(List<SysDictItem> list) {
        if ( list == null ) {
            return null;
        }

        List<DictPageVO> list1 = new ArrayList<DictPageVO>( list.size() );
        for ( SysDictItem sysDictItem : list ) {
            list1.add( sysDictItemToDictPageVO( sysDictItem ) );
        }

        return list1;
    }
}
