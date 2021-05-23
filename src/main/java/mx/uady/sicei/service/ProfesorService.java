package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mx.uady.sicei.model.Profesor;
import mx.uady.sicei.model.request.ProfesorRequest;
import mx.uady.sicei.repository.ProfesorRepository;

@Service
public class ProfesorService {
    
    @Autowired
    private ProfesorRepository profesorRepository;

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

    //@Override
    public Profesor getProfesor(String nombre) {
        return profesorRepository.findByNombre(nombre).get(0);//findByIdSerializable(id).get();
    }

    //@Override
    public Profesor actualizarProfesor(String nombre, ProfesorRequest profesor){
        Profesor profesorAct = getProfesor(nombre);

        Profesor profesorReference = profesorRepository.findByNombre(profesorAct.getNombre()).get(0);
        profesorReference = setProfesorNewValues(profesorReference, profesor);
        return profesorRepository.save(profesorReference);
    }
    /*

    @Override
    public Video editVideo(String id,VideoEditRequest videoEditRequest) {
        Video videoToEdit = getVideo(id);
        videoClasificationService.deleteMultipleVideoClasification(videoToEdit.getId());
        UserUtil.checkUserAuthorization(UserUtil.getActualSession(), videoToEdit);
        //get object refererence and not database entity
        Video videoReference = videoRepository.getOne(videoToEdit.getId());
        videoReference = setVideoReferenceValues(videoReference,videoEditRequest);
        return videoRepository.save(videoReference);
    }*/

    private Profesor setProfesorNewValues(Profesor profesorRef,ProfesorRequest profesorEdit){
        profesorRef.setNombre(profesorEdit.getNombre());
        profesorRef.setHoras(profesorEdit.getHoras());
        return profesorRef;
    }
    /*
    private Video setVideoReferenceValues(Video videoReference,VideoEditRequest videoEditRequest){
        if(videoEditRequest.getDescription() != null) videoReference.setDescription(videoEditRequest.getDescription());
        if(videoEditRequest.getTitulo() != null) videoReference.setTitulo(videoEditRequest.getTitulo());
        if(videoEditRequest.getClasificaciones() != null) videoReference.
                setVideosClasification(videoClasificationService.
                        storeMultipleVideoClasification(videoReference,videoEditRequest.getClasificaciones()));
        return videoReference;
    }*/

}
