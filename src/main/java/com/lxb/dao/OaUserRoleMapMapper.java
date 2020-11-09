package com.lxb.dao;

import com.lxb.entity.OaUserRoleMap;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OaUserRoleMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaUserRoleMap record);

    OaUserRoleMap selectByPrimaryKey(Integer id);

    List<OaUserRoleMap> selectAll();

    int updateByPrimaryKey(OaUserRoleMap record);
}