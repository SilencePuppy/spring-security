package com.lxb.security.authentication;

import com.lxb.RedisCache;
import com.lxb.bo.UserDelegator;
import com.lxb.security.persistence.MySecurityContextRepository;
import jdk.nashorn.internal.parser.Token;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 李晓冰
 * @date 2020年11月10日
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDelegator.UserDelegatorDetail delegatorDetail=new UserDelegator.UserDelegatorDetail();
        delegatorDetail.setToken(UUID.randomUUID().toString());
        User user = (User) authentication.getPrincipal();
        UserDelegator userDelegator =new UserDelegator(user,delegatorDetail);
        RedisCache.putUserInfo(delegatorDetail.getToken(),userDelegator);
        response.addCookie(new Cookie(MySecurityContextRepository.OA_SECURITY_TOKEN_KEY,delegatorDetail.getToken()));
    }
}
