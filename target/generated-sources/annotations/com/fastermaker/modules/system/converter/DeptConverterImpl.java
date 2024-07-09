package com.fastermaker.modules.system.converter;

import com.fastermaker.modules.system.model.entity.SysDept;
import com.fastermaker.modules.system.model.form.DeptForm;
import com.fastermaker.modules.system.model.vo.DeptVO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-09T12:03:34+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class DeptConverterImpl implements DeptConverter {

    @Override
    public DeptForm convertToForm(SysDept entity) {
        if ( entity == null ) {
            return null;
        }

        DeptForm deptForm = new DeptForm();

        deptForm.setId( entity.getId() );
        deptForm.setName( entity.getName() );
        deptForm.setCode( entity.getCode() );
        deptForm.setParentId( entity.getParentId() );
        deptForm.setStatus( entity.getStatus() );
        deptForm.setSort( entity.getSort() );

        return deptForm;
    }

    @Override
    public DeptVO convertToVo(SysDept entity) {
        if ( entity == null ) {
            return null;
        }

        DeptVO deptVO = new DeptVO();

        deptVO.setId( entity.getId() );
        deptVO.setParentId( entity.getParentId() );
        deptVO.setName( entity.getName() );
        deptVO.setCode( entity.getCode() );
        deptVO.setSort( entity.getSort() );
        deptVO.setStatus( entity.getStatus() );
        deptVO.setCreateTime( entity.getCreateTime() );
        deptVO.setUpdateTime( entity.getUpdateTime() );

        return deptVO;
    }

    @Override
    public SysDept convertToEntity(DeptForm deptForm) {
        if ( deptForm == null ) {
            return null;
        }

        SysDept sysDept = new SysDept();

        sysDept.setId( deptForm.getId() );
        sysDept.setName( deptForm.getName() );
        sysDept.setCode( deptForm.getCode() );
        sysDept.setParentId( deptForm.getParentId() );
        sysDept.setSort( deptForm.getSort() );
        sysDept.setStatus( deptForm.getStatus() );

        return sysDept;
    }
}
