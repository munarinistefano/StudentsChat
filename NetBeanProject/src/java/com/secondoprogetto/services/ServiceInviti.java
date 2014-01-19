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
import com.secondoprogetto.model.Inviti;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ©Povodev
 */
public class ServiceInviti {
    
    /*
     * add invitation
     */
    public static void aggiungiInvito (int USER_ID, int GROUP_ID, int stato) throws SQLException{
        try (PreparedStatement stm = con.prepareStatement
                ("INSERT INTO inviti (id_utente,id_gruppo,stato) VALUES (?,?,?)")) {
            stm.setInt(1, USER_ID);
            stm.setInt(2, GROUP_ID);
            stm.setInt(3, stato);
            stm.executeUpdate();
        }
    }
    
    
    /**
     * invitationList
     * @param id
     * @return
     * @throws SQLException 
     */
    public static ArrayList<Inviti> getInvitiUtente(int id) throws SQLException{

        ArrayList<Inviti> inviti = new ArrayList();
        try (PreparedStatement stm = con.prepareStatement("SELECT * FROM inviti JOIN gruppi ON inviti.ID_GRUPPO = gruppi.ID WHERE inviti.id_utente = ? AND inviti.stato = 0")) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Inviti invito = new Inviti();
                    invito.setId_gruppo(rs.getInt(4));
                    invito.setNome_gruppo(rs.getString(5));
                    invito.setData_creazione(rs.getTimestamp(6));
                    invito.setProprietario(rs.getString(8));
                    inviti.add(invito);
                }
            }
        }
        return inviti;
    }
    
    /*
     * update invitation state
     */
    public static void cambiaStato(int user_id, int group_id, int stato) throws SQLException {
        try (PreparedStatement stm = con.prepareStatement
                ("UPDATE inviti SET stato = ? WHERE id_utente = ? AND id_gruppo = ?")) {
            stm.setInt(1, stato);
            stm.setInt(2, user_id);
            stm.setInt(3, group_id);
            stm.executeUpdate();
        }
    }
    
}
