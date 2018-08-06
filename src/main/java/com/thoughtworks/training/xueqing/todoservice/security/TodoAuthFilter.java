package com.thoughtworks.training.xueqing.todoservice.security;

import com.google.common.net.HttpHeaders;
import com.thoughtworks.training.xueqing.todoservice.client.UserClient;
import com.thoughtworks.training.xueqing.todoservice.dto.User;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.feign.FeignClient;
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

    @Value("${secretkey}")
    private String secretKey;
    @Autowired
    private UserClient userClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.isEmpty(token)) {
            try {
                System.out.println(token);
                User user = userClient.verifyToken(token);
                System.out.println("------------------"+user);
                if(user!=null){
                    System.out.println("user="+user);
                    SecurityContextHolder.getContext()
                            .setAuthentication(
                                    new UsernamePasswordAuthenticationToken(user,
                                            null,
                                            Collections.emptyList()));
                }else{
                    System.out.println("not found user");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }
}

