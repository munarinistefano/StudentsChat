/*
 * This project was deploy by ©povodev for WebProgramming course.
 * Was made public for illustrative purposes and for support. 
 * Represents a university examination certificate of the University of Trento 
 * course Computer Science.
 * 
 * No cut-and-paster. Don't be a drunk monkey, learns.
 */

package com.secondoprogetto.servlet;

import com.oreilly.servlet.MultipartRequest;
import com.secondoprogetto.model.Gruppo;
import com.secondoprogetto.model.Utente;
import com.secondoprogetto.services.ServiceGruppi;
import com.secondoprogetto.services.ServicePost;
import com.secondoprogetto.services.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author ©Povodev
 */
public class InserisciPost extends HttpServlet {
    
    private String dirName;
    private String fileName;
    private int postID;
    private int group_id;

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
        response.sendRedirect(request.getContextPath() + "/Gruppo?id="+group_id); //redirect to landing page
    }

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
        
        HttpSession session = request.getSession();        
        MultipartRequest multi = new MultipartRequest(request,".",1024*1024*5,"UTF-8");  

        Utente user = (Utente)session.getAttribute("utente");
        group_id = Integer.parseInt(request.getQueryString().substring(3));
        
        File f = multi.getFile("file1");
        String text=(String) multi.getParameter("testo");
        
        fileName = multi.getFilesystemName("file1");

        String path = request.getServletContext().getRealPath("/Resources/File/"+group_id+"/");
        text = Utils.parsing(text, group_id, path);
                
        boolean stringaVuota = text.matches("^\\s*$");
        
        Gruppo g = new Gruppo();
        try {
            g = ServiceGruppi.getGruppo(group_id);
        } catch (SQLException ex) {Logger.getLogger(InserisciPost.class.getName()).log(Level.SEVERE, null, ex);}
                
        if(g.getStato()==0){

            if(stringaVuota && f==null){
                response.sendRedirect(request.getContextPath() + "/Gruppo?id="+group_id);
            } else {
                if(stringaVuota){
                    text = "Ho inserito questo file: ";
                }

                if (fileName!=null){
                    fileName = formatFileName(fileName);
                    //check if "Resources" folder exists. If not, create it!
                    dirName = request.getServletContext().getRealPath("Resources/");
                    File file = new File(dirName);
                    if (!file.exists()){
                        file.mkdir();
                    }

                    //check if "Resources/File" folder exists. If not, create it!
                    dirName = request.getServletContext().getRealPath("Resources/File/");
                    File file2 = new File(dirName);
                    if (!file2.exists()){
                        file2.mkdir();
                    }

                    //check if "Resources/File/#groupID" folder exists. If not, create it!
                    dirName = request.getServletContext().getRealPath("Resources/File/" + group_id + "/");
                    File file3 = new File(dirName);
                    if (!file3.exists()){
                        file3.mkdir();
                    }

                    //se il file non e' nullo procedo all'upload
                    if (f!=null) {
                        int id_add = 0;
                        try {
                            id_add = ServicePost.getIdProssimoPost()+1;
                        } catch (SQLException ex) {Logger.getLogger(InserisciPost.class.getName()).log(Level.SEVERE, null, ex);}
                        fileName = "("+id_add+")-" + fileName;
                        File fOUT = new File(dirName,fileName);
                        FileOutputStream fOS;
                        try (FileInputStream fIS = new FileInputStream(f)) {
                            fOS = new FileOutputStream(fOUT);
                            while (fIS.available()>0)
                                fOS.write(fIS.read());
                        }
                        fOS.close();
                    }
                }
                try {
                    postID = ServicePost.aggiungiPost(text, user.getId(), group_id, fileName);
                } catch (SQLException ex) {
                    Logger.getLogger(InserisciPost.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            processRequest(request, response);
        }
    }
    
    /*
    *  Used to upload a file to the server.
    *  @return true if succesfully uploaded, false otherwise
    */
    private boolean uploadFile(File f, Utente user) throws FileNotFoundException, IOException {
        if (f!=null) { 
            fileName = user.getId() + "_" +fileName;      
            File fOUT = new File(dirName,fileName);
            FileOutputStream fOS;
            try (FileInputStream fIS = new FileInputStream(f)) {
                fOS = new FileOutputStream(fOUT);
                while (fIS.available()>0) { 
                    fOS.write(fIS.read());
                }
            }
            fOS.close();
            return true;
        }
        return false;
    }

    /*
    *  Delete white spaces from file name
    */
    private String formatFileName(String fileName) {
        fileName = fileName.replaceAll(" ", "");
        return fileName;
    }
    
    private static String getFilename(Part part) {
    for (String cd : part.getHeader("content-disposition").split(";")) {
        if (cd.trim().startsWith("filename")) {
            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
        }
    }
    return null;
}
}
