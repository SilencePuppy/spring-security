package com.lxb.dao;

import com.lxb.entity.OaRoleAuthorityModuleMap;
import java.util.List;

public interface OaRoleAuthorityModuleMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaRoleAuthorityModuleMap record);

    OaRoleAuthorityModuleMap selectByPrimaryKey(Integer id);

    List<OaRoleAuthorityModuleMap> selectAll();

    int updateByPrimaryKey(OaRoleAuthorityModuleMap record);
}