package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.Profesor;
import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.request.ProfesorRequest;
import mx.uady.sicei.repository.ProfesorRepository;
import mx.uady.sicei.repository.TutoriaRepository;

@Service
public class ProfesorService {
    
    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private TutoriaRepository tutoriaRepository;

    public List<Profesor> getProfesores() {

        List<Profesor> profesores = new LinkedList<>();

        profesorRepository.findAll().iterator().forEachRemaining(profesores::add);

        return profesores;
    }

    public List<Profesor> buscarProfesores(String nombre) {
        return profesorRepository.findByNombreContaining(nombre);
    }

    public Profesor crearProfesor(ProfesorRequest request) {
        Profesor profesor = new Profesor();

        profesor.setNombre(request.getNombre());
        profesor.setHoras(request.getHoras());
        profesor = profesorRepository.save(profesor);

        return profesor;
    }

    public Profesor getProfesor(String nombre) {
        return profesorRepository.findByNombre(nombre).get(0);//findByIdSerializable(id).get();
    }

    public Profesor actualizarProfesor(String nombre, ProfesorRequest profesor){
        Profesor profesorAct = getProfesor(nombre);

        Profesor profesorReference = profesorRepository.findByNombre(profesorAct.getNombre()).get(0);
        profesorReference = setProfesorNewValues(profesorReference, profesor);
        return profesorRepository.save(profesorReference);
    }

    private Profesor setProfesorNewValues(Profesor profesorRef,ProfesorRequest profesorEdit){
        profesorRef.setNombre(profesorEdit.getNombre());
        profesorRef.setHoras(profesorEdit.getHoras());
        return profesorRef;
    }

    @Transactional
    public Profesor eliminarProfesor(String nombre){
        Profesor profeEliminar = profesorRepository.findByNombre(nombre).get(0);

        List<Tutoria> tutorias = tutoriaRepository.findByProfesorId(profeEliminar.getId());

        if(!tutorias.isEmpty()){
            throw new NotFoundException("El profesor tiene tutorías activas.");
        }

        profesorRepository.deleteById(profeEliminar.getId());;
        return profeEliminar;
    }
}
