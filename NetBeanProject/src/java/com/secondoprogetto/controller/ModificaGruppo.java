/*
 * This project was deploy by ©povodev for WebProgramming course.
 * Was made public for illustrative purposes and for support. 
 * Represents a university examination certificate of the University of Trento 
 * course Computer Science.
 * 
 * No cut-and-paster. Don't be a drunk monkey, learns.
 */

package com.secondoprogetto.controller;

import com.secondoprogetto.model.Utente;
import com.secondoprogetto.services.ServiceGruppi;
import com.secondoprogetto.services.ServiceUtente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ©Povodev
 */
public class ModificaGruppo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String query = request.getQueryString();
        int id_gruppo = Integer.parseInt(query.substring(3));
        
        ArrayList<Utente> utenti = new ArrayList<>();
        try {
            utenti = ServiceUtente.getTuttiUtentiNonIscritti(id_gruppo);
        } catch (SQLException ex) {
            Logger.getLogger(Gruppi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        com.secondoprogetto.model.Gruppo gruppo = new com.secondoprogetto.model.Gruppo();
        try {
            gruppo = ServiceGruppi.getGruppo(id_gruppo);
        } catch (SQLException ex) {
            Logger.getLogger(Gruppo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("id", id_gruppo);
        request.setAttribute("utenti", utenti);
        request.setAttribute("gruppo", gruppo);        
        RequestDispatcher view = request.getRequestDispatcher("modificadatigruppo.jsp");
        view.forward(request, response);
        
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
