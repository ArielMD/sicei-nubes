package mx.uady.sicei.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TutoriaRequest {

  @NotNull
  private Integer alumnoId;

  @NotNull
  private Integer profesorId;

  @Max(2)
  @NotNull
  @Positive
  private Integer horas;

  public Integer getAlumnoId() {
    return alumnoId;
  }

  public void setAlumnoId(Integer alumnoId) {
    this.alumnoId = alumnoId;
  }

  public Integer getProfesorId() {
    return this.profesorId;
  }

  public void setProfesorId(Integer profesorId) {
    this.profesorId = profesorId;
  }

  public Integer getHoras() {
    return horas;
  }

  public void setHoras(Integer horas) {
    this.horas = horas;
  }
}
