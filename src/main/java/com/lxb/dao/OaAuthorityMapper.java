package com.lxb.dao;

import com.lxb.entity.OaAuthority;
import java.util.List;

public interface OaAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaAuthority record);

    OaAuthority selectByPrimaryKey(Integer id);

    List<OaAuthority> selectAll();

    int updateByPrimaryKey(OaAuthority record);
}