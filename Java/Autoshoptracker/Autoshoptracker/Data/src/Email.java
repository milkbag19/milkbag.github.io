

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
public class Email {
   
   public Properties props = new Properties();
   public Session session;
   static String username, password;
   public Email(String user, String pass){
      username = user;
      password = pass;
      if(user.contains("gmail")||user.contains("@ocdsb"))
         props.put("mail.smtp.host", "smtp.gmail.com");
      else if(user.contains("@hotmail")||user.contains("@outlook")||user.contains("@live"))
         props.put("mail.smtp.host", "smtp-mail.outlook.com");
      
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.port", "587");
      
   }
   
   public void send(String sentTo, String messages){
      send(sentTo, "No Subject", messages);
   }
   public void send(String sentTo, String subject, String messages){
      session = Session.getInstance(props,
                                    new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }
      });
      try {
         
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(username));
         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(sentTo));
         message.setSubject(subject);
         
         message.setText(messages);
         
         Transport.send(message);
         
         
         
      } catch (MessagingException e) {
         e.printStackTrace(); 
         
      }
   }
   
}
