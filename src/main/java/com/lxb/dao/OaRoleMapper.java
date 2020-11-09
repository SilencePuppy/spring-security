package com.lxb.dao;

import com.lxb.entity.OaRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OaRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaRole record);

    OaRole selectByPrimaryKey(Integer id);

    List<OaRole> selectAll();

    int updateByPrimaryKey(OaRole record);
}