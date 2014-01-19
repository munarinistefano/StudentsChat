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
import com.secondoprogetto.services.ServiceGruppi;
import com.secondoprogetto.services.ServiceInviti;
import com.secondoprogetto.services.Utils;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
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
public class InserisciGruppo extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        Utente utente = (Utente)session.getAttribute("utente");
                
        String flag = request.getParameter("privacy");
        
        int pubblico;
        
        if ("pubblico".equals(flag))
            pubblico = 0;
        else
            pubblico = 1;
        
        Enumeration paramNames = request.getParameterNames();
        int group_id = 0;
        
        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement(); 
            java.sql.Date date;
            java.util.Date utilDate = new java.util.Date();
            date = new java.sql.Date(utilDate.getTime());    

            String[] paramValues = request.getParameterValues(paramName);
            for (String paramValue : paramValues) {
                if (paramName.equals("groupname")) {
                    try {
                        paramValue = Utils.parsingNomeGruppo(paramValue);
                        Timestamp now = new Timestamp(date.getTime());
                        group_id = ServiceGruppi.aggiungi(paramValue, utente.getId(), pubblico, now);
                        ServiceInviti.aggiungiInvito(utente.getId(), group_id, 1);
                        ServiceGruppiUtente.aggiungi(utente.getId(),group_id);
                    }catch (SQLException ex) {
                        Logger.getLogger(InserisciGruppo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    //get selected checkboxes
                    if (!paramName.equals("privacy")){
                        if (group_id!=0){
                            try {           
                                ServiceInviti.aggiungiInvito(Integer.parseInt(paramName), group_id, 0);
                            } catch (SQLException ex) {
                                Logger.getLogger(InserisciGruppo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/Gruppi");
    }
}
