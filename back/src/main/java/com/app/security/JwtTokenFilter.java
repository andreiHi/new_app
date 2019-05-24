package com.app.security;

import com.app.security.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 27.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
@Slf4j
public class JwtTokenFilter extends GenericFilterBean {
    
    private final JwtTokenProvider jwtTokenProvider;
    public static final String AUTHORIZATION_HEADER = "Authorization";

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final String token = resolveToken((HttpServletRequest) servletRequest);
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            final Authentication authentication = jwtTokenProvider.getAuthentication(token);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }
    private String resolveToken(HttpServletRequest req) {
        final String bearerToken = req.getHeader(AUTHORIZATION_HEADER);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
