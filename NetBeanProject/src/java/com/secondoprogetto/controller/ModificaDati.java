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
public class ModificaDati extends HttpServlet {

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
            Logger.getLogger(ModificaDati.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
       
        HttpSession session = request.getSession();
        Utente ute = (Utente) session.getAttribute("utente");
        PrintWriter out = response.getWriter();
        
        MultipartRequest multi = new MultipartRequest(request,".",1024*1024*5,"UTF-8");

        String newPassword = (String) multi.getParameter("password");
        String confirm_password = (String) multi.getParameter("confirm_password");
        File f = multi.getFile("avatar");
        String avatar = multi.getFilesystemName("avatar");
        String uploadAvatarPathAssoluta = request.getServletContext().getRealPath("/UploadedAvatar");           
        
        if(newPassword.length()<4 || newPassword.length()>20){
            response.sendRedirect("pagenotfound.jsp");
        }
        else if(!newPassword.equals(confirm_password)){
                out.println(
                "<!DOCTYPE html>" +
                "<html>" +
                "    <head>" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                "        <link rel=\"stylesheet\" type=\"text/css\" href= \"css/bootstrap.css\" media=\"screen\">" +
                "        <title>Dati modificati</title>" +
                "        <meta http-equiv=\"refresh\" content=\"3; url=modificaDati.jsp\">  " +
                "    </head>" +
                "    <body>" +
                "        <div class=\"container\" style=\"padding: 50px\">" +
                "            <div class=\"col-lg-6 col-lg-offset-3\">" +
                "                <div class=\"alert alert-danger\"> <strong>Le password inserite non sono uguali!</strong> <p>Verrete reindirizzati alla pagina dei vostri dati.</p></div>" +
                "            </div>" +
                "        </div>" +
                "    </body>" +
                "</html>");
        } else {
            if(!newPassword.equals(""))
                ute.setPassword(newPassword);
            
            if(avatar!=null)
                ute.setAvatar(avatar);
            
            try {
                ServiceUtente.aggiornaUtente(ute);
            } catch (SQLException ex) {
                Logger.getLogger(ModificaDati.class.getName()).log(Level.SEVERE, null, ex);
            }
        
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

            session.removeAttribute("utente");
            session.setAttribute("utente", ute);

            RequestDispatcher view = request.getRequestDispatcher("modificheSuccess.jsp");
            view.forward(request, response);
        
        }
    }

}
