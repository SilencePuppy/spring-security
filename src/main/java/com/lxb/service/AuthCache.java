package com.lxb.service;

import com.lxb.dao.OaAccountMapper;
import com.lxb.entity.OaAccount;
import com.lxb.entity.OaAuthority;
import com.lxb.entity.OaRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李晓冰
 * @date 2020年11月09日
 */
@Service
public class AuthCache {

    @Autowired
    OaAccountMapper oaAccountMapper;


    public static Map<Integer, OaAccount> accountInfos = new HashMap<>();

    public static Map<Integer, OaRole> roleInfos = new HashMap<>();

    public static Map<Integer, OaAuthority> authorityInfos = new HashMap<>();


    public static Map<Integer, Integer> accountRoleMap = new HashMap<>();

    public static Map<Integer, Integer> roleAuthorityMap = new HashMap<>();

    static {
        accountInfos.put()
    }

}
