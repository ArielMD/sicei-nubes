package mx.uady.sicei.model;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Encriptacion {

  static public String encriptar(String password){
    String pwhash = BCrypt.hashpw(password, BCrypt.gensalt());
    return pwhash;
  }

  static public String desencriptar (String pw, String pwHash){
    return "s";
  }
}
