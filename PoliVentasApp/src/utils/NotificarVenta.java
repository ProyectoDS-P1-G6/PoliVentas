/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import javafx.scene.control.Alert;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author carlasanchez
 */
public class NotificarVenta {

    private static final String GMAIL_HOST = "smtp.gmail.com";
    public static final String Username = "appespolncml@gmail.com";
    public static final String PassWord = "passComprador";

    public void SendMail(String To, String msg) {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", GMAIL_HOST);
        props.setProperty("mail.user", Username);
        props.setProperty("mail.password", PassWord);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Username, PassWord);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setText("Se ha realizado el pedido del siguiente producto: " + msg);
            message.setSubject("[POLIVENTAS APP] Tienes un nuevo pedido.");
            message.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(To)});
            try (Transport t = session.getTransport("smtp")) {
                t.connect(GMAIL_HOST, Username, PassWord);
                t.sendMessage(message, message.getAllRecipients());
            }
            System.out.println("Se envío la notificación al vendedor ");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notificación enviada exitosamente");
            alert.setHeaderText(null);
            alert.setContentText("El pedido se realizó con éxito");
            alert.showAndWait();
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notificación no enviada");
            alert.setHeaderText(null);
            alert.setContentText("Lo sentimos, hay inconvenientes para realizar el pedido");
            alert.showAndWait();
        }
    }
}
