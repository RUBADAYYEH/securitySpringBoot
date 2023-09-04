package com.security.Config;

import com.security.service.JwtService;
import com.security.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { // insure the filter is invoked only once per request
    private final JwtService jwtService;
    private final UserService userService;

    //Retrieve the userEmail by parsing the Bearer Token and
    // subsequently search for the corresponding user information in the database.
    //Verify the authenticity of the JWT.
    //Generate an Authentication object using the provided username and password, and subsequently store it in the SecurityContextHolder.


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader=request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (!StringUtils.hasLength(authHeader) ||
        ! StringUtils.startsWithIgnoreCase(authHeader,"Bearer ")){
            filterChain.doFilter(request,response);
            return ;
        }
        jwt=authHeader.substring(7);
        userEmail=jwtService.extractUserName(jwt);
        if (StringUtils.hasLength(userEmail) && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails = userService.userDetailsService()
                    .loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(context);

            }
        }
        filterChain.doFilter(request,response);
        }


    }

