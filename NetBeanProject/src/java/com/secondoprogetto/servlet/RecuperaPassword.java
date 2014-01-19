/*
 * This project was deploy by ©povodev for WebProgramming course.
 * Was made public for illustrative purposes and for support. 
 * Represents a university examination certificate of the University of Trento 
 * course Computer Science.
 * 
 * No cut-and-paster. Don't be a drunk monkey, learns.
 */
package com.secondoprogetto.servlet;

import com.secondoprogetto.services.ServiceUtente;
import javax.mail.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Security;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author ©Povodev
 */
public class RecuperaPassword extends HttpServlet {

    private String email_ute;
    private String password_ute;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AddressException, MessagingException {        
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
    
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put( "mail.debug", "true" );
        
        
        final String username = "**insertyourgmail@mail**";
        final String password = "**yourPassword**";
        
        
        Session session = Session.getInstance(props, new Authenticator(){
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }});
        
         // — Create a new message –
        Message msg = new MimeMessage(session);
        
        // — Set the FROM and TO fields –
        msg.setFrom(new InternetAddress(username));
        msg.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(email_ute,false));  
        
        msg.setSubject("Recupero Password Progetto Web");
        msg.setText("Questo messaggio le e' stato inviato da Student's Forum"
                + "La sua password è: " + password_ute);
        msg.setSentDate(new Date());
        Transport.send(msg);
        
        response.sendRedirect("recuperoPasswordSuccess.jsp");

    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(RecuperaPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        try {
            if(ServiceUtente.controlloEmail(email)){
                String password = ServiceUtente.passwordUtente(email);
                email_ute=email;
                password_ute=password;
                processRequest(request, response);
            }else{
                out.println("<!DOCTYPE html>"
                + "<html>" +
                "    <head>" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                "        <link rel=\"stylesheet\" type=\"text/css\" href= \"css/bootstrap.css\" media=\"screen\">" +
                "        <title>Utente gia presente</title>" +
                "        <meta http-equiv=\"refresh\" content=\"3; url=recuperaPassword.jsp\">  " +
                "    </head>" +
                "    <body>" +
                "        <div class=\"container\" style=\"padding: 50px\">" +
                "            <div class=\"col-lg-6 col-lg-offset-3\">" +
                "                <div class=\"alert alert-danger\"> "
                                        + "<strong>Email non registrata!</strong> "
                                        + "<p>State per essere reindirizzati alla pagina di recupero.</p></div>" +
                "            </div>" +
                "        </div>" +
                "    </body>" +
                "</html>");
            }
        } catch (SQLException | MessagingException ex) {Logger.getLogger(RecuperaPassword.class.getName()).log(Level.SEVERE, null, ex);}
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
