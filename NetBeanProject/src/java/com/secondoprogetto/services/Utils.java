/*
 * This project was deploy by ©povodev for WebProgramming course.
 * Was made public for illustrative purposes and for support. 
 * Represents a university examination certificate of the University of Trento 
 * course Computer Science.
 * 
 * No cut-and-paster. Don't be a drunk monkey, learns.
 */

package com.secondoprogetto.services;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ©Povodev
 */
public class Utils {
    
    private static ResultSet generatedKey = null;
    /**
     * Get id of just created group. Used to get the id of the just created Group
     * @param stm
     * @return
     * @throws SQLException 
     */
    public static int getId(PreparedStatement stm) throws SQLException {
        int id = 0;
        try {
            generatedKey = stm.getGeneratedKeys();
        
            if (generatedKey.next()) {
                id = generatedKey.getInt(1);
            } else {
                throw new SQLException("add failed, no generated key obtained.");
            }
        } finally {
              if (generatedKey != null) try {generatedKey.close();} catch (SQLException logOrIgnore) {}  
        }
        return id;
    }
    
    /**
     * Control email is a valid email
     * @param email
     * @return 
     */
    public static boolean controlemail(String email){
        
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        boolean matchFound = m.matches();

        if(matchFound)
            return true;
        else
            return false;
    }
    
    
    /**
     * Parsing text
     * @param text
     * @param gruppo
     * @param pt
     * @return 
     */
    public static String parsing(String text,int gruppo,String pt){
    
        String[] codice = {"html","head","body","h1","script","h2","h3","h4","h5","h6","style="};
        String[] result = text.split(" ");
        String res = "";
        for (String singleWord : result) {
            int length = singleWord.length();
            for (String codice1 : codice) {
                if (text.contains(codice1)) {
                    singleWord = " - non e' permesso inserire questo genere di codice - ";
                }
            }
            if(singleWord.length()>450){
                singleWord = "wordTooLong please insert space or something else";
            }
            if(singleWord.startsWith("<script")){
                singleWord = " - no script permitted -";
            }
            if(singleWord.startsWith("<")){
                singleWord = " - non e' permesso inserire codice - ";
            }
            
            if (text.contains("<script")){
                singleWord = " - non e' permesso inserire questo genere di codice - ";
            }
            
            if(singleWord.startsWith("$$") && singleWord.endsWith("$$")){
                singleWord = singleWord.replace("$$","");
                singleWord = singleWord.replace("\n","");
                        
                if(singleWord.startsWith("www.")){
                    singleWord = "http://" +singleWord;
                    singleWord = "<a href=\""+singleWord+"\" >"+singleWord+"</a>";                
                }else if(singleWord.startsWith("http://") || singleWord.startsWith("https://")){
                    singleWord = "<a href=\""+singleWord+"\" >"+singleWord+"</a>";                
                }else{
                    if(checkExists(singleWord,pt)){
                        singleWord = "<a href=Resources/File/"+gruppo+"/"+singleWord+"><img src=Resources/File/"+gruppo+"/"+singleWord+" alt=\"image\" height=\"100\" width=\"100\" class=\"img-thumbnail\">"+singleWord+"</a>";
                    }
                }
            }
            res = res + " " +singleWord;
        }
        return res;
    }
    
    /**
     * file checker
     * @param nome
     * @param pt
     * @return 
     */
    public static boolean checkExists(String nome,String pt) {
        nome = pt + "/"+nome;
        File file=new File(nome);
        boolean exists = file.exists();
        if (!exists) {
            return false;
        }else{
            return true;
        }
    }
   
    
    public static String parsingNomeGruppo(String text){
    
        String[] codice = {"<",">","/","html","head","body","h1","script","h2","h3","h4","h5","h6","style="};
        String[] result = text.split(" ");
        String res = "";
        for (String singleWord : result) {
            int length = singleWord.length();
            for (String codice1 : codice) {
                if (text.contains(codice1)) {
                    singleWord = singleWord.replace(codice1, "");
                }
            }
            res = res + " " +singleWord;
        }
        return res;
    }
}
