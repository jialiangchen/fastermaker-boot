package com.fastermaker.modules.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.modules.system.model.bo.ParamBO;
import com.fastermaker.modules.system.model.entity.Param;
import com.fastermaker.modules.system.model.form.ParamForm;
import com.fastermaker.modules.system.model.vo.ParamPageVO;
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
public class ParamConverterImpl implements ParamConverter {

    @Override
    public ParamPageVO toPageVo(ParamBO bo) {
        if ( bo == null ) {
            return null;
        }

        ParamPageVO paramPageVO = new ParamPageVO();

        paramPageVO.setId( bo.getId() );
        paramPageVO.setParamName( bo.getParamName() );
        paramPageVO.setParamKey( bo.getParamKey() );
        paramPageVO.setParamValue( bo.getParamValue() );
        paramPageVO.setCreateBy( bo.getCreateBy() );
        paramPageVO.setCreateTime( bo.getCreateTime() );
        paramPageVO.setUpdateBy( bo.getUpdateBy() );
        paramPageVO.setUpdateTime( bo.getUpdateTime() );

        return paramPageVO;
    }

    @Override
    public Page<ParamPageVO> toPageVo(Page<ParamBO> bo) {
        if ( bo == null ) {
            return null;
        }

        Page<ParamPageVO> page = new Page<ParamPageVO>();

        page.setPages( bo.getPages() );
        page.setRecords( paramBOListToParamPageVOList( bo.getRecords() ) );
        page.setTotal( bo.getTotal() );
        page.setSize( bo.getSize() );
        page.setCurrent( bo.getCurrent() );

        return page;
    }

    @Override
    public ParamForm convertToForm(Param entity) {
        if ( entity == null ) {
            return null;
        }

        ParamForm paramForm = new ParamForm();

        paramForm.setId( entity.getId() );
        paramForm.setParamName( entity.getParamName() );
        paramForm.setParamKey( entity.getParamKey() );
        paramForm.setParamValue( entity.getParamValue() );
        paramForm.setCreateBy( entity.getCreateBy() );
        paramForm.setCreateTime( entity.getCreateTime() );
        paramForm.setUpdateBy( entity.getUpdateBy() );
        paramForm.setUpdateTime( entity.getUpdateTime() );

        return paramForm;
    }

    @Override
    public Param convertToEntity(ParamForm entity) {
        if ( entity == null ) {
            return null;
        }

        Param param = new Param();

        param.setId( entity.getId() );
        param.setCreateTime( entity.getCreateTime() );
        param.setUpdateTime( entity.getUpdateTime() );
        param.setCreateBy( entity.getCreateBy() );
        param.setUpdateBy( entity.getUpdateBy() );
        param.setParamName( entity.getParamName() );
        param.setParamKey( entity.getParamKey() );
        param.setParamValue( entity.getParamValue() );

        return param;
    }

    protected List<ParamPageVO> paramBOListToParamPageVOList(List<ParamBO> list) {
        if ( list == null ) {
            return null;
        }

        List<ParamPageVO> list1 = new ArrayList<ParamPageVO>( list.size() );
        for ( ParamBO paramBO : list ) {
            list1.add( toPageVo( paramBO ) );
        }

        return list1;
    }
}
