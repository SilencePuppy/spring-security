package com.lxb.dao;

import com.lxb.entity.OaRoleAuthorityMap;
import java.util.List;

public interface OaRoleAuthorityMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaRoleAuthorityMap record);

    OaRoleAuthorityMap selectByPrimaryKey(Integer id);

    List<OaRoleAuthorityMap> selectAll();

    int updateByPrimaryKey(OaRoleAuthorityMap record);
}