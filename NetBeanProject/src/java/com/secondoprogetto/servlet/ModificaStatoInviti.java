/*
 * This project was deploy by ©povodev for WebProgramming course.
 * Was made public for illustrative purposes and for support. 
 * Represents a university examination certificate of the University of Trento 
 * course Computer Science.
 * 
 * No cut-and-paster. Don't be a drunk monkey, learns.
 */

package com.secondoprogetto.servlet;

import com.secondoprogetto.model.Utente;
import com.secondoprogetto.services.ServiceGruppiUtente;
import com.secondoprogetto.services.ServiceInviti;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ©Povodev
 */
public class ModificaStatoInviti extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        Utente utente = (Utente)session.getAttribute("utente");

        Enumeration paramNames = request.getParameterNames();

        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            for (String paramValue : paramValues) {
                switch (paramValue) {
                    case "accept":
                        try {
                            ServiceInviti.cambiaStato(utente.getId(),Integer.parseInt(paramName),1);
                            ServiceGruppiUtente.aggiungi(utente.getId(),Integer.parseInt(paramName));
                        } catch (SQLException ex) {
                            Logger.getLogger(ModificaStatoInviti.class.getName()).log(Level.SEVERE, null, ex);
                        }   break;
                    case "refuse":
                        try {
                            ServiceInviti.cambiaStato(utente.getId(),Integer.parseInt(paramName),2);
                        } catch (SQLException ex) {
                            Logger.getLogger(ModificaStatoInviti.class.getName()).log(Level.SEVERE, null, ex);
                        }   break;
                }
            }
        }
        response.sendRedirect(request.getContextPath()+"/Home");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
