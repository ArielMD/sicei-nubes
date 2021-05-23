package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.TutoriaLlave;
import mx.uady.sicei.model.request.TutoriaRequest;
import mx.uady.sicei.repository.TutoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutoriaService {

  @Autowired
  private TutoriaRepository tutoriaRepository;

  public List<Tutoria> getTutorias() {
    List<Tutoria> tutorias = new LinkedList<>();

    tutoriaRepository.findAll().iterator().forEachRemaining(tutorias::add);

    return tutorias;
  }

  public Tutoria getTutoria(TutoriaLlave id) {
    Optional<Tutoria> tutoriaEncontrada = tutoriaRepository.findById(id);

    if (!tutoriaEncontrada.isPresent()) {
      throw new NotFoundException();
    }

    return tutoriaEncontrada.get();
  }

  public List<Tutoria> getTutoriaByIdAlumno(Integer idAlumno) {
    List<Tutoria> tutorias = new LinkedList<>();

    tutorias = tutoriaRepository.findByIdAlumno(idAlumno);

    return tutorias;
  }

  public List<Tutoria> getTutoriaByIdProfesor(Integer idProfesor) {
    List<Tutoria> tutorias = new LinkedList<>();

    tutorias = tutoriaRepository.findByIdProfesor(idProfesor);

    return tutorias;
  }

  @Transactional
  public Tutoria crearTutoria(TutoriaRequest request) {

    Tutoria tutoria = new Tutoria();

    //validar existencia Alumno

    tutoria.setId(request.getId());
    tutoria.setHoras(request.getHoras());
    tutoria = tutoriaRepository.save(tutoria);

    return tutoria;
  }

  @Transactional
  public void eliminarTutoria(TutoriaLlave id) {
    Tutoria tutoriaEliminada = getTutoria(id);

    tutoriaRepository.delete(tutoriaEliminada);
  }

  @Transactional
  public Tutoria actualizarTutoria(TutoriaLlave id, TutoriaRequest request) {
    Tutoria tutoriaEncontrada = getTutoria(id);

    tutoriaEncontrada.setHoras(request.getHoras());
    tutoriaRepository.save(tutoriaEncontrada);

    return tutoriaEncontrada;
  }
}
