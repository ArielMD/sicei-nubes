package mx.uady.sicei.service;

import java.io.IOException;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Tutoria;

@Service
public class EmailService {

  @Value("${app.sendgrid.key}")
  private String sendgridKey;

  @Value("${email.sendgrid}")
  private String sendgridEmail;

  @Async
  public void WelcomeEmail(String email, Alumno alumno) throws IOException {
    String mensaje = "Bienvenido a la aplicación sicei-app" + alumno.getNombre();
    sendEMail(email, "Registro de usuario", mensaje);
  }

  @Async
  public void loginAlert(String email, String userAgent) throws IOException {
    String mensaje = "Se ha iniciado sesión en el dispositivo " + userAgent;
    sendEMail(email, "Alerta de incio de sesión", mensaje);
  }

  @Async
  public void tutoriaAlert(String email, Tutoria tutoria) throws IOException {
    String mensaje = "Se ha eliminado la tutoria\n" + "La tutoría a las " + tutoria.getHoras() + " con el "
        + tutoria.getProfesor().getNombre() + " se ha cancelado";
    sendEMail(email, "Tutoria eliminada", mensaje);
  }

  @Async
  public void editAlert(String email, Alumno alumno, Alumno alumnoEditado) throws IOException {
    String inicio =  "Se ha realizado un cambio en la cuenta " + alumnoEditado.getUsuario().getUsuario();
    String cuerpoInicio = "\nde" +
    "\nNombre: " + alumno.getNombre() +
    "\nLicenciatura: " + alumno.getLicenciatura();

    if(alumno.getEquipo() != null) {
      cuerpoInicio = cuerpoInicio + 
      "\nEquipo: " + alumno.getEquipo().getModelo();
    }

    String cuerpoFinal = "\na" +
    "\nNombre: " + alumnoEditado.getNombre() +
    "\nLicenciatura: " + alumnoEditado.getLicenciatura();

    if(alumnoEditado.getEquipo() != null) {
      cuerpoFinal = cuerpoFinal + 
      "\nEquipo: " + alumnoEditado.getEquipo().getModelo();
    }
    sendEMail(email, "Campo Editado", inicio + cuerpoInicio + cuerpoFinal);
  }

  public void sendEMail(String email, String subject, String mensaje) throws IOException {
    Mail mail = prepareMail(email, subject, mensaje);

    SendGrid sg = new SendGrid(sendgridKey);

    Request request = new Request();

    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());

      Response response = sg.api(request);

      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());

    } catch (IOException ex) {
      throw ex;
    }
  }

  private Mail prepareMail(String email, String subject, String mensaje) {
    Email from = new Email(sendgridEmail);
    Content content = new Content("text/plain", mensaje);
    Email to = new Email(email);
    Mail mail = new Mail(from, subject, to, content);
    return mail;
  }
}
