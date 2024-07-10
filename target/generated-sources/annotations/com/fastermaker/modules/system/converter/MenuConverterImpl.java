package com.fastermaker.modules.system.converter;

import com.fastermaker.modules.system.model.entity.SysMenu;
import com.fastermaker.modules.system.model.form.MenuForm;
import com.fastermaker.modules.system.model.vo.MenuVO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-10T09:08:21+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class MenuConverterImpl implements MenuConverter {

    @Override
    public MenuVO convertToVo(SysMenu entity) {
        if ( entity == null ) {
            return null;
        }

        MenuVO menuVO = new MenuVO();

        menuVO.setId( entity.getId() );
        menuVO.setParentId( entity.getParentId() );
        menuVO.setName( entity.getName() );
        menuVO.setType( entity.getType() );
        menuVO.setRouteName( entity.getRouteName() );
        menuVO.setRoutePath( entity.getRoutePath() );
        menuVO.setComponent( entity.getComponent() );
        menuVO.setSort( entity.getSort() );
        menuVO.setVisible( entity.getVisible() );
        menuVO.setIcon( entity.getIcon() );
        menuVO.setRedirect( entity.getRedirect() );
        menuVO.setPerm( entity.getPerm() );

        return menuVO;
    }

    @Override
    public MenuForm convertToForm(SysMenu entity) {
        if ( entity == null ) {
            return null;
        }

        MenuForm menuForm = new MenuForm();

        menuForm.setId( entity.getId() );
        menuForm.setParentId( entity.getParentId() );
        menuForm.setName( entity.getName() );
        menuForm.setType( entity.getType() );
        menuForm.setRouteName( entity.getRouteName() );
        menuForm.setRoutePath( entity.getRoutePath() );
        menuForm.setComponent( entity.getComponent() );
        menuForm.setPerm( entity.getPerm() );
        menuForm.setVisible( entity.getVisible() );
        menuForm.setSort( entity.getSort() );
        menuForm.setIcon( entity.getIcon() );
        menuForm.setRedirect( entity.getRedirect() );
        menuForm.setKeepAlive( entity.getKeepAlive() );
        menuForm.setAlwaysShow( entity.getAlwaysShow() );

        return menuForm;
    }

    @Override
    public SysMenu convertToEntity(MenuForm menuForm) {
        if ( menuForm == null ) {
            return null;
        }

        SysMenu sysMenu = new SysMenu();

        sysMenu.setId( menuForm.getId() );
        sysMenu.setParentId( menuForm.getParentId() );
        sysMenu.setName( menuForm.getName() );
        sysMenu.setType( menuForm.getType() );
        sysMenu.setRouteName( menuForm.getRouteName() );
        sysMenu.setRoutePath( menuForm.getRoutePath() );
        sysMenu.setComponent( menuForm.getComponent() );
        sysMenu.setPerm( menuForm.getPerm() );
        sysMenu.setVisible( menuForm.getVisible() );
        sysMenu.setSort( menuForm.getSort() );
        sysMenu.setIcon( menuForm.getIcon() );
        sysMenu.setRedirect( menuForm.getRedirect() );
        sysMenu.setKeepAlive( menuForm.getKeepAlive() );
        sysMenu.setAlwaysShow( menuForm.getAlwaysShow() );

        return sysMenu;
    }
}
