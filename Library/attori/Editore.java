/*
 * Copyright (c) 2021-2021.
 * Authors: Ghirimoldi Luca && Carlomagno Stefano && Esposito Andrea
 */

package Library.attori;

import Library.attori.materialedisponibile.Libro;

import java.util.ArrayList;
import java.util.List;


public class Editore {
    private String nome, stato, sedePrincipale, settore;
    private long isin;
    private final List<Libro> libri = new ArrayList<>();

    public Editore() {}

    public Editore(String nome, String stato, String sedePrincipale, String settore, long isin) {
        this.nome = nome;
        this.stato = stato;
        this.sedePrincipale = sedePrincipale;
        this.settore = settore;
        this.isin = isin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getSettore() {
        return settore;
    }

    public void setSettore(String settore) {
        this.settore = settore;
    }

    public long getIsin() {
        return isin;
    }

    public void setIsin(long isin) {
        this.isin = isin;
    }

    public String getSedePrincipale() {
        return sedePrincipale;
    }

    public void setSedePrincipale(String sedePrincipale) {
        this.sedePrincipale = sedePrincipale;
    }

    public void addLibro(Libro libro) {
        this.libri.add(libro);
    }

    public List<Libro> getLibri() {
        return libri;
    }


    @Override
    public String toString() {
        return "Nome: "+nome+", Stato: "+stato+", Sede principale: "+sedePrincipale+", Settore: "+settore+", Isin: "+isin;
    }
}