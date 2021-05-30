package mx.uady.sicei.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntity extends RuntimeException{
  public UnprocessableEntity() {
        super("La entidad no puede ser procesada");
    }

  public UnprocessableEntity(String mensaje) {
        super(mensaje);
    }
}
