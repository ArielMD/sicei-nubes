package mx.uady.sicei.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TutoriaLlave implements Serializable {

  @Column(name = "id_alumno")
  private Integer idAlumno;

  @Column(name = "id_profesor")
  private Integer idProfesor;

  public TutoriaLlave() {}

  public TutoriaLlave(Integer idAlumno, Integer idProfesor) {
    this.idAlumno = idAlumno;
    this.idProfesor = idProfesor;
  }

  public void setIdAlumno(Integer idAlumno) {
    this.idAlumno = idAlumno;
  }

  public Integer getIdAlumno() {
    return this.idAlumno;
  }

  public void setIdProfesor(Integer idProfesor) {
    this.idProfesor = idProfesor;
  }

  public Integer getIdProfesor() {
    return this.idProfesor;
  }
}
