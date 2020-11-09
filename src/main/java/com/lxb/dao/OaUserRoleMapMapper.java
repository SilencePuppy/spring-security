package com.lxb.dao;

import com.lxb.entity.OaUserRoleMap;
import java.util.List;

public interface OaUserRoleMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaUserRoleMap record);

    OaUserRoleMap selectByPrimaryKey(Integer id);

    List<OaUserRoleMap> selectAll();

    int updateByPrimaryKey(OaUserRoleMap record);
}