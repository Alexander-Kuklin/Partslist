package ru.kuklin.partslist.service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


//@WebFilter(filterName = "MYFilter", urlPatterns = { "/*"})
public class FilterCharset implements Filter {
    private FilterConfig config;

    public void destroy() {
        config = null;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        FilterConfig _filterConfig = null;
        req.setCharacterEncoding(config.getInitParameter("UTF-8"));
        chain.doFilter(req, resp);
    }
}
