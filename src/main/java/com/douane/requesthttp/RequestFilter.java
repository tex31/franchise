package com.douane.requesthttp;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hasina on 10/25/17.
 */
public class RequestFilter implements Filter {

    private static ThreadLocal<HttpServletRequest> localRequest = new ThreadLocal<HttpServletRequest>();


    public static HttpServletRequest getRequest() {
        return localRequest.get();
    }

    public static HttpSession getSession() {
        HttpServletRequest request = localRequest.get();
        return (request != null) ? request.getSession() : null;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            localRequest.set((HttpServletRequest) servletRequest);
        }

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            localRequest.remove();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}