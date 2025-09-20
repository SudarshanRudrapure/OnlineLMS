package com.lms;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        String uri = request.getRequestURI();

        if (uri.endsWith("login.html") || uri.endsWith("register.html") ||
            uri.endsWith("login") || uri.endsWith("register") ||
            uri.contains("assets") || uri.contains("css") ) {
            chain.doFilter(req, res);
        } else {
            if (session != null && session.getAttribute("user") != null) {
                chain.doFilter(req, res);
            } else {
                response.sendRedirect("login.html");
            }
        }
    }
}

