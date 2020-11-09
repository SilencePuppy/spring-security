package com.lxb.dao;

import com.lxb.entity.OaRole;
import java.util.List;

public interface OaRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaRole record);

    OaRole selectByPrimaryKey(Integer id);

    List<OaRole> selectAll();

    int updateByPrimaryKey(OaRole record);
}