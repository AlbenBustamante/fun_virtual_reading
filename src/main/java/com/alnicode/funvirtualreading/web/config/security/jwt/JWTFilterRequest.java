package com.alnicode.funvirtualreading.web.config.security.jwt;

import com.alnicode.funvirtualreading.domain.service.IUserService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter the request to verify if the token is valid and set the authentication.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@Component
public class JWTFilterRequest extends OncePerRequestFilter {
    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer";
    private static final int BEGIN_INDEX = PREFIX.length() + 1;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private IUserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final var authorizationHeader = request.getHeader(HEADER);

        if (isToken(authorizationHeader)) {
            final var token = authorizationHeader.substring(BEGIN_INDEX);
            final var username = jwtUtil.getUsername(token);

            if (isNotAuthenticated(username)) {
                final var userDetails = userService.loadUserByUsername(username);

                if (jwtUtil.isValid(token, userDetails)) {
                    final var authToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isToken(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.startsWith(PREFIX);
    }

    private boolean isNotAuthenticated(String username) {
        return username != null && SecurityContextHolder.getContext().getAuthentication() == null;
    }
}
