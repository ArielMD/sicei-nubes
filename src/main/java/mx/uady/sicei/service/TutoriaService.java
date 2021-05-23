package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;
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
    Tutoria tutoriaEncontrada = tutoriaRepository.findbyId(id);

    return tutoriaEncontrada;
  }

  public Tutoria crearTutoria(TutoriaRequest request) {
    Tutoria tutoria = new Tutoria();

    tutoria.setId(request.getId());
    tutoria = tutoriaRepository.save(tutoria);

    return tutoria;
  }

  public void eliminarTutoria(TutoriaLlave id) {
    Tutoria tutoriaEliminada = getTutoria(id);

    tutoriaRepository.delete(tutoriaEliminada);
  }

  public Tutoria actualizarTutoria(TutoriaLlave id, TutoriaRequest request) {
    Tutoria tutoriaEncontrada = getTutoria(id);

    tutoriaEncontrada.setHoras(request.getHoras());
    tutoriaRepository.save(tutoriaEncontrada);

    return tutoriaEncontrada;
  }
}
