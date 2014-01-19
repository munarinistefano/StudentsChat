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
public class Inviti {
    private int id_gruppo;
    private String nome_gruppo;
    private Timestamp data_creazione;
    private String proprietario;
    private int stato;

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
     * @return the nome_gruppo
     */
    public String getNome_gruppo() {
        return nome_gruppo;
    }

    /**
     * @param nome_gruppo the nome_gruppo to set
     */
    public void setNome_gruppo(String nome_gruppo) {
        this.nome_gruppo = nome_gruppo;
    }

    /**
     * @return the data_creazione
     */
    public Timestamp getData_creazione() {
        return data_creazione;
    }

    /**
     * @param data_creazione the data_creazione to set
     */
    public void setData_creazione(Timestamp data_creazione) {
        this.data_creazione = data_creazione;
    }

    /**
     * @return the proprietario
     */
    public String getProprietario() {
        return proprietario;
    }

    /**
     * @param proprietario the proprietario to set
     */
    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    /**
     * @return the stato
     */
    public int getStato() {
        return stato;
    }

    /**
     * @param stato the stato to set
     */
    public void setStato(int stato) {
        this.stato = stato;
    }
    
}
