package mx.uady.sicei.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tutorias")
public class Tutoria {

  @EmbeddedId
  @ManyToOne(targetEntity = TutoriaLlave.class)
  private TutoriaLlave id;

  @Column(name = "horas")
  private Integer horas;

  public Tutoria() {}

  public void setId(TutoriaLlave id) {
    this.id = id;
  }

  public TutoriaLlave getId() {
    return this.id;
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
      "id_alumno: " +
      this.id.getIdAlumno() +
      "id_profesor: " +
      this.id.getIdProfesor() +
      "horas: " +
      this.horas +
      "}"
    );
  }
}
