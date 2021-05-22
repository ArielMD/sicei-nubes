package mx.uady.sicei.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import mx.uady.sicei.model.Licenciatura;

public class AlumnoRequest {

    @NotEmpty
    @Size(min = 1, max = 255)
    private String nombre;

    @NotEmpty
    private Licenciatura licenciatura;

    public AlumnoRequest() {
    }

    public AlumnoRequest(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Licenciatura getLicenciatura() {
        return this.licenciatura;
    }

    public void setLicenciatura(Licenciatura licenciatura) {
        this.licenciatura = licenciatura;
    }

    public AlumnoRequest nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Override
    public String toString() {
        return "{" + " nombre='" + getNombre() + "'" + "}";
    }

}
