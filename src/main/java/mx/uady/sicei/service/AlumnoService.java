package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.request.AlumnoRequest;
import mx.uady.sicei.repository.AlumnoRepository;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

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

    public Alumno crearAlumno(AlumnoRequest request) {
        Alumno alumno = new Alumno();

        alumno.setNombre(request.getNombre());
        alumno.setLicenciatura(request.getLicenciatura());
        alumno = alumnoRepository.save(alumno); // INSERT

        return alumno;
    }

}
