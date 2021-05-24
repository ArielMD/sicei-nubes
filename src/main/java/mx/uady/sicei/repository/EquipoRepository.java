package mx.uady.sicei.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import mx.uady.sicei.model.Equipo;

@Repository
public interface EquipoRepository extends CrudRepository<Equipo, Integer> {
    List<Equipo> findAll();
    Equipo findbyId(Integer id);
}