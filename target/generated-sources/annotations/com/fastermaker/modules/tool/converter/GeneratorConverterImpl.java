package com.fastermaker.modules.tool.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.modules.tool.model.bo.GeneratorBO;
import com.fastermaker.modules.tool.model.entity.Generator;
import com.fastermaker.modules.tool.model.form.GeneratorForm;
import com.fastermaker.modules.tool.model.vo.GeneratorPageVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-10T09:08:21+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class GeneratorConverterImpl implements GeneratorConverter {

    @Override
    public GeneratorPageVO toPageVo(GeneratorBO bo) {
        if ( bo == null ) {
            return null;
        }

        GeneratorPageVO generatorPageVO = new GeneratorPageVO();

        generatorPageVO.setId( bo.getId() );
        generatorPageVO.setPackageName( bo.getPackageName() );
        generatorPageVO.setModuleName( bo.getModuleName() );
        generatorPageVO.setTableName( bo.getTableName() );
        generatorPageVO.setTablePrefix( bo.getTablePrefix() );
        generatorPageVO.setAuthor( bo.getAuthor() );
        generatorPageVO.setCreateBy( bo.getCreateBy() );
        generatorPageVO.setUpdateBy( bo.getUpdateBy() );
        generatorPageVO.setCreateTime( bo.getCreateTime() );
        generatorPageVO.setUpdateTime( bo.getUpdateTime() );

        return generatorPageVO;
    }

    @Override
    public Page<GeneratorPageVO> toPageVo(Page<GeneratorBO> bo) {
        if ( bo == null ) {
            return null;
        }

        Page<GeneratorPageVO> page = new Page<GeneratorPageVO>();

        page.setPages( bo.getPages() );
        page.setRecords( generatorBOListToGeneratorPageVOList( bo.getRecords() ) );
        page.setTotal( bo.getTotal() );
        page.setSize( bo.getSize() );
        page.setCurrent( bo.getCurrent() );

        return page;
    }

    @Override
    public GeneratorForm convertToForm(Generator entity) {
        if ( entity == null ) {
            return null;
        }

        GeneratorForm generatorForm = new GeneratorForm();

        generatorForm.setId( entity.getId() );
        generatorForm.setPackageName( entity.getPackageName() );
        generatorForm.setModuleName( entity.getModuleName() );
        generatorForm.setTableName( entity.getTableName() );
        generatorForm.setTablePrefix( entity.getTablePrefix() );
        generatorForm.setAuthor( entity.getAuthor() );
        generatorForm.setCreateBy( entity.getCreateBy() );
        generatorForm.setUpdateBy( entity.getUpdateBy() );
        generatorForm.setCreateTime( entity.getCreateTime() );
        generatorForm.setUpdateTime( entity.getUpdateTime() );

        return generatorForm;
    }

    @Override
    public Generator convertToEntity(GeneratorForm entity) {
        if ( entity == null ) {
            return null;
        }

        Generator generator = new Generator();

        generator.setId( entity.getId() );
        generator.setCreateTime( entity.getCreateTime() );
        generator.setUpdateTime( entity.getUpdateTime() );
        generator.setCreateBy( entity.getCreateBy() );
        generator.setUpdateBy( entity.getUpdateBy() );
        generator.setPackageName( entity.getPackageName() );
        generator.setModuleName( entity.getModuleName() );
        generator.setTableName( entity.getTableName() );
        generator.setTablePrefix( entity.getTablePrefix() );
        generator.setAuthor( entity.getAuthor() );

        return generator;
    }

    protected List<GeneratorPageVO> generatorBOListToGeneratorPageVOList(List<GeneratorBO> list) {
        if ( list == null ) {
            return null;
        }

        List<GeneratorPageVO> list1 = new ArrayList<GeneratorPageVO>( list.size() );
        for ( GeneratorBO generatorBO : list ) {
            list1.add( toPageVo( generatorBO ) );
        }

        return list1;
    }
}
