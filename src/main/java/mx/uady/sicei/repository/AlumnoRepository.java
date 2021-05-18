package mx.uady.sicei.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uady.sicei.model.Alumno;

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Integer> {

    List<Alumno> findByNombre(String nombre);

    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

    List<Alumno> findByNombreContaining(String nombre); // LIKE %nombre%
}