//package com.thoughtworks.training.xueqing.todoservice.security;
//
//import com.google.common.net.HttpHeaders;
//import com.thoughtworks.training.xueqing.todoservice.dto.User;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.thymeleaf.util.StringUtils;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collections;
//
//public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
//        request.getHeader(HttpHeaders.AUTHORIZATION);
//
//        if (!StringUtils.isEmpty(token)) {
//            try {
//                RestTemplate restTemplate = new RestTemplateBuilder.build();
//                ResponseEntity<User> responseEntity = restTemplate.postForEntity(
//                        "http://localhost:8081/api/users/verifications",
//                        token,
//                        User.class
//                );
//                SecurityContextHolder.getContext()
//                        .setAuthentication(
//                                new UsernamePasswordAuthenticationToken(responseEntity.getBody(),
//                                        null,
//                                        Collections.emptyList()));
//
//            } catch (Exception e) {
//                System.out.println("not token");
//                e.printStackTrace();
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
