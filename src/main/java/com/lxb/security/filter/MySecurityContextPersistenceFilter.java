package com.lxb.security.filter;

import com.lxb.security.persistence.MySecurityContextRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 李晓冰
 * @date 2020年11月10日
 */
@Slf4j
public class MySecurityContextPersistenceFilter extends GenericFilterBean{

    static final String FILTER_APPLIED = "__oa_security_scpf_applied";

    private SecurityContextRepository repo;

    public MySecurityContextPersistenceFilter() {
        repo = new MySecurityContextRepository();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (request.getAttribute(FILTER_APPLIED) != null) {
            // ensure that filter is only applied once per request
            chain.doFilter(request, response);
            return;
        }

        final boolean debug = logger.isDebugEnabled();

        request.setAttribute(FILTER_APPLIED, Boolean.TRUE);

        HttpRequestResponseHolder holder = new HttpRequestResponseHolder(request,
                response);
        SecurityContext contextBeforeChainExecution = repo.loadContext(holder);

        if (contextBeforeChainExecution == null) {
            contextBeforeChainExecution = SecurityContextHolder.createEmptyContext();
        }

        try {
            SecurityContextHolder.setContext(contextBeforeChainExecution);

            chain.doFilter(holder.getRequest(), holder.getResponse());

        }finally {
            SecurityContextHolder.clearContext();

            request.removeAttribute(FILTER_APPLIED);
        }
    }
}
