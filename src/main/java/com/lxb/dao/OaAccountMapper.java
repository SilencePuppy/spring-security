package com.lxb.dao;

import com.lxb.entity.OaAccount;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface OaAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaAccount record);

    OaAccount selectByPrimaryKey(Integer id);

    List<OaAccount> selectAll();

    OaAccount selectByAccountName(String account);

    int updateByPrimaryKey(OaAccount record);
}