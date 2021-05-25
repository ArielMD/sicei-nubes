package mx.uady.sicei.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tutorias")
public class Tutoria {

  @EmbeddedId
  @JsonIgnore
  private TutoriaLlave id;

  @ManyToOne
  @JoinColumn(name="id_alumno", insertable = false, updatable = false)
  private Alumno alumno;

  @ManyToOne
  @JoinColumn(name="id_profesor", insertable = false, updatable = false)
  private Profesor profesor;

  @Column(name = "horas")
  private Integer horas;

  public Tutoria() {}

  public Tutoria(TutoriaLlave id, Integer horas) {
    this.id = id;
    this.horas = horas;
  }

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

  public Alumno getAlumno() {
    return this.alumno;
  }

  public void setAlumno(Alumno alumno) {
    this.alumno = alumno;
  }

  public Profesor getProfesor() {
    return this.profesor;
  }

  public void setProfesor(Profesor profesor) {
    this.profesor = profesor;
  }

  @Override
  public String toString() {
    return ( "{" + this.getAlumno() + "," + this.getProfesor() + "," + this.horas + "}");
  }
}
