package mx.uady.sicei.service;
import mx.uady.sicei.exception.NotFoundException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.Equipo;
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.request.EquipoRequest;
import mx.uady.sicei.repository.EquipoRepository;
import mx.uady.sicei.repository.AlumnoRepository;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Equipo> getEquipos() {

        List<Equipo> equipos = new LinkedList<>();
        equipoRepository.findAll().iterator().forEachRemaining(equipos::add);

        return equipos;
    }

    public Equipo getEquipo(Integer id) {
        Optional<Equipo> op = equipoRepository.findById(id);

        if (!op.isPresent()) {
            throw new NotFoundException("No se encontro el equipo");
        }

        return op.get();
    }

    public Equipo crearEquipo(EquipoRequest request) {
        Equipo equipo = new Equipo();
        equipo.setId(request.getId());
        equipo.setModelo(request.getModelo());
        equipo = equipoRepository.save(equipo);

        return equipo;
    }

    public Equipo actualizarEquipo(Integer id, EquipoRequest request) {
        Equipo equipo = getEquipo(id);
        equipo.setId(request.getId());
        equipo.setModelo(request.getModelo());
        equipoRepository.save(equipo);
        return equipo;
    }

    public Equipo agregarAlumno(Integer equipoID, Integer alumnoID){
        Equipo equipo = getEquipo(equipoID);
        Optional<Alumno> op = alumnoRepository.findById(alumnoID);
        equipo.getAlumnos().add(op.get());
        equipoRepository.save(equipo);
        return equipo;
    }

    public Equipo eliminarAlumno(Integer equipoID, Integer alumnoID){
        Equipo equipo = getEquipo(equipoID);
        Optional<Alumno> op = alumnoRepository.findById(alumnoID);
        if (!op.isPresent()) {
            throw new NotFoundException("No se encontro el alumno");
        } else if(!equipo.getAlumnos().contains(op.get())){
            throw new NotFoundException("No esta registrado el alumno para este equipo");
        }
        equipo.getAlumnos().remove(op.get());
        equipoRepository.save(equipo);
        return equipo;
    }

    public void eliminarEquipo(Integer id) {
        Equipo equipo = getEquipo(id);
        List<Alumno> alumnos = equipo.getAlumnos();

        if (!alumnos.isEmpty()) {
            throw new NotFoundException("Aun hay alumnos utilizando el equipo");
        }
        equipoRepository.delete(equipo);
    }

}
