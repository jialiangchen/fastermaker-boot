package com.fastermaker.modules.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.modules.system.model.entity.SysDict;
import com.fastermaker.modules.system.model.form.DictForm;
import com.fastermaker.modules.system.model.vo.DictPageVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-09T12:03:34+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class DictConverterImpl implements DictConverter {

    @Override
    public Page<DictPageVO> convertToPageVo(Page<SysDict> page) {
        if ( page == null ) {
            return null;
        }

        Page<DictPageVO> page1 = new Page<DictPageVO>();

        page1.setPages( page.getPages() );
        page1.setRecords( sysDictListToDictPageVOList( page.getRecords() ) );
        page1.setTotal( page.getTotal() );
        page1.setSize( page.getSize() );
        page1.setCurrent( page.getCurrent() );

        return page1;
    }

    @Override
    public DictForm convertToForm(SysDict entity) {
        if ( entity == null ) {
            return null;
        }

        DictForm dictForm = new DictForm();

        dictForm.setId( entity.getId() );
        dictForm.setName( entity.getName() );
        dictForm.setCode( entity.getCode() );
        dictForm.setStatus( entity.getStatus() );

        return dictForm;
    }

    @Override
    public SysDict convertToEntity(DictForm entity) {
        if ( entity == null ) {
            return null;
        }

        SysDict sysDict = new SysDict();

        sysDict.setId( entity.getId() );
        sysDict.setName( entity.getName() );
        sysDict.setCode( entity.getCode() );
        sysDict.setStatus( entity.getStatus() );

        return sysDict;
    }

    protected DictPageVO sysDictToDictPageVO(SysDict sysDict) {
        if ( sysDict == null ) {
            return null;
        }

        DictPageVO dictPageVO = new DictPageVO();

        dictPageVO.setId( sysDict.getId() );
        dictPageVO.setName( sysDict.getName() );
        dictPageVO.setCode( sysDict.getCode() );
        dictPageVO.setStatus( sysDict.getStatus() );

        return dictPageVO;
    }

    protected List<DictPageVO> sysDictListToDictPageVOList(List<SysDict> list) {
        if ( list == null ) {
            return null;
        }

        List<DictPageVO> list1 = new ArrayList<DictPageVO>( list.size() );
        for ( SysDict sysDict : list ) {
            list1.add( sysDictToDictPageVO( sysDict ) );
        }

        return list1;
    }
}
