/*
 * This project was deploy by ©povodev for WebProgramming course.
 * Was made public for illustrative purposes and for support. 
 * Represents a university examination certificate of the University of Trento 
 * course Computer Science.
 * 
 * No cut-and-paster. Don't be a drunk monkey, learns.
 */

package com.secondoprogetto.filters;

import com.secondoprogetto.model.Utente;
import com.secondoprogetto.services.ServiceGruppi;
import com.secondoprogetto.services.ServiceGruppiUtente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ©Povodev
 */
public class GruppoFilter implements Filter {
    int idGruppo = 0;
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession();
        HttpServletResponse resp = (HttpServletResponse)response;
        
        Utente ute = (Utente) session.getAttribute("utente");
        
        
        boolean isPubblico = true;
        idGruppo = Integer.parseInt(request.getParameter("id"));
                
        try {
            isPubblico = ServiceGruppi.isPubblico(idGruppo);
        } catch (SQLException ex) {
            Logger.getLogger(GruppoFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        int tipo_utente = 1;
        if(ute!=null){
            tipo_utente = ute.getTipo_utente();
        }
        
        boolean isModeratore;
        if (tipo_utente == 0){
            isModeratore = true;
        }
        else{
            isModeratore = false;
        }
        
        if(isModeratore || (ute==null && isPubblico))
            chain.doFilter(request, response);
        else if(ute!= null) {
            try {
                boolean iscrittoAlGruppo = ServiceGruppiUtente.utenteIscrittoAlGruppo(idGruppo,ute.getId());
                if (iscrittoAlGruppo || isPubblico){
                    request.setAttribute("iscrittoAlGruppo", iscrittoAlGruppo);
                    chain.doFilter(request, response);
                } else {
                    resp.sendRedirect(req.getContextPath()+"/accessdenied.jsp");
                }
            } catch (SQLException ex) {
                Logger.getLogger(GruppoFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(ute==null && !isPubblico){
            resp.sendRedirect(req.getContextPath()+"/Logout"); 
        }
    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
}
