package mx.uady.sicei.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProfesorRequest {
    
    @NotEmpty
    @Size(min = 1, max = 255)
    private String nombre;

    @NotNull(message = "El profesor debe tener horas de trabajo asignadas.")
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
        if(horas.equals(null)){
            this.horas = 0;
        }else{
            this.horas = horas;
        }
        return this;
    }

    @Override
    public String toString() {
        return "{" + " nombre='" + getNombre() + "'" + "}";
    }

}