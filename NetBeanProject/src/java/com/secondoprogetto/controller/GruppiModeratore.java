/*
 * This project was deploy by ©povodev for WebProgramming course.
 * Was made public for illustrative purposes and for support. 
 * Represents a university examination certificate of the University of Trento 
 * course Computer Science.
 * 
 * No cut-and-paster. Don't be a drunk monkey, learns.
 */

package com.secondoprogetto.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.secondoprogetto.services.ServiceGruppi;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.secondoprogetto.model.Gruppo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;


/**
 *
 * @author ©Povodev
 */
public class GruppiModeratore extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        
        ArrayList<Gruppo> listaGruppi = ServiceGruppi.listaDeiGruppiPerModeratore();        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaGruppi);
        request.setAttribute("gruppiMod", json);
        RequestDispatcher view = request.getRequestDispatcher("moderatore.jsp");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GruppiModeratore.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GruppiModeratore.class.getName()).log(Level.SEVERE, null, ex);
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
