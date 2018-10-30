package es.ua.dlsi.copymus.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JsonAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
 
    @Override
    public void commence
      (HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) 
      throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "API_key realm=\"CopyMus webservices\"");
        PrintWriter writer = response.getWriter();
        writer.println("{\"message\": \"Unauthorized\"}");
    }
 
    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("CopyMus webservices");
        super.afterPropertiesSet();
    }
}