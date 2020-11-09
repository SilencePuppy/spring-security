package com.lxb.dao;

import com.lxb.entity.OaRoleAuthorityMap;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OaRoleAuthorityMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaRoleAuthorityMap record);

    OaRoleAuthorityMap selectByPrimaryKey(Integer id);

    List<OaRoleAuthorityMap> selectAll();

    int updateByPrimaryKey(OaRoleAuthorityMap record);
}