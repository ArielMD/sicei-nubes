package mx.uady.sicei.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.repository.UsuarioRepository;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired 
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(jwtToken == null || jwtToken.isEmpty() || !jwtTokenUtil.validateStructure(jwtToken)) { //valida que exista el token y sea válido
            chain.doFilter(request, response);
            return;
        }

        String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        Usuario usuario = usuarioRepository.findByUsuario(username);

        if(usuario == null || !jwtTokenUtil.validateToken(jwtToken, usuario)) { //valida que el token corresponda a un usuario
            chain.doFilter(request, response);
            return;
        }

        if(usuario.getToken() == null || usuario.getToken().isEmpty()) { //valida que el token existe para el usuario respectivo
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}
