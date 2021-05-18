package mx.uady.sicei.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uady.sicei.model.Equipo;

@Repository
public interface EquipoRepository extends CrudRepository<Equipo, Integer> {

}