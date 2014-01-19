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
public class FileFilter implements Filter {
    int idGruppo;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session= req.getSession();
        HttpServletResponse resp = (HttpServletResponse)response;
                
        Utente ute = (Utente) session.getAttribute("utente");
        
        String requestURI = req.getRequestURI();
        
        int start = requestURI.indexOf("File/") + 5;
        int end = requestURI.indexOf("/", start);
        
        boolean isPubblico = true;
        
        NumberFormatException error =  null;
        try{
            idGruppo = Integer.parseInt(requestURI.substring(start,end));
        } catch (NumberFormatException nfe){
            error = nfe;
        }
        
        try {
            isPubblico = ServiceGruppi.isPubblico(idGruppo);
        } catch (SQLException ex) {
            Logger.getLogger(FileFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        if(ute==null && isPubblico)
            chain.doFilter(request, response);
        else if(ute!= null) {
            try {
                if (ServiceGruppiUtente.utenteIscrittoAlGruppo(idGruppo,ute.getId()) || isPubblico){
                    chain.doFilter(request, response);
                } else {
                    resp.sendRedirect(req.getContextPath()+"/accessdenied.jsp");
                }
            } catch (SQLException ex) {
                Logger.getLogger(FileFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(ute==null && !isPubblico)   
            resp.sendRedirect(req.getContextPath()+"/Logout");
    }

    @Override
    public void destroy() {
    }
    
}
