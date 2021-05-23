package mx.uady.sicei.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.TutoriaLlave;
import mx.uady.sicei.model.request.TutoriaRequest;
import mx.uady.sicei.service.TutoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TutoriaRest {

  @Autowired
  private TutoriaService tutoriaService;

  @GetMapping("/tutorias")
  public ResponseEntity<List<Tutoria>> getTutoria() {
    List<Tutoria> tutorias = tutoriaService.getTutorias();
    return ResponseEntity.ok().body(tutorias);
  }

  @GetMapping("/tutorias/{id}")
  public ResponseEntity<Tutoria> getTutoria(@PathVariable TutoriaLlave id) {
    Tutoria tutoria = tutoriaService.getTutoria(id);

    return ResponseEntity.ok().body(tutoria);
  }

  @PostMapping("/tutorias")
  public ResponseEntity<Tutoria> postTutoria(
    @RequestBody @Valid TutoriaRequest request
  )
    throws URISyntaxException {
    Tutoria tutoriaCreada = tutoriaService.crearTutoria(request);

    return ResponseEntity
      .created(new URI("/tutorias/" + tutoriaCreada.getId()))
      .body(tutoriaCreada);
  }

  @PutMapping("/tutorias/{id}")
  public ResponseEntity<Tutoria> putTutoria(
    @RequestBody @Valid TutoriaRequest request,
    @PathVariable TutoriaLlave id
  ) {
    Tutoria tutoriaActualizada = tutoriaService.actualizarTutoria(id, request);

    return ResponseEntity.ok().body(tutoriaActualizada);
  }

  @DeleteMapping("/tutorias/{id}")
  public ResponseEntity<Void> deleteTutoria(@PathVariable TutoriaLlave id) {
    tutoriaService.eliminarTutoria(id);

    return ResponseEntity.ok().build();
  }
}
