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
import com.secondoprogetto.model.Utente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author ©Povodev
 */
public class ServiceGruppi {
    
    /*
     * Create a new Group
     */
    public static int aggiungi(String name, int id_proprietario, int flag, Timestamp datacreazione) throws SQLException {
        int id;
        try (PreparedStatement stm = con.prepareStatement
                    ("INSERT INTO gruppi (nome,data_creazione,flag,id_proprietario) VALUES (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, name);
            stm.setTimestamp(2, datacreazione);
            stm.setInt(3, flag);
            stm.setInt(4, id_proprietario);
            stm.executeUpdate();
            id = Utils.getId(stm);
        }
        return id;
    }

    /**
     * Group ADmin
     * @param id_gruppo
     * @return
     * @throws SQLException 
     */
    public static int getIdProprietario(int id_gruppo) throws SQLException {        
        try (PreparedStatement stm = con.prepareStatement
                    ("SELECT id_proprietario FROM gruppi WHERE id = ?")) {
            stm.setInt(1, id_gruppo);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }
    
    
    /**
     * GetGruppo
     * @param id_gruppo
     * @return
     * @throws SQLException 
     */
    public static Gruppo getGruppo(int id_gruppo) throws SQLException{
        Gruppo gruppo = new Gruppo();
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM gruppi WHERE id = ?")) {
            stm.setInt(1, id_gruppo);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    gruppo.setID(rs.getInt("id"));
                    gruppo.setNome(rs.getString("nome"));
                    gruppo.setData_creazione(rs.getDate("data_creazione"));
                    gruppo.setFlag(rs.getInt("flag"));
                    gruppo.setID_proprietario(rs.getInt("ID_proprietario"));
                    gruppo.setStato(rs.getInt("stato"));
                }
            }
        }
        return gruppo;
    }
    
    /**
     * allGroups
     * @return 
     * @throws java.sql.SQLException 
     */
    public static ArrayList<Gruppo> listaDeiGruppiPerModeratore() throws SQLException{

    ArrayList<Gruppo> listaGruppi = new ArrayList();
    PreparedStatement stm = 
            con.prepareStatement("SELECT * FROM GRUPPI");

        try (ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                Gruppo gruppo = new Gruppo();
                gruppo.setID(rs.getInt("id"));
                gruppo.setNome(rs.getString("nome"));
                gruppo.setFlag(rs.getInt("flag"));
                gruppo.setData_creazione(rs.getDate("data_creazione"));
                gruppo.setID_proprietario(rs.getInt("id_proprietario"));
                gruppo.setStato(rs.getInt("stato"));
                
                int numeroPost = ServiceGruppi.numeroPostDelGruppo(gruppo.getID());
                Utente proprietario = ServiceUtente.searchUtenteById(gruppo.getID_proprietario());
                gruppo.setNumero_post(numeroPost);
                gruppo.setNome_proprietario(proprietario.getUsername());
                listaGruppi.add(gruppo);
            }
        } finally {
            stm.close();
        }
        return listaGruppi;
    }
        
        
        
        
    /**
     * number group posts
     * @param id_gruppo
     * @return
     * @throws SQLException 
     */
    public static int numeroPostDelGruppo(int id_gruppo) throws SQLException{
      try (PreparedStatement stm = con.prepareStatement("SELECT COUNT(*) FROM posts WHERE id_gruppo = ?")) {
        stm.setInt(1, id_gruppo);
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
               return rs.getInt(1);
            }        
        }
        }
        return 0;
    }
    
    
    /**
     * update gruppo
     * @param id_gruppo
     * @param nome_gruppo
     * @param flag
     * @throws SQLException 
     */
    public static void modifica(int id_gruppo, String nome_gruppo, int flag) throws SQLException{
        try (PreparedStatement stm = con.prepareStatement
                    ("UPDATE gruppi SET nome = ?, flag = ? WHERE id = ?")) {
            stm.setString(1, nome_gruppo);
            stm.setInt(2, flag);
            stm.setInt(3, id_gruppo);
            stm.executeUpdate();
        }
    }
    
    /**
     * update state
     * 
     *  0 = public
     *  1 = private
     *  2 = close
     * 
     * @param id
     * @param flag
     * @throws SQLException 
     */
    public static void cambiaStatoGruppo (int id, int flag) throws SQLException {
        try (PreparedStatement stm = con.prepareStatement("UPDATE gruppi SET stato = ? WHERE id = ?")) {
            stm.setInt(1, flag);
            stm.setInt(2, id);
            stm.executeUpdate();
        }
    }
    
    
    
    
    /**
     * isPubblico
     * @param id
     * @return
     * @throws SQLException 
     */
    public static boolean isPubblico(int id) throws SQLException{
        PreparedStatement stm = 
              con.prepareStatement("SELECT * FROM gruppi WHERE id = ?");
        int res;
        
        try {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    res = rs.getInt("flag");
                    if(res == 0)
                        return true;
                    else
                        return false;
                }        
            }
        }finally {stm.close();}        
        return false;
    }
}
