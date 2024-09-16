//package com.example.jwt_spring_ex.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtService jwtService;
//
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain
//    ) throws ServletException, IOException {
//        System.out.println("man innne do filter eke");
//        final String authHeader = request.getHeader("Authorization");
//
//        System.out.println("authHeader: " + authHeader);
//        final String jwt;
//        final String username;
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            System.out.println("auth header not beer ");
//            filterChain.doFilter(request, response);  //methanin thama ilaga filter walta pass wenne
//            return;
//        }
//        System.out.println("if eken passe continue wenwa");
//        jwt = authHeader.substring(7); //methanadi "Baarer " kalla authheader eken kapala ithuru tika gannawa jwt kiyan ekata
//        username = jwtService.extractUsername(jwt);
//        Map<String, Object> claims = jwtService.getAllClamsByToken(jwt);
//        List<String> roles = Collections.emptyList();
//        if (claims.get("roles") != null) {
//            roles = (List<String>) ((List<?>) claims.get("roles")).stream().toList();
//        }
//        System.out.println("roles " + roles);
//        System.out.println("roles size " + roles.size());
//        List<SimpleGrantedAuthority> authorities = roles
//                .stream()
////                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                .map(role -> new SimpleGrantedAuthority( role))
//                .collect(Collectors.toList());
//
//        System.out.println("user roles set up completed" + username);
//        User user = new User(claims.get("username").toString(), "test123", authorities);
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                user,
//                null,
//                user.getAuthorities()
//        );
//        authToken.setDetails(
//                new WebAuthenticationDetailsSource().buildDetails(request)
//        );
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//        filterChain.doFilter(request, response);
//    }
//}


package lk.purna.healthy_life.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7); // Extract the JWT token from the header
        username = jwtService.extractUsername(jwt);
        Map<String, Object> claims = jwtService.getAllClamsByToken(jwt);

        // Handle "roles" as a single string or as a list
        List<String> roles = new ArrayList<>();
        Object rolesClaim = claims.get("roles");

        if (rolesClaim instanceof String) {
            roles.add((String) rolesClaim); // Single role in string form
        } else if (rolesClaim instanceof List<?>) {
            roles = ((List<?>) rolesClaim).stream()
                    .filter(role -> role instanceof String)
                    .map(role -> (String) role)
                    .collect(Collectors.toList());
        }

        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new) // Create authorities from the roles
                .collect(Collectors.toList());

        User user = new User(username, "test123", authorities); // "test123" is a placeholder password
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities()
        );
        authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
