package com.qflow.Qflow.infra.security;

import com.qflow.Qflow.core.ports.UserRepository;
import com.qflow.Qflow.infra.security.jwt.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtTokenService tokenService;

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("🔵 SecurityFilter - Method: " + request.getMethod() + " | Path: " + request.getRequestURI());
        var token = this.recoverToken(request);
        if (token != null) {
            System.out.println("🔵 Token found: " + token.substring(0, Math.min(20, token.length())) + "...");
            var subject = tokenService.validateToken(token);

            if (subject != null) {
                System.out.println("🟢 Token valid for user: " + subject);
                var user = this.userRepository.findByEmail(subject);

                if (user != null) {
                    System.out.println("🟢 User found: " + user.getEmail() + " | Role: " + user.getRole().getRoleName());
                    var userDetails = new MyUserDetails(user);
                    System.out.println("🟢 Authorities: " + userDetails.getAuthorities());

                    var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("✅ Authentication set successfully");
                } else {
                    System.out.println("🔴 User not found in database");
                }
            } else {
                System.out.println("🔴 Token validation failed");
            }
        } else {
            System.out.println("⚪ No token in request");
        }
        filterChain.doFilter(request, response);
    }

    public String recoverToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }

    @Autowired
    public SecurityFilter(JwtTokenService tokenService, UserRepository userRepository)  {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }
}
