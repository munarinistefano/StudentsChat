/*
 * This project was deploy by ©povodev for WebProgramming course.
 * Was made public for illustrative purposes and for support. 
 * Represents a university examination certificate of the University of Trento 
 * course Computer Science.
 * 
 * No cut-and-paster. Don't be a drunk monkey, learns.
 */

package com.secondoprogetto.controller;

import com.oreilly.servlet.MultipartRequest;
import com.secondoprogetto.model.Utente;
import com.secondoprogetto.services.ServiceUtente;
import com.secondoprogetto.services.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 
 * @author ©Povodev
 */
public class RegistrazioneUtente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrazioneUtente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            PrintWriter out = response.getWriter();

            MultipartRequest multi = new MultipartRequest(request,".",1024*1024*5,"UTF-8");
            
            String username = (String) multi.getParameter("username");
            String password = (String) multi.getParameter("password");
            String confirm_password = (String) multi.getParameter("confirm_password");
            String email = (String) multi.getParameter("email");
            String confirm_email = (String) multi.getParameter("confirm_email");
            String ruolo = (String) multi.getParameter("ruolo");
            
            username = Utils.parsingNomeGruppo(username);
            password = Utils.parsingNomeGruppo(password);
            email = Utils.parsingNomeGruppo(email);
            
            if(password.length()<4 || username.length()<4 || email.length()<4 || password.length()>20 || username.length()>20 || email.length()>20){
                response.sendRedirect("pagenotfound.jsp");
            }
            
            File f = multi.getFile("avatar");
            String avatar = multi.getFilesystemName("avatar");
            String uploadAvatarPathAssoluta = request.getServletContext().getRealPath("/UploadedAvatar");
            
            if(!ServiceUtente.controlNameUser(username) || username.equals("")){
                out.println("<!DOCTYPE html>"
                + "<html>" +
                "    <head>" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                "        <link rel=\"stylesheet\" type=\"text/css\" href= \"css/bootstrap.css\" media=\"screen\">" +
                "        <title>Utente gia presente</title>" +
                "        <meta http-equiv=\"refresh\" content=\"4; url=registrazione.jsp\">  " +
                "    </head>" +
                "    <body>" +
                "        <div class=\"container\" style=\"padding: 50px\">" +
                "            <div class=\"col-lg-6 col-lg-offset-3\">" +
                "                <div class=\"alert alert-danger\"> "
                                        + "<strong>Nome utente gia' registrato!</strong> "
                                        + "<p>State per essere reindirizzati alla pagina di registrazione.</p></div>" +
                "            </div>" +
                "        </div>" +
                "    </body>" +
                "</html>");
            } else if(ServiceUtente.controlloEmail(email)) {
                out.println("<!DOCTYPE html>"
                + "<html>" +
                "    <head>" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                "        <link rel=\"stylesheet\" type=\"text/css\" href= \"css/bootstrap.css\" media=\"screen\">" +
                "        <title>Email gia presente</title>" +
                "        <meta http-equiv=\"refresh\" content=\"4; url=registrazione.jsp\">  " +
                "    </head>" +
                "    <body>" +
                "        <div class=\"container\" style=\"padding: 50px\">" +
                "            <div class=\"col-lg-6 col-lg-offset-3\">" +
                "                <div class=\"alert alert-danger\"> "
                                        + "<strong>Un untente ha gia utilizzato questo indirizzo mail!</strong> "
                                        + "<p>State per essere reindirizzati alla pagina di registrazione.</p></div>" +
                "            </div>" +
                "        </div>" +
                "    </body>" +
                "</html>");
            }
            else{
                
                HttpSession session = request.getSession(true);
                
                Utente ute = new Utente();
                ute.setPassword(password);
                ute.setUsername(username);
                ute.setEmail(email);
                ute.setAvatar(avatar);
                if(ruolo.equals("admin"))
                    ute.setTipo_utente(0);
                else
                    ute.setTipo_utente(1);

                int id = ServiceUtente.inserisciUtente(ute);
                
                ute.setId(id);

                File file = new File(uploadAvatarPathAssoluta);
                if (!file.exists()){
                    file.mkdir();
                }

                if (f!=null) {
                    File fOUT = new File(uploadAvatarPathAssoluta,avatar);
                    FileOutputStream fOS;
                    try (FileInputStream fIS = new FileInputStream(f)) {
                        fOS = new FileOutputStream(fOUT);
                        while (fIS.available()>0)
                            fOS.write(fIS.read());
                    }
                    fOS.close();
                }
                request.setAttribute("utente", ute);
                session.setAttribute("utente", ute);
                
                RequestDispatcher view = request.getRequestDispatcher("registrazioneSuccess.jsp");
                view.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrazioneUtente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
