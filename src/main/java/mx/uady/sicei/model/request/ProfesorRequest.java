package mx.uady.sicei.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ProfesorRequest {
    
    @NotEmpty
    @Size(min = 1, max = 255)
    private String nombre;

    private Integer horas;

    public ProfesorRequest() {
    }

    public ProfesorRequest(String nombre, Integer horas) {
        this.nombre = nombre;
        this.horas = horas;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ProfesorRequest nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Integer getHoras() {
        return this.horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public ProfesorRequest horas(Integer horas){
        this.horas = horas;
        return this;
    }

    @Override
    public String toString() {
        return "{" + " nombre='" + getNombre() + "'" + "}";
    }

}