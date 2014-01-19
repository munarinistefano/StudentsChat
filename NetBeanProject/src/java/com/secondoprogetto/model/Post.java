/*
 * This project was deploy by ©povodev for WebProgramming course.
 * Was made public for illustrative purposes and for support. 
 * Represents a university examination certificate of the University of Trento 
 * course Computer Science.
 * 
 * No cut-and-paster. Don't be a drunk monkey, learns.
 */

package com.secondoprogetto.model;

import java.sql.Timestamp;

/**
 *
 * @author ©Povodev
 */
public class Post {
    private int id;
    private String testo;
    private int id_utente;
    private int id_gruppo;
    private Timestamp date_time;
    private String file;
    private String nome_utente;
    private String nome_gruppo;
    private String avatar_utente;
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the testo
     */
    public String getTesto() {
        return testo;
    }

    /**
     * @param testo the testo to set
     */
    public void setTesto(String testo) {
        this.testo = testo;
    }

    /**
     * @return the id_utente
     */
    public int getId_utente() {
        return id_utente;
    }

    /**
     * @param id_utente the id_utente to set
     */
    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
    }

    /**
     * @return the id_gruppo
     */
    public int getId_gruppo() {
        return id_gruppo;
    }

    /**
     * @param id_gruppo the id_gruppo to set
     */
    public void setId_gruppo(int id_gruppo) {
        this.id_gruppo = id_gruppo;
    }

    /**
     * @return the date_time
     */
    public Timestamp getDate_time() {
        return date_time;
    }

    /**
     * @param date_time the date_time to set
     */
    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * @return the nome_utente
     */
    public String getNome_utente() {
        return nome_utente;
    }

    /**
     * @param nome_utente the nome_utente to set
     */
    public void setNome_utente(String nome_utente) {
        this.nome_utente = nome_utente;
    }

    /**
     * @return the avatar_utente
     */
    public String getAvatar_utente() {
        return avatar_utente;
    }

    /**
     * @param avatar_utente the avatar_utente to set
     */
    public void setAvatar_utente(String avatar_utente) {
        this.avatar_utente = avatar_utente;
    }

    public String getNome_gruppo() {
        return nome_gruppo;
    }

    public void setNome_gruppo(String nome_gruppo) {
        this.nome_gruppo = nome_gruppo;
    }
}
