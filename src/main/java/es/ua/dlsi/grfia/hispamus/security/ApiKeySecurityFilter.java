package es.ua.dlsi.grfia.hispamus.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class ApiKeySecurityFilter extends BasicAuthenticationFilter {
	
	private final Logger log = LoggerFactory.getLogger(ApiKeySecurityFilter.class);

	private final String AUTH_HEADER = "X-API-KEY";
	
	public ApiKeySecurityFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String token = req.getHeader(AUTH_HEADER);

        if (token == null) {
        	log.info("Access denied without token");
            chain.doFilter(req, res);
            return;
        }

        try {        	
        	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("user", token);
        	Authentication result = getAuthenticationManager().authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(result);
        	log.info("Access granted with token " + token);
        } catch (Exception e) {
        	log.info("Access denied with token " + token);
        }        

        chain.doFilter(req, res);
	}

}
