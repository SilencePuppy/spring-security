package com.lxb.service;

import com.lxb.dao.OaAccountMapper;
import com.lxb.entity.OaAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李晓冰
 * @date 2020年11月09日
 */
@Service
public class AccountService {
    @Autowired
    private OaAccountMapper oaAccountMapper;

    public List<OaAccount> selectAll(){
        return oaAccountMapper.selectAll();
    }

}
