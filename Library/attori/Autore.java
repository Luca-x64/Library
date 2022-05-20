/*
 * Copyright (c) 2021-2021.
 * Authors: Ghirimoldi Luca && Carlomagno Stefano && Esposito Andrea
 */

package Library.attori;

import Library.attori.materialedisponibile.Libro;

import java.util.ArrayList;
import java.util.List;


public class Autore {
    private String nome, cognome, nazionalita, dataNascita;
    private final List<Libro> libri = new ArrayList<>();

    public Autore(String nome, String cognome, String nazionalita, String dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.nazionalita = nazionalita;
        this.dataNascita = dataNascita;
    }

    public Autore() {}
    public void addLibro(Libro libro) {
        this.libri.add(libro);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public List<Libro> getLibri() {
        return libri;
    }

    @Override
    public String toString() {
        return "Nome: "+nome+", Cognome: "+cognome+", Nazionalita': "+nazionalita+", Data di nascita: "+dataNascita;
    }
}