
package com.project.service_desk.security;


import com.project.service_desk.service.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService service;
    private final UserDetailsService userDetails;

    public JWTAuthenticationFilter(JwtService service , UserDetailsService userDetails){
        this.service = service;
        this.userDetails = userDetails;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if(authorization == null || !(authorization.startsWith("Bearer "))){
            filterChain.doFilter(request,response);
            return;
        }

        String token = authorization.substring(7);
        if(token.isEmpty()){
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            if(authentication == null) {
                String email = service.extractEmail(token);
                UserDetails details = userDetails.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(token1);
            }
        }
        catch (JwtException | UsernameNotFoundException exception){
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        filterChain.doFilter(request, response);
    }

}
