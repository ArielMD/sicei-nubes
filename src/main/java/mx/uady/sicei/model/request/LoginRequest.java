package mx.uady.sicei.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginRequest {

    @NotEmpty
    @Size(min = 1, max = 255)
    private String email;
    
    @NotEmpty
    @Size(min = 1, max = 255)
    private String password;


    public LoginRequest() {}

    public LoginRequest(String email,String password){
        this.email = email;
        this.password  = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public LoginRequest email(String email) {
        this.email = email;
        return this;
    }

    public LoginRequest password(String password) {
        this.password = password;
        return this;
    }
}