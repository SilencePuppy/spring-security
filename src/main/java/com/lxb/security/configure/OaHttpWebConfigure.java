package com.lxb.security.configure;

import com.lxb.security.authentication.MyAuthenticationSuccessHandler;
import com.lxb.security.filter.MySecurityContextPersistenceFilter;
import com.lxb.security.interceptor.MyAccessDeniedHandlerImpl;
import com.lxb.security.interceptor.MyVoter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李晓冰
 * @date 2020年11月09日
 */
@Configuration
public class OaHttpWebConfigure extends WebSecurityConfigurerAdapter {

    public OaHttpWebConfigure() {
        super(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new MySecurityContextPersistenceFilter(), LogoutFilter.class)
                .apply(new DefaultLoginPageConfigurer<>())
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .accessDecisionManager(accessDecisionManager())
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .formLogin()
                .successHandler(new MyAuthenticationSuccessHandler())
                .and()
                .logout();
    }


    public AccessDecisionManager accessDecisionManager(){
        MyVoter myVoter = getApplicationContext().getBean("myVoter", MyVoter.class);
        List<AccessDecisionVoter<? extends Object>> voters = new ArrayList<>();
        voters.add(new WebExpressionVoter());
        voters.add(myVoter);
        return  new UnanimousBased(voters);
    }
    public AccessDeniedHandler accessDeniedHandler(){
        return getApplicationContext().getBean("myAccessDeniedHandlerImpl", MyAccessDeniedHandlerImpl.class);
    }
}
