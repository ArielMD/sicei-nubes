package mx.uady.sicei.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.Alumno;
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

    public String loginUser(LoginRequest request) { //El String que retorna es el token
        
        Usuario usuarioLogeado = usuarioRepository.findByUsuario(request.getEmail());

        if(usuarioLogeado.equals(null)){
            throw new NotFoundException("Su usuario es incorrecto");
        } else if(!usuarioLogeado.getPassword().equals(request.getPassword())){
            throw new NotFoundException("Su contraseña es incorrecta");
        }

        String token = getToken();

        usuarioLogeado.setUsuario(request.getEmail());
        usuarioLogeado.setPassword(request.getPassword());
        usuarioLogeado.setToken(token);

        usuarioRepository.save(usuarioLogeado);

        return token;
    }

    private String getToken() {
        StringBuilder builder;
        String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";

        builder = new StringBuilder(15);

        for (int m = 0; m < 15; m++){
            int myindex = (int) (alphaNumeric.length() * Math.random());
            builder.append(alphaNumeric.charAt(myindex));
        }

        return builder.toString();
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