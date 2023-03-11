package com.farenet.nodo.maestro.api.seguridad;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {


    private String token = null;
    private String username = null;
    private static final String SECURITY_TOKEN_KEY = "token";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public TokenAuthenticationProcessingFilter(@Value("/") String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(defaultFilterProcessesUrl));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        List<String> authLst = grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        Map<String, String> authMap = new HashMap<>();
        authMap.put("token", this.token);
        authMap.put("username", this.username);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authMap, null,
                authLst.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        return auth;
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        Authentication authResult;

        if (request.getRequestURI().equals("/login")
                || request.getRequestURI().equals("/loginmaestro")
                || request.getRequestURI().indexOf("/capi/") == 0
                || request.getRequestURI().indexOf("/cierre/") == 0
                || request.getRequestURI().indexOf("/maestro/plantas") == 0) {
            chain.doFilter(request, response);
            return;
        }


        this.token = request.getHeader(SECURITY_TOKEN_KEY);
        this.username = jwtTokenUtil.getUsernameFromToken(this.token);

        try {
            authResult = attemptAuthentication(request, response);
            if (authResult == null) {
                // return immediately as subclass has indicated that it hasn't completed authentication
                return;
            }
        } catch (InternalAuthenticationServiceException failed) {
            logger.error("An internal error occurred while trying to authenticate the user.", failed);
            unsuccessfulAuthentication(request, response, failed);

            return;
        } catch (org.springframework.security.core.AuthenticationException failed) {
            // Authentication failed
            unsuccessfulAuthentication(request, response, failed);

            return;
        }

        // Authentication success
        chain.doFilter(request, response);


        successfulAuthentication(request, response, chain, authResult);
    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);

    }

}