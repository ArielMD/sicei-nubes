package mx.uady.sicei.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

@Embeddable
public class TutoriaLlave {

  @Column(name = "id_alumno")
  private Integer idAlumno;

  @Column(name = "id_profesor")
  private Integer idProfesor;

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
