package mx.uady.sicei.repository;

import java.util.List;
import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.TutoriaLlave;
import org.springframework.data.repository.CrudRepository;

public interface TutoriaRepository
  extends CrudRepository<Tutoria, TutoriaLlave> {
  List<Tutoria> findAll();
  Tutoria findbyId(TutoriaLlave id);
}
