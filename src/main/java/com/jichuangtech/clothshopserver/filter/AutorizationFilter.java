package com.jichuangtech.clothshopserver.filter;

import com.google.common.collect.Sets;
import com.jichuangtech.clothshopserver.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

/**
 * Created by yangjb on 2017/8/18.
 * 验证身份
 */
@Component("autorizationFilter")
public class AutorizationFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutorizationFilter.class);
    private static final Set<String> filterUri = Sets.newHashSet();

    static {
        //不用进行身份验证的URI
        filterUri.add("/");
        filterUri.add("/onlogin");
        filterUri.add("/swagger-ui.html");
    }

    @Autowired
    private SessionService sessionService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        String remoteHost = req.getRemoteHost();
        if (filterUri.contains(requestURI)) {
            chain.doFilter(request, response);
            return;
        }
        LOGGER.info("context path is {},remote host is {}", requestURI, remoteHost);
    }

    @Override
    public void destroy() {

    }
}
