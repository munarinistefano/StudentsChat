/*
 * This project was deploy by ©povodev for WebProgramming course.
 * Was made public for illustrative purposes and for support. 
 * Represents a university examination certificate of the University of Trento 
 * course Computer Science.
 * 
 * No cut-and-paster. Don't be a drunk monkey, learns.
 */

package com.secondoprogetto.services;

import static com.secondoprogetto.database.DBManager.con;
import com.secondoprogetto.model.Gruppo;
import com.secondoprogetto.model.Post;
import com.secondoprogetto.model.Utente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ©Povodev
 */
public class ServicePost {
    
    /**
     * all posts
     * @param id_gruppo
     * @return
     * @throws SQLException 
     */
    public static ArrayList<Post> getTuttiPost(int id_gruppo) throws SQLException{
        ArrayList<Post> posts = new ArrayList<>();
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM posts JOIN utenti ON posts.ID_UTENTE = utenti.ID WHERE posts.id_gruppo = ? ORDER BY posts.date_time DESC")) {
            stm.setInt(1, id_gruppo);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Post post = new Post();
                    post.setId(rs.getInt("ID"));
                    post.setTesto(rs.getString("testo"));
                    post.setId_utente(rs.getInt("id_utente"));
                    post.setId_gruppo(rs.getInt("id_gruppo"));
                    post.setDate_time(rs.getTimestamp("date_time"));
                    post.setFile(rs.getString("file"));
                    post.setNome_utente(rs.getString("username"));
                    post.setAvatar_utente(rs.getString("avatar"));
                    posts.add(post);
                }
            }
        }
        return posts;
    }
    
    /**
     * id next
     * @return
     * @throws SQLException 
     */
    public static int getIdProssimoPost() throws SQLException{
        PreparedStatement stm = con.prepareStatement
            ("SELECT COUNT(*) FROM POSTS");
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } finally {stm.close();}
        return 0;
    }
    
    
    /**
     * add post
     * @param testo
     * @param id_utente
     * @param id_gruppo
     * @param file
     * @return
     * @throws SQLException 
     */
    public static int aggiungiPost(String testo, int id_utente, int id_gruppo, String file) throws SQLException{

        int id = 0;
        
        PreparedStatement stm;
        stm = con.prepareStatement
                ("INSERT INTO posts (testo,id_utente,id_gruppo,date_time,file) VALUES (?,?,?,CURRENT_TIMESTAMP,?)",PreparedStatement.RETURN_GENERATED_KEYS);
        try {
            stm.setString(1, testo);
            stm.setInt(2, id_utente);
            stm.setInt(3, id_gruppo);
            stm.setString(4, file);
            stm.executeUpdate();
            id = Utils.getId(stm);
        } finally {
            stm.close();
        }
        return id;
    }
    
    
    /**
     * offline posts
     * @param ute
     * @return 
     * @throws java.sql.SQLException 
     */
    
    public static ArrayList<Post> postOffline(Utente ute) throws SQLException{
        
        ArrayList<Post> posts = new ArrayList<>();
        try (PreparedStatement stm = con.prepareStatement
                ("SELECT * FROM posts WHERE date_time >= ? AND id_gruppo IN (SELECT id_gruppo FROM gruppiutente WHERE id_utente = ?) AND id_utente != ? ORDER BY date_time DESC")) {
            stm.setTimestamp(1, ute.getDate_time());
            stm.setInt(2, ute.getId());
            stm.setInt(3, ute.getId());
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Gruppo g= ServiceGruppi.getGruppo(rs.getInt("id_gruppo"));
                    Utente uteTmp = ServiceUtente.searchUtenteById(rs.getInt("id_utente"));
                    Post post = new Post();
                    post.setId(rs.getInt("ID"));
                    
                    
                    String postTest =  (rs.getString("testo"));
                    if(postTest.length()>55)
                        postTest=postTest.substring(0, 40)+"..";
                    post.setTesto(postTest);
                    
                    
                    
                    post.setId_utente(rs.getInt("id_utente"));
                    post.setId_gruppo(rs.getInt("id_gruppo"));
                    post.setDate_time(rs.getTimestamp("date_time"));
                    post.setFile(rs.getString("file"));
                    post.setNome_utente(uteTmp.getUsername());
                    post.setAvatar_utente(uteTmp.getAvatar());
                    post.setNome_gruppo(g.getNome());
                    posts.add(post);
                }
            }
        }
        
        return posts;
    }
    
}
