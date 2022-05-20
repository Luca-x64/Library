/*
 * Copyright (c) 2021-2021.
 * Authors: Ghirimoldi Luca && Carlomagno Stefano && Esposito Andrea
 */

package Library.attori;

import Library.attori.materialedisponibile.Libro;

import java.util.ArrayList;
import java.util.List;

public class Utente {

    private String nome, cognome, citta;
    private int musica, film;
    private long numerotelefono;
    private final List<Libro> libriPrenotati = new ArrayList<>();

    public Utente(String nome, String cognome, String citta, long numerotelefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.citta = citta;
        this.numerotelefono = numerotelefono;
    }

    public Utente() {

    }

    public List<Libro> getLibriPrenotati() {
        return libriPrenotati;
    }

    public int getMusica() {
        return musica;
    }

    public int getFilm() {
        return film;
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

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public long getNumerotelefono() {
        return numerotelefono;
    }

    public void setNumerotelefono(long numerotelefono) {
        this.numerotelefono = numerotelefono;
    }

    public void setMusica(int musica) {
        this.musica = musica;
    }

    public void setFilm(int film) {
        this.film = film;
    }

    public void addLibro(Libro i){
        libriPrenotati.add(i);
    }

    @Override
    public String toString() {
        return "Nome: "+nome+", Cognome: "+cognome+", Citta': "+citta+", Numero telefonico: "+numerotelefono;
    }
}