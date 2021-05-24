package mx.uady.sicei.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import mx.uady.sicei.model.TutoriaLlave;

public class TutoriaRequest {

  @NotNull
  private TutoriaLlave id;

  @Max(2)
  @NotNull
  @Positive
  private Integer horas;

  public TutoriaLlave getId() {
    return id;
  }

  public void setId(TutoriaLlave id) {
    this.id = id;
  }

  public Integer getHoras() {
    return horas;
  }

  public void setHoras(Integer horas) {
    this.horas = horas;
  }

  @Override
  public String toString() {
    return (
      "{" +
      "id:" +
      "{ 'id_alumno':" +
        this.id.getAlumnoId() +
      "'id_profesor': " +
        this.id.getProfesorId() +
      "}" +
      "'horas': " +
      this.horas +
      "}"
    );
  }
}
