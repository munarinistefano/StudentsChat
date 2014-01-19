/*
 * This project was deploy by ©povodev for WebProgramming course.
 * Was made public for illustrative purposes and for support. 
 * Represents a university examination certificate of the University of Trento 
 * course Computer Science.
 * 
 * No cut-and-paster. Don't be a drunk monkey, learns.
 */
package com.secondoprogetto.model;

import java.sql.Date;

/**
 *
 * @author ©Povodev
 */
public class Gruppo {
    private int ID;
    private String nome;
    private Date data_creazione;
    private int flag;      
    private int ID_proprietario;
    private String nome_proprietario;
    private int numero_post;
    private int stato;      

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the data_creazione
     */
    public Date getData_creazione() {
        return data_creazione;
    }

    /**
     * @param data_creazione the data_creazione to set
     */
    public void setData_creazione(Date data_creazione) {
        this.data_creazione = data_creazione;
    }

    /**
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

    /**
     * @return the ID_proprietario
     */
    public int getID_proprietario() {
        return ID_proprietario;
    }

    /**
     * @param ID_proprietario the ID_proprietario to set
     */
    public void setID_proprietario(int ID_proprietario) {
        this.ID_proprietario = ID_proprietario;
    }

    /**
     * @return the nome_proprietario
     */
    public String getNome_proprietario() {
        return nome_proprietario;
    }

    /**
     * @param nome_proprietario the nome_proprietario to set
     */
    public void setNome_proprietario(String nome_proprietario) {
        this.nome_proprietario = nome_proprietario;
    }

    public int getNumero_post() {
        return numero_post;
    }

    public void setNumero_post(int numero_post) {
        this.numero_post = numero_post;
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
