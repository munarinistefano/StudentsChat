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
import java.io.IOException;
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
 * @author ©Povodev
 */
public class SessionFilter implements Filter {
    HttpSession session;
    Utente utente;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        session = null;
        utente = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        
        session = req.getSession(false);
        
        if (session!=null){
            utente = (Utente)session.getAttribute("utente");
        }
        
        if (session==null || utente==null){
            resp.sendRedirect(req.getContextPath()+"/Logout");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
    
    
}
