package com.lxb.security.authentication;

import com.lxb.dao.OaAccountMapper;
import com.lxb.entity.OaAccount;
import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author 李晓冰
 * @date 2020年11月09日
 */
@Component
public class DaoUserDetailService implements UserDetailsService {

    @Autowired
    OaAccountMapper oaAccountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("user name not found");
        }

        OaAccount oaAccount = oaAccountMapper.selectByAccountName(username);

        if (oaAccount == null) {
            throw new UsernameNotFoundException("user name not found");
        }

        User user =new User(oaAccount.getAccount(),oaAccount.getPassword(),new ArrayList<>());

        return null;
    }
}
