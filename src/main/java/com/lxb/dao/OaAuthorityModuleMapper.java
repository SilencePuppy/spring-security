package com.lxb.dao;

import com.lxb.entity.OaAuthorityModule;
import java.util.List;

public interface OaAuthorityModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaAuthorityModule record);

    OaAuthorityModule selectByPrimaryKey(Integer id);

    List<OaAuthorityModule> selectAll();

    int updateByPrimaryKey(OaAuthorityModule record);
}