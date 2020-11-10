package com.lxb.service;

import com.lxb.dao.*;
import com.lxb.entity.*;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.beans.IntrospectionException;
import java.util.*;

/**
 * @author 李晓冰
 * @date 2020年11月09日
 */
@Component
public class AuthCache {

    @Autowired
    OaAccountMapper oaAccountMapper;
    @Autowired
    OaAuthorityMapper oaAuthorityMapper;
    @Autowired
    OaRoleMapper oaRoleMapper;
    @Autowired
    OaUserRoleMapMapper oaUserRoleMapMapper;
    @Autowired
    OaRoleAuthorityMapMapper oaRoleAuthorityMapMapper;


    public static Map<Integer, OaAccount> accountInfos = new HashMap<>();

    public static Map<Integer, OaRole> roleInfos = new HashMap<>();

    public static Map<Integer, OaAuthority> authorityInfos = new HashMap<>();

    public static Map<Integer, List<Integer>> userRoleMap = new HashMap<>();

    public static Map<Integer, List<Integer>> roleAuthorityMap = new HashMap<>();

    @PostConstruct
    public void initMethod(){
        initAccountInfos();
        initRoleInfos();
        initAuthorityInfos();
        initAccountRoleMap();
        initRoleAuthorityMap();
    }

    private void initAccountInfos(){
        List<OaAccount> oaAccounts = oaAccountMapper.selectAll();
        for (OaAccount oaAccount : oaAccounts) {
            accountInfos.put(oaAccount.getId(), oaAccount);
        }
    }

    private void initRoleInfos(){
        List<OaRole> oaRoles = oaRoleMapper.selectAll();
        for (OaRole oaRole : oaRoles) {
            roleInfos.put(oaRole.getId(),oaRole);
        }
    }

    private void initAuthorityInfos(){
        List<OaAuthority> oaAuthorities = oaAuthorityMapper.selectAll();
        for (OaAuthority oaAuthority : oaAuthorities) {
            authorityInfos.put(oaAuthority.getId(),oaAuthority);
        }
    }

    private void initAccountRoleMap(){
        List<OaUserRoleMap> oaUserRoleList = oaUserRoleMapMapper.selectAll();
        for (OaUserRoleMap oaUserRole : oaUserRoleList) {
            List<Integer> roleIds = userRoleMap.computeIfAbsent(oaUserRole.getUser_id(), k -> new ArrayList<>());
            roleIds.add(oaUserRole.getRole_id());
        }
    }

    private void initRoleAuthorityMap(){
        List<OaRoleAuthorityMap> oaRoleAuthorityList = oaRoleAuthorityMapMapper.selectAll();
        for (OaRoleAuthorityMap oaRoleAuthority : oaRoleAuthorityList) {
            List<Integer> authorities = roleAuthorityMap.computeIfAbsent(oaRoleAuthority.getRole_id(), k -> new ArrayList<>());
            authorities.add(oaRoleAuthority.getAuthority_module_id());
        }
    }

    public List<String> getAccountRoles(Integer accountId) {
        List<Integer> roleIds = userRoleMap.get(accountId);
        List<String> roles = new ArrayList<>();
        for (Integer roleId : roleIds) {
            OaRole oaRole = roleInfos.get(roleId);
            roles.add(oaRole.getRole_name());
        }
        return roles;
    }

    public List<String> getAccountAuthorities(Integer accountId){
        List<Integer> roleIds = userRoleMap.get(accountId);
        List<String> authorities =new ArrayList<>();
        for (Integer roleId : roleIds) {
            List<Integer> authorityIds = roleAuthorityMap.get(roleId);
            for (Integer authorityId : authorityIds) {
                OaAuthority oaAuthority = authorityInfos.get(authorityId);
                authorities.add(oaAuthority.getAuthority_url());
            }
        }
        return authorities;
    }

    public List<String> getAccountAuthoriteis(String accountName) {
        Integer accountId = null;
        for (OaAccount value : accountInfos.values()) {
            if (value.getAccount().equals(accountName)) {
                accountId = value.getId();
                break;
            }
        }

        if (accountId ==null){
            return new ArrayList<>();
        }

        return getAccountAuthorities(accountId);
    }
}
