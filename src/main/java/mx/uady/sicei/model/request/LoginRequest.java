package mx.uady.sicei.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginRequest {

    @NotEmpty
    @Size(min = 1, max = 255)
    private String matricula;
    
    @NotEmpty
    @Size(min = 1, max = 255)
    private String contrasena;


    public LoginRequest() {}

    public LoginRequest(String matricula,String contrasena){
        this.matricula = matricula;
        this.contrasena  = contrasena;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}