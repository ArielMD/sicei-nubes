package mx.uady.sicei.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import mx.uady.sicei.model.Licenciatura;

public class AlumnoRequest {

    @NotEmpty
    @Size(min = 1, max = 255)
    private String nombre;

    @NotNull
    private Licenciatura licenciatura;

    private Integer equipo;

    @Size(min = 8, message = "La contraseña debe contener al menos 8 caracteres")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&].*", message = "La contraseña debe contener al memos un letra, un numero y un caracter especial")
    @NotEmpty
    private String contrasena;

    @Email
    @NotEmpty
    private String correo;

    @NotEmpty
    @NotBlank
    @Positive
    private String matricula;

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

    public Integer getEquipo() {
        return this.equipo;
    }

    public void setEquipo(Integer equipo) {
        this.equipo = equipo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "{" + " nombre='" + getNombre() + "'" + "}";
    }

}
