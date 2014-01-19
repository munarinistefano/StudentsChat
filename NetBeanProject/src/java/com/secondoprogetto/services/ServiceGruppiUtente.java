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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ©Povodev
 */
public class ServiceGruppiUtente {
    
    /**
     * search Group
     * @param id
     * @return
     * @throws SQLException 
     */
    public static Gruppo searchGroupById(int id) throws SQLException{
        
        Gruppo gruppo = new Gruppo();
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM GRUPPI WHERE id=? AND flag = 1")) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    gruppo.setID(rs.getInt("id"));
                    gruppo.setNome(rs.getString("nome"));
                    gruppo.setData_creazione(rs.getDate("data_creazione"));
                    gruppo.setFlag(rs.getInt("flag"));
                    gruppo.setNome_proprietario(getNomeProprietario(rs.getInt("id_proprietario")));
                    gruppo.setID_proprietario(rs.getInt("id_proprietario"));
                    gruppo.setStato(rs.getInt("stato"));
                } else {
                    return null;
                }
            }
        }
        return gruppo;
    }
    
    /**
     * group admin
     * @param id
     * @return
     * @throws SQLException 
     */
    public static String getNomeProprietario(int id) throws SQLException{
        
        Gruppo gruppo = new Gruppo();
        try (PreparedStatement stm = con.prepareStatement("SELECT username FROM utenti WHERE id = ?")) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    return rs.getString(1);
                }
            }
        }
        return null;
    }
    
    /**
     * list user's group
     * @param id
     * @return
     * @throws SQLException 
     */
    public static ArrayList<Gruppo> getGruppiUtente(int id) throws SQLException{

        ArrayList<Gruppo> gruppi = new ArrayList();
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM gruppiutente WHERE id_utente = ?")) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Gruppo gruppo = ServiceGruppiUtente.searchGroupById(rs.getInt("id_gruppo"));
                    if (gruppo!=null){
                        gruppi.add(gruppo);
                    }
                }
            }
        }
        return gruppi;
    }
    
    /**
     * add new one
     * @param user_id
     * @param group_id
     * @throws SQLException 
     */
    public static void aggiungi(int user_id, int group_id) throws SQLException {
        try (PreparedStatement stm = con.prepareStatement
                    ("INSERT INTO gruppiutente (id_utente,id_gruppo) VALUES (?,?)")) {
            stm.setInt(1, user_id);
            stm.setInt(2, group_id);
            stm.executeUpdate();
        }
    }
    
    /**
     * delete
     * @param user_id
     * @param group_id
     * @throws SQLException 
     */
    public static void elimina(int user_id, int group_id) throws SQLException {
        try (PreparedStatement stm = con.prepareStatement
                    ("DELETE FROM gruppiutente WHERE id_utente = ? AND id_gruppo = ?")) {
            stm.setInt(1, user_id);
            stm.setInt(2, group_id);
            stm.executeUpdate();
        }
    }

    /**
     * public group
     * @return
     * @throws SQLException 
     */
    public static ArrayList<Gruppo> getGruppiPubblici() throws SQLException {
        ArrayList<Gruppo> gruppi = new ArrayList();
        PreparedStatement stm = 
                con.prepareStatement("SELECT * FROM gruppi JOIN utenti ON gruppi.ID_PROPRIETARIO = utenti.ID WHERE gruppi.flag = 0");
            
        try (ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                Gruppo gruppo = new Gruppo();
                gruppo.setNome_proprietario(rs.getString("username"));
                gruppo.setID(rs.getInt("ID"));
                gruppo.setNome(rs.getString("nome"));
                gruppo.setData_creazione(rs.getDate("data_creazione"));
                gruppo.setFlag(rs.getInt("flag"));
                gruppo.setID_proprietario(rs.getInt("id_proprietario"));
                gruppo.setStato(rs.getInt("stato"));
                gruppi.add(gruppo);
            }
        }
        return gruppi;    
    }
    
    /**
     * registered users
     * @param id_gruppo
     * @param id_utente
     * @return
     * @throws SQLException 
     */
    public static boolean utenteIscrittoAlGruppo(int id_gruppo, int id_utente) throws SQLException{
        int i=0;
        try (PreparedStatement stm = con.prepareStatement("SELECT COUNT(1) FROM gruppiutente WHERE id_gruppo = ? AND id_utente = ?")) {
            stm.setInt(1, id_gruppo);
            stm.setInt(2, id_utente);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    if (rs.getInt(1)==1)
                        return true;
                    else
                        return false;
                }
            }
        }
        return false;
    }
}
