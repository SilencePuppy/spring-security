package com.lxb.security.interceptor;

import com.lxb.service.AuthCache;
import javafx.scene.AmbientLight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.Collection;

/**
 * @author 李晓冰
 * @date 2020年11月10日
 */
@Component("myVoter")
public class MyVoter implements AccessDecisionVoter<FilterInvocation> {
    @Autowired
    AuthCache authCache;

    private PathMatcher matcher = new AntPathMatcher();

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
        String fullRequestUrl = object.getRequestUrl();

        Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();

        for (GrantedAuthority userAuthority : userAuthorities) {
            if (matcher.match(userAuthority.getAuthority(), fullRequestUrl)) {
                return ACCESS_GRANTED;
            }
        }

        return ACCESS_DENIED;
    }
}
