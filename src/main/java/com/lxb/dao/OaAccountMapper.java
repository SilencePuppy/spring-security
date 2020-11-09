package com.lxb.dao;

import com.lxb.entity.OaAccount;
import java.util.List;

public interface OaAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaAccount record);

    OaAccount selectByPrimaryKey(Integer id);

    List<OaAccount> selectAll();

    int updateByPrimaryKey(OaAccount record);
}