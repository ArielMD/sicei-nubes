package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.exception.UnprocessableEntity;
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Encriptacion;
import mx.uady.sicei.model.Equipo;
import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.model.request.AlumnoRequest;
import mx.uady.sicei.repository.AlumnoRepository;
import mx.uady.sicei.repository.EquipoRepository;
import mx.uady.sicei.repository.TutoriaRepository;
import mx.uady.sicei.repository.UsuarioRepository;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TutoriaRepository tutoriaRepository;

    @Autowired
    private EquipoRepository equipoRepository;

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

        validarUsuario(request);

        Alumno alumno = new Alumno();

        alumno.setNombre(request.getNombre());
        alumno.setLicenciatura(request.getLicenciatura());

        if(request.getEquipo() != null){
            Equipo equipo = validarEquipo(request.getEquipo());
            alumno.setEquipo(equipo);
        }

        Usuario usuario = new Usuario();

        usuario.setUsuario(request.getMatricula());

        String pwHash = Encriptacion.encriptar(request.getContrasena());
        usuario.setPassword(pwHash);

        String token = UUID.randomUUID().toString();
        usuario.setToken(token);
        alumno.setUsuario(usuario);

        usuario = usuarioRepository.save(usuario);
        alumno = alumnoRepository.save(alumno);

        return alumno;
    }

    public Equipo validarEquipo(Integer equipoID) {
        Optional<Equipo> op = equipoRepository.findById(equipoID);

        if (!op.isPresent()) {
            throw new NotFoundException("El equipo ingresado no existe");
        }

        return op.get();
    }

    public void validarUsuario(AlumnoRequest request){
        Usuario usuarioEncontrado = usuarioRepository.findByUsuario(request.getMatricula());

        if(usuarioEncontrado != null){
            throw new UnprocessableEntity("El usuario ya existe");
        }
    }

    @Transactional
    public Alumno actualizarAlumno(Integer id, AlumnoRequest request) {

        Equipo equipo = validarEquipo(request.getEquipo());

        Alumno alumnoEncontrado = getAlumno(id);

        alumnoEncontrado.setLicenciatura(request.getLicenciatura());
        alumnoEncontrado.setNombre(request.getNombre());
        alumnoEncontrado.setEquipo(equipo);

        alumnoRepository.save(alumnoEncontrado);

        return alumnoEncontrado;
    }

    @Transactional
    public void eliminarAlumno(Integer id) {
        Usuario usuarioEliminar = getAlumno(id).getUsuario();

        List<Tutoria> tutorias = tutoriaRepository.findByAlumnoId(id);

        if (!tutorias.isEmpty()) {
            throw new NotFoundException("El Alumno tiene tutorias activas");
        }

        usuarioRepository.delete(usuarioEliminar);

        alumnoRepository.deleteById(id);
    }

}
