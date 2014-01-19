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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.secondoprogetto.model.Utente;
import java.util.ArrayList;

/**
 * 
 * @author ©Povodev
 */
public class ServiceUtente {

    /**
     * authenticate
     * @param username
     * @param password
     * @return
     * @throws SQLException 
     */
    public static Utente authenticate(String username,String password) throws SQLException{
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM utenti WHERE username = ? AND password = ?")) {
            stm.setString(1, username);
            stm.setString(2, password);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    aggiornaData(rs.getInt(1));
                    Utente utente = new Utente();
                    utente.setId(rs.getInt("id"));
                    utente.setUsername(rs.getString("username"));
                    utente.setPassword(rs.getString("password"));
                    utente.setEmail(rs.getString("email"));
                    utente.setAvatar(rs.getString("avatar"));
                    utente.setTipo_utente(rs.getInt("tipo_utente"));
                    utente.setDate_time(rs.getTimestamp("date_time"));
                    return utente;
                } else { return null;}
            }
        }
    }

    /**
     * Email already registered
     * @param email
     * @return
     * @throws SQLException 
     */
    public static boolean emailGiaPresente(String email) throws SQLException{
        try (PreparedStatement stm = con.prepareStatement
                ("SELECT email FROM utenti WHERE email = ?")) {
            stm.setString(1, email);
             try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
             }
        }
        return false;
    }
    
    /**
     * update date
     * @param ID
     * @throws SQLException 
     */
    private static void aggiornaData(int ID) throws SQLException {
        try (PreparedStatement stm = con.prepareStatement("UPDATE UTENTI SET date_time = CURRENT_TIMESTAMP WHERE ID = ?")) {
            stm.setInt(1, ID);
            stm.executeUpdate();
        }
    }
    
    /**
     * allUser
     * @return
     * @throws SQLException 
     */
    public static ArrayList<Utente> getTuttiUtenti() throws SQLException{
        
        ArrayList<Utente> utenti = new ArrayList();
        PreparedStatement stm = 
                con.prepareStatement("SELECT * FROM utenti");
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Utente utente = new Utente();
                    utente.setId(rs.getInt(1));
                    utente.setUsername(rs.getString(2));
                    utenti.add(utente);
                }
            }
        return utenti;
    }
  
    /**
     * insert new user
     * @param ute
     * @return 
     * @throws SQLException 
     */
    public static int inserisciUtente(Utente ute) throws SQLException{
        int id = 0;
        PreparedStatement stm = con.prepareStatement
("INSERT INTO UTENTI(username,password,email,avatar,tipo_utente,date_time) VALUES (?,?,?,?,?,CURRENT_TIMESTAMP)",PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, ute.getUsername());
            stm.setString(2, ute.getPassword());
            stm.setString(3, ute.getEmail());
            stm.setString(4, ute.getAvatar());
            stm.setInt(5, ute.getTipo_utente());
            stm.executeUpdate();
            return id = Utils.getId(stm);
    }
    
     /**
     * checker user
     * @param usr
     * @return true se il nome utente è già presente, false altrimenti
     * @throws java.sql.SQLException
     */
    public static boolean controlNameUser(String usr) throws SQLException{
        PreparedStatement stm = 
            con.prepareStatement("SELECT * FROM utenti WHERE username = ?");
            stm.setString(1, usr);

        try (ResultSet rs = stm.executeQuery()) {
            if(rs.next())
                return false;
            else 
                return true;
        }finally {stm.close();}
    }
    
    /**
     * email exist
     * @param email
     * @return
     * @throws SQLException 
     */
    public static boolean controlloEmail(String email) throws SQLException{
        PreparedStatement stm = 
            con.prepareStatement("SELECT * FROM utenti WHERE email = ?");
            stm.setString(1, email);

        try (ResultSet rs = stm.executeQuery()) {
            if(rs.next())
                return true;
            else 
                return false;
        }finally {stm.close();}
    }
    
    /**
     * password email
     * @param email
     * @return
     * @throws SQLException 
     */
    public static String passwordUtente(String email) throws SQLException{
        
        String password="";
        PreparedStatement stm = 
            con.prepareStatement("SELECT password FROM utenti WHERE email = ?");
            stm.setString(1, email);

        try (ResultSet rs = stm.executeQuery()) {
            if(rs.next())
                password = rs.getString("password");
            else 
                password = "";
        }finally {stm.close();}
        
        return password;
    }
    
    /**
     * update utente
     * @param ute
     * @throws SQLException 
     */
    public static void aggiornaUtente(Utente ute) throws SQLException{
            
        try(PreparedStatement stm = con.prepareStatement
                    ("UPDATE UTENTI SET password = ?, avatar = ? WHERE id = ?")) {
            stm.setString(1, ute.getPassword());
            stm.setString(2, ute.getAvatar());
            stm.setInt(3, ute.getId());
            stm.executeUpdate();
        }
    }

    /**
     * user registered
     * @param id_gruppo
     * @return
     * @throws SQLException 
     */
    public static ArrayList<Utente> getTuttiUtentiNonIscritti(int id_gruppo) throws SQLException {
        ArrayList<Utente> utenti = new ArrayList();
        try (PreparedStatement stm = con.prepareStatement                
         ("SELECT id,username FROM utenti WHERE id NOT IN (SELECT id_utente FROM gruppiutente WHERE id_gruppo = ?) AND id NOT IN (SELECT id_utente FROM inviti WHERE id_gruppo = ? AND stato = ?)")) {
            stm.setInt(1, id_gruppo);
            stm.setInt(2, id_gruppo);
            stm.setInt(3, 0);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Utente utente = new Utente();
                utente.setId(rs.getInt(1));
                utente.setUsername(rs.getString(2));
                utenti.add(utente);
            }
        }
        return utenti;
    }
    
    /**
     * serch user
     * @param id
     * @return
     * @throws SQLException 
     */
    public static Utente searchUtenteById(int id) throws SQLException{
        Utente ute = new Utente();
        PreparedStatement stm = 
            con.prepareStatement("SELECT * FROM utenti WHERE id = ?");
            stm.setInt(1, id);

        try (ResultSet rs = stm.executeQuery()) {
             while (rs.next()) {
                ute.setUsername(rs.getString("username"));
                ute.setAvatar(rs.getString("avatar"));
                ute.setDate_time(rs.getTimestamp("date_time"));
                ute.setEmail(rs.getString("email"));
                ute.setId(id);
                ute.setPassword(rs.getString("password"));
                ute.setTipo_utente(rs.getInt("tipo_utente"));
            }
        }finally {stm.close();}
        
        return ute;
    }
}
