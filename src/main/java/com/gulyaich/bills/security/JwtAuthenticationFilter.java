package com.gulyaich.bills.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenUtils jwtTokenUtils;
    private CustomUserDetailsService customUserDetailsService;
    private String authHeader;
    private String tokenStart;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain filterChain)
            throws ServletException, IOException {
        try {
            final String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && jwtTokenUtils.validateToken(jwt)) {
                final Long userId = jwtTokenUtils.getUserIdFromJWT(jwt);
                final UserDetails userDetails = customUserDetailsService.loadUserById(userId);
                final UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            log.error("Unable to authenticate user", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        final String bearerToken = request.getHeader(authHeader);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenStart + " ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    @Autowired
    public void setJwtTokenUtils(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Autowired
    public void setCustomUserDetailsService(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Autowired
    public void setAuthHeader(@Value("${jwt.header.string}") String authHeader) {
        this.authHeader = authHeader;
    }

    @Autowired
    public void setTokenStart(@Value("${jwt.token.prefix}") String tokenStart) {
        this.tokenStart = tokenStart;
    }
}
