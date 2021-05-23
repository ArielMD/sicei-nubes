package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.sicei.model.Profesor;
import mx.uady.sicei.model.request.ProfesorRequest;
import mx.uady.sicei.repository.ProfesorRepository;

@Service
public class ProfesorService {
    
    @Autowired
    private ProfesorRepository profesorRepository;

    @Transactional
    public List<Profesor> getProfesores() {

        List<Profesor> profesores = new LinkedList<>();

        profesorRepository.findAll().iterator().forEachRemaining(profesores::add);

        return profesores;
    }

    public List<Profesor> buscarProfesores(String nombre) {
        return profesorRepository.findByNombreContaining(nombre);
    }

    @Transactional
    public Profesor crearProfesor(ProfesorRequest request) {
        Profesor profesor = new Profesor();

        profesor.setNombre(request.getNombre());
        profesor.setHoras(request.getHoras());
        profesor = profesorRepository.save(profesor);

        return profesor;
    }

    public Profesor actualizarProfesor(String nombre, ProfesorRequest profesor){
        Profesor profeAactualizar = getOneProfesor(nombre);

        Profesor profesorReference = getOneProfesor(profeAactualizar.getNombre());
        profesorReference = setProfesorNewValues(profesorReference, profesor);
        return profesorRepository.save(profesorReference);
    }

    private Profesor getOneProfesor(String nombre) {
        return profesorRepository.findByNombre(nombre).get(0);
    }

    private Profesor setProfesorNewValues(Profesor profesorRef,ProfesorRequest profesorEdit){
        profesorRef.setNombre(profesorEdit.getNombre());
        profesorRef.setHoras(profesorEdit.getHoras());
        return profesorRef;
    }

    public Profesor eliminarProfesor(String nombre){
        Profesor profeEliminar = profesorRepository.findByNombre(nombre).get(0);
        profesorRepository.deleteById(profeEliminar.getId());;
        return profeEliminar;
    }
}
