package com.lxb.dao;

import com.lxb.entity.OaAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OaAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaAuthority record);

    OaAuthority selectByPrimaryKey(Integer id);

    List<OaAuthority> selectAll();

    int updateByPrimaryKey(OaAuthority record);
}