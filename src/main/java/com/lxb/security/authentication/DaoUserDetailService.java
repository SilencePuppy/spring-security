package com.lxb.security.authentication;

import com.lxb.dao.OaAccountMapper;
import com.lxb.dao.OaUserRoleMapMapper;
import com.lxb.entity.OaAccount;
import com.lxb.entity.OaUserRoleMap;
import com.lxb.service.AuthCache;
import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 李晓冰
 * @date 2020年11月09日
 */
@Component
public class DaoUserDetailService implements UserDetailsService {

    @Autowired
    OaAccountMapper oaAccountMapper;
    @Autowired
    AuthCache authCache;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("user name not found");
        }

        OaAccount oaAccount = oaAccountMapper.selectByAccountName(username);

        if (oaAccount == null) {
            throw new UsernameNotFoundException("user name not found");
        }

        List<String> accountAuthorities = authCache.getAccountAuthorities(oaAccount.getId());
        List<String> accountRoles = authCache.getAccountRoles(oaAccount.getId());
        accountRoles = accountRoles.stream().map(s -> "ROLE_" + s).collect(Collectors.toList());
        accountAuthorities.addAll(accountRoles);

        List<SimpleGrantedAuthority> collect = accountAuthorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        User user =new User(oaAccount.getAccount(),oaAccount.getPassword(),collect);

        return user;
    }
}
