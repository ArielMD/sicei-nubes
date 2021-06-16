package mx.uady.sicei.service;

import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.sicei.config.JwtTokenUtil;
import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Encriptacion;
import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.model.request.LoginRequest;
import mx.uady.sicei.model.request.UsuarioRequest;
import mx.uady.sicei.repository.AlumnoRepository;
import mx.uady.sicei.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Alumno usuarioActivo(Usuario user) {
        Alumno usuarioActivo = this.alumnoRepository.findByUsuario_Id(user.getId());

        if (usuarioActivo.equals(null) ) {
            throw new NotFoundException("No se encuentra el Alumno deseado.");
        }

        return usuarioActivo;
    }

    @Transactional
    public String loginUser(LoginRequest request, String userAgent) { //El String que retorna es el token
        Optional<Usuario> oUsuario = usuarioRepository.findByUsuario(request.getUsuario());
        
        if(!oUsuario.isPresent()) {
            throw new NotFoundException("Su usuario es incorrecto");
        }
        
        Usuario usuarioLoggeado = oUsuario.get();
        
        String pwd = request.getContrasena();
        String pwdHash = usuarioLoggeado.getPassword();

        if(!Encriptacion.desencriptar(pwd, pwdHash)) {
            throw new NotFoundException("Su contraseña es incorrecta");
        }

        String token = jwtTokenUtil.generateToken(usuarioLoggeado);
        usuarioLoggeado.setToken(token);

        usuarioRepository.save(usuarioLoggeado);

        try {
            emailService.loginAlert(usuarioLoggeado.getEmail(), userAgent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return token;
    }

    @Transactional
    public void logoutUser(Usuario loggedUser){
        loggedUser.setToken(null);
        usuarioRepository.save(loggedUser);
    }

    @Transactional //(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional // Crear una transaccion
    public Usuario crear(UsuarioRequest request) {
        Usuario usuarioCrear = new Usuario();

        usuarioCrear.setUsuario(request.getUsuario());
        usuarioCrear.setPassword(request.getPassword());

        String token = UUID.randomUUID().toString();
        usuarioCrear.setToken(token);

        Usuario usuarioGuardado = usuarioRepository.save(usuarioCrear);

        Alumno alumno = new Alumno();

        alumno.setNombre(request.getNombre());
        alumno.setUsuario(usuarioGuardado); // Relacionar 2 entidades

        alumno = alumnoRepository.save(alumno);

        return usuarioGuardado;
    }

    public Usuario getUsuario(Integer id) {

        Optional<Usuario> opt = usuarioRepository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }

        throw new NotFoundException();
    }

    public Usuario actualizarUsuario(Integer id, UsuarioRequest request) {
        Usuario usuario = getUsuario(id);

        usuario.setPassword(request.getPassword());
        usuario.setUsuario(request.getUsuario());
        usuarioRepository.save(usuario);

        return usuario;
      }

    public void eliminarUsuario(Integer id) {
        Usuario usuario = getUsuario(id);

        usuarioRepository.delete(usuario);
      }

}