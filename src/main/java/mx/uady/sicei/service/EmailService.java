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

  public void tutoriaAlert() {

  }

  public void editAlert() {

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
