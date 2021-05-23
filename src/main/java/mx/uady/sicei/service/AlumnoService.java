package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Equipo;
import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.model.request.AlumnoRequest;
import mx.uady.sicei.repository.AlumnoRepository;
import mx.uady.sicei.repository.UsuarioRepository;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Alumno> getAlumnos() {

        List<Alumno> alumnos = new LinkedList<>();

        alumnoRepository.findAll().iterator().forEachRemaining(alumnos::add); // SELECT(id, nombre)

        return alumnos;
    }

    public Alumno getAlumno(Integer id) {
        Optional<Alumno> alumno = alumnoRepository.findById(id);

        if (!alumno.isPresent()) {
            throw new NotFoundException();
        }

        return alumno.get();
    }

    public List<Alumno> buscarAlumnos(String nombre) {
        return alumnoRepository.findByNombreContaining(nombre);
    }

    @Transactional
    public Alumno crearAlumno(AlumnoRequest request) {

        Alumno alumno = new Alumno();

        alumno.setNombre(request.getNombre());
        alumno.setLicenciatura(request.getLicenciatura());

        // if(!validarEquipo(request.getEquipo()).present){
        // throw new NotFoundException()
        // }

        // alumno.setEquipo(request.getEquipo());

        Usuario usuario = new Usuario();

        usuario.setUsuario(request.getCorreo());
        usuario.setPassword("123");

        String token = UUID.randomUUID().toString();
        usuario.setToken(token);
        alumno.setUsuario(usuario);

        usuario = usuarioRepository.save(usuario);
        alumno = alumnoRepository.save(alumno); // INSERT

        return alumno;
    }

    // public Optional validarEquipo(Integer equipoID) {
    // Optional<Equipo> op = equipoRepository.findById(equipoID);
    // return op;
    // }

    @Transactional
    public Alumno actualizarAlumno(Integer id, AlumnoRequest request) {
        // Validar equipo

        Alumno alumnoEncontrado = getAlumno(id);

        alumnoEncontrado.setLicenciatura(request.getLicenciatura());
        alumnoEncontrado.setNombre(request.getNombre());
        // alumnoEncontrado.setEquipo();

        alumnoRepository.save(alumnoEncontrado);

        return alumnoEncontrado;
    }

    @Transactional
    public void eliminarAlumno(Integer id) {
        Usuario usuarioEliminar = getAlumno(id).getUsuario();

        // Validar que no existan tutorias

        usuarioRepository.delete(usuarioEliminar);

        alumnoRepository.deleteById(id);
    }

}
