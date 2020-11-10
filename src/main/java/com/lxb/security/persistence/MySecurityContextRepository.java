package com.lxb.security.persistence;

import com.lxb.RedisCache;
import com.lxb.bo.UserDelegator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * @author 李晓冰
 * @date 2020年11月10日
 */
@Slf4j
public class MySecurityContextRepository implements SecurityContextRepository {

    public static final String OA_SECURITY_TOKEN_KEY = "oa_web_token";

    @Override
    /**
     *方法描述：
     *@return: null or SecurityContext
     */
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        return getContextFromRedis(request);
    }

    private SecurityContext getContextFromRedis(HttpServletRequest request) {
        String token = request.getHeader(OA_SECURITY_TOKEN_KEY);
        if (token == null) {
            return null;
        }
        UserDelegator userDelegator = RedisCache.getUserInfo(token);

        if (userDelegator == null) {
            return null;
        }

        return convertUserDelegatorToSecurityContext(userDelegator);
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        if (!(context instanceof SecurityContextImpl)) {
            log.warn("SecurityContext not implement SecurityContextImpl");
            return;
        }
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) context;
        UserDelegator userDelegator = convertSecurityContextToUserDelegator(securityContextImpl);
        if (userDelegator == null) {
            return;
        }
        String token =userDelegator.getDetail().getToken();

        RedisCache.putUserInfo(token,userDelegator);
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return getContextFromRedis(request) != null;
    }

    private SecurityContext convertUserDelegatorToSecurityContext(UserDelegator userDelegator) {
        SecurityContextImpl securityContext = new SecurityContextImpl();
        User user = userDelegator.getUser();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        authenticationToken.setDetails(userDelegator.getDetail());
        securityContext.setAuthentication(authenticationToken);
        return securityContext;
    }

    private UserDelegator convertSecurityContextToUserDelegator(SecurityContextImpl securityContextImpl) {
        Authentication authentication = securityContextImpl.getAuthentication();
        try {
            String account = authentication.getPrincipal().toString();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            UserDelegator.UserDelegatorDetail details = (UserDelegator.UserDelegatorDetail) authentication.getDetails();
            User user = new User(account, null, authorities);
            return new UserDelegator(user,details);
        } catch (Exception e) {
            log.info("securityContextImpl convert to UserDelegator wrong");
            return null;
        }
    }
}
