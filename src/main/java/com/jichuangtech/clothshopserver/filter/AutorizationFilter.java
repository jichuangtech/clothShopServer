package com.jichuangtech.clothshopserver.filter;

import com.google.common.collect.Sets;
import com.jichuangtech.clothshopserver.service.SessionService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        filterUri.add("/login");
        filterUri.add("/onlogin");
        filterUri.add("/configuration/ui");
        filterUri.add("/swagger-resources");
        filterUri.add("/webjars/springfox-swagger-ui/lib/underscore-min.map");
        filterUri.add("/swagger-ui.html");
        filterUri.add("/v2/api-docs");
    }

    @Autowired
    private SessionService sessionService;
    @Value("${is_product}")
    private boolean isProdect;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //不是生产环境不开启session验证
        if (!isProdect) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        String remoteHost = req.getRemoteHost();
        if (filterUri.contains(requestURI)) {
            chain.doFilter(request, response);
            return;
        }
        //过滤一些静态资源,如果不用swagger可以考虑注释掉
        if (StringUtils.endsWith(requestURI, "js") || StringUtils.endsWith(requestURI, "css")
                || StringUtils.endsWith(requestURI, "html") || StringUtils.endsWith(requestURI, "png")
                || StringUtils.endsWith(requestURI, "jpg") || StringUtils.endsWith(requestURI, "ttf")) {
            chain.doFilter(request, response);
            return;
        }
        String sessionId = req.getHeader("access_token");
        if (sessionId == null) {
            response.getWriter().write("sessionId param lost");
            return;
        }
        String value = sessionService.get(sessionId);
        if (value != null) {
            chain.doFilter(request, response);
            return;
        }
        response.getWriter().write("valid user");
        LOGGER.info("context path is {},remote host is {}", requestURI, remoteHost);
    }

    @Override
    public void destroy() {

    }
}
