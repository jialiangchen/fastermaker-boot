package com.fastermaker.modules.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fastermaker.modules.system.model.bo.UserBO;
import com.fastermaker.modules.system.model.entity.SysUser;
import com.fastermaker.modules.system.model.form.UserForm;
import com.fastermaker.modules.system.model.vo.UserInfoVO;
import com.fastermaker.modules.system.model.vo.UserPageVO;
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
public class UserConverterImpl implements UserConverter {

    @Override
    public UserPageVO toPageVo(UserBO bo) {
        if ( bo == null ) {
            return null;
        }

        UserPageVO userPageVO = new UserPageVO();

        userPageVO.setId( bo.getId() );
        userPageVO.setUsername( bo.getUsername() );
        userPageVO.setNickname( bo.getNickname() );
        userPageVO.setMobile( bo.getMobile() );
        userPageVO.setAvatar( bo.getAvatar() );
        userPageVO.setEmail( bo.getEmail() );
        userPageVO.setStatus( bo.getStatus() );
        userPageVO.setDeptName( bo.getDeptName() );
        userPageVO.setRoleNames( bo.getRoleNames() );
        userPageVO.setCreateTime( bo.getCreateTime() );

        userPageVO.setGenderLabel( com.fastermaker.common.base.IBaseEnum.getLabelByValue(bo.getGender(), com.fastermaker.enums.GenderEnum.class) );

        return userPageVO;
    }

    @Override
    public Page<UserPageVO> toPageVo(Page<UserBO> bo) {
        if ( bo == null ) {
            return null;
        }

        Page<UserPageVO> page = new Page<UserPageVO>();

        page.setPages( bo.getPages() );
        page.setRecords( userBOListToUserPageVOList( bo.getRecords() ) );
        page.setTotal( bo.getTotal() );
        page.setSize( bo.getSize() );
        page.setCurrent( bo.getCurrent() );

        return page;
    }

    @Override
    public UserForm convertToForm(SysUser entity) {
        if ( entity == null ) {
            return null;
        }

        UserForm userForm = new UserForm();

        userForm.setId( entity.getId() );
        userForm.setUsername( entity.getUsername() );
        userForm.setNickname( entity.getNickname() );
        userForm.setMobile( entity.getMobile() );
        userForm.setGender( entity.getGender() );
        userForm.setAvatar( entity.getAvatar() );
        userForm.setEmail( entity.getEmail() );
        userForm.setStatus( entity.getStatus() );
        userForm.setDeptId( entity.getDeptId() );

        return userForm;
    }

    @Override
    public SysUser convertToEntity(UserForm entity) {
        if ( entity == null ) {
            return null;
        }

        SysUser sysUser = new SysUser();

        sysUser.setId( entity.getId() );
        sysUser.setUsername( entity.getUsername() );
        sysUser.setNickname( entity.getNickname() );
        sysUser.setGender( entity.getGender() );
        sysUser.setDeptId( entity.getDeptId() );
        sysUser.setAvatar( entity.getAvatar() );
        sysUser.setMobile( entity.getMobile() );
        sysUser.setStatus( entity.getStatus() );
        sysUser.setEmail( entity.getEmail() );

        return sysUser;
    }

    @Override
    public UserInfoVO toUserInfoVo(SysUser entity) {
        if ( entity == null ) {
            return null;
        }

        UserInfoVO userInfoVO = new UserInfoVO();

        userInfoVO.setUserId( entity.getId() );
        userInfoVO.setUsername( entity.getUsername() );
        userInfoVO.setNickname( entity.getNickname() );
        userInfoVO.setAvatar( entity.getAvatar() );

        return userInfoVO;
    }

    protected List<UserPageVO> userBOListToUserPageVOList(List<UserBO> list) {
        if ( list == null ) {
            return null;
        }

        List<UserPageVO> list1 = new ArrayList<UserPageVO>( list.size() );
        for ( UserBO userBO : list ) {
            list1.add( toPageVo( userBO ) );
        }

        return list1;
    }
}
