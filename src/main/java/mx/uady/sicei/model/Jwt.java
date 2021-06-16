package mx.uady.sicei.model;

public class Jwt {
    private String token;

    public Jwt(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
