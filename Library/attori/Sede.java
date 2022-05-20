/*
 * Copyright (c) 2021-2021.
 * Authors: Ghirimoldi Luca && Carlomagno Stefano && Esposito Andrea
 */

package Library.attori;

import Library.BibliotecaCollectioInterface;

import java.util.*;

public class Sede extends Provincia implements BibliotecaCollectioInterface {
    private String nome, citta, mail, indirizzoVia;
    private long telefono;
    private int indirizzoCivico;
    private final List<Personale> personale = new ArrayList<>();

    public Sede(String nme, String city, String via, int civ, String mail, long tel) {
        this.nome = nme;
        this.citta = city;
        this.indirizzoVia = via;
        this.indirizzoCivico = civ;
        this.mail = mail;
        this.telefono = tel;
    }

    public Sede() {
    }

    public String getIndirizzoVia() {
        return indirizzoVia;
    }

    public void setIndirizzoVia(String indirizzoVia) {
        this.indirizzoVia = indirizzoVia;
    }

    public int getIndirizzoCivico() {
        return indirizzoCivico;
    }

    public void setIndirizzoCivico(int indirizzoCivico) {
        this.indirizzoCivico = indirizzoCivico;
    }

    public void addPersonale(Personale pr) {
        this.personale.add(pr);
    }

    public List<Personale> getPersonale() {
        return personale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Nome: "+nome+", Citta': "+ citta +", Via: "+ indirizzoVia +", Civico: "+ indirizzoCivico +", Mail: "+ mail +", Telefono: "+ telefono;
    }

    public void stampaOrario() {
        System.out.println("Orario:");
        for(int i = 0; i<ORARIO.size()-1;i=i+2) System.out.println(ORARIO.get(i) + ORARIO.get(i+1));
    }
}