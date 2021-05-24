package mx.uady.sicei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TutoriaLlave implements Serializable {

  @Column(name = "id_alumno")
  private Integer alumnoId;

  @Column(name = "id_profesor")
  private Integer profesorId;

  public TutoriaLlave() {}

  public TutoriaLlave(Integer alumnoId, Integer profesorId) {
    this.alumnoId = alumnoId;
    this.profesorId = profesorId;
  }

  public void setAlumnoId(Integer alumnoId) {
    this.alumnoId = alumnoId;
  }

  public Integer getAlumnoId() {
    return this.alumnoId;
  }

  public void setProfesorId(Integer profesorId) {
    this.profesorId = profesorId;
  }

  public Integer getProfesorId() {
    return this.profesorId;
  }
}
