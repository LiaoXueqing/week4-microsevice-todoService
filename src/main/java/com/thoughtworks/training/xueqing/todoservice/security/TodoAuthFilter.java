package com.thoughtworks.training.xueqing.todoservice.security;

import com.google.common.net.HttpHeaders;
import com.thoughtworks.training.xueqing.todoservice.dto.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class TodoAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("todo service---"+request);
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.isEmpty(token)) {
            try {
                User user = fromToken(token);
                if (user != null) {
                    SecurityContextHolder.getContext()
                            .setAuthentication(
                                    new UsernamePasswordAuthenticationToken(user,
                                            null,
                                            Collections.emptyList()));
                } else {
                    System.out.println("not found user");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }

    private User fromToken(String token) {
        String[] userInfo = token.split(":");
        return new User(Integer.valueOf(userInfo[0]), userInfo[1], "");
    }
}

