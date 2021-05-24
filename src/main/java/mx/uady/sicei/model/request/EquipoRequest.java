package mx.uady.sicei.model.request;

import mx.uady.sicei.model.Alumno;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class EquipoRequest {

    @NotNull
    private Integer id;

    @NotNull
    @Size(min = 1, max = 255)
    private String modelo;

    private List<Alumno> alumnos;

    public EquipoRequest() {
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" +  ", modelo='" + getModelo() + "'" + "}";
    }

}