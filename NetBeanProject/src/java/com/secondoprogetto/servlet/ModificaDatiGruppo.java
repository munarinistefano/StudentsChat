/*
 * This project was deploy by ©povodev for WebProgramming course.
 * Was made public for illustrative purposes and for support. 
 * Represents a university examination certificate of the University of Trento 
 * course Computer Science.
 * 
 * No cut-and-paster. Don't be a drunk monkey, learns.
 */

package com.secondoprogetto.servlet;

import com.secondoprogetto.model.Gruppo;
import com.secondoprogetto.model.Utente;
import com.secondoprogetto.services.ServiceGruppi;
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
public class ModificaDatiGruppo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        
        Utente ute = (Utente) session.getAttribute("utente");
        String flag = request.getParameter("privacy");
        int pubblico;
        
        if ("pubblico".equals(flag))
            pubblico = 0;
        else
            pubblico = 1;
        
        String query = request.getQueryString();
        int id_gruppo = Integer.parseInt(request.getParameter("id"));
        int id_gruppo_mod = 0;
        
        Enumeration paramNames = request.getParameterNames();
        
        int group_id = 0;
        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();  
            
            String[] paramValues = request.getParameterValues(paramName);
            for (String paramValue : paramValues) {
                if (paramName.equals("groupname")) {
                    String group_name = paramValue;
                    if(group_name.equals("")){
                        Gruppo g = ServiceGruppi.getGruppo(id_gruppo);
                        group_name = g.getNome();
                        id_gruppo_mod = g.getID();
                    }
                    try {
                        ServiceGruppi.modifica(id_gruppo, group_name, pubblico);
                    } catch (SQLException ex) {
                        Logger.getLogger(InserisciGruppo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (!paramName.equals("id") && !paramName.equals("privacy")){  //INSERISCO NUOVI UTENTI
                    int id_utente = Integer.parseInt(paramName);
                    int stato = 0;
                    try {           
                        ServiceInviti.aggiungiInvito(id_utente, id_gruppo, stato);
                    } catch (SQLException ex) {
                        Logger.getLogger(InserisciGruppo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        response.sendRedirect(request.getContextPath() + "/Gruppi");
        
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
            Logger.getLogger(ModificaDatiGruppo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ModificaDatiGruppo.class.getName()).log(Level.SEVERE, null, ex);
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
