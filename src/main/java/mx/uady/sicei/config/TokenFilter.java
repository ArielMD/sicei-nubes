package mx.uady.sicei.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.repository.UsuarioRepository;

@Component
public class TokenFilter extends GenericFilterBean {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        // Authorization: abc
        // Authorization: def
        String authHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION); 
        
        if(authHeader == null || authHeader.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }

        Usuario usuario = usuarioRepository.findByToken(authHeader);

        if (usuario == null) {
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        
        chain.doFilter(request, response);
    }
}