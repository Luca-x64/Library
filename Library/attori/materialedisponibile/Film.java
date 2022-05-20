/*
 * Copyright (c) 2021-2021.
 * Authors: Ghirimoldi Luca && Carlomagno Stefano && Esposito Andrea
 */

package Library.attori.materialedisponibile;

import Library.BibliotecaCollectioInterface;
import Library.attori.Sede;

public class Film implements BibliotecaCollectioInterface {
    private String titolo, lingua, genere, regista, stato = STATO_DISPONIBILE;
    private int anno, durata;
    private Sede sedeAppartenenza;

    public Film() {
    }

    public Film(String stato, String titolo, String lingua, String genere, String regista, int anno, int durata, Sede sedeAppartenenza) {
        this.stato =stato;
        this.titolo = titolo;
        this.lingua = lingua;
        this.genere = genere;
        this.regista = regista;
        this.anno = anno;
        this.durata = durata;
        this.sedeAppartenenza = sedeAppartenenza;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getRegista() {
        return regista;
    }

    public void setRegista(String regista) {
        this.regista = regista;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public Sede getSedeAppartenenza() {
        return sedeAppartenenza;
    }

    public void setSedeAppartenenza(Sede sedeAppartenenza) {
        this.sedeAppartenenza = sedeAppartenenza;
    }

    @Override
    public String toString() {
        return "Titolo: "+titolo+", Lingua: "+lingua+", Genere: "+genere+", Regista: "+regista +", Anno: "+anno+", Durata: "+durata+", Sede: "+sedeAppartenenza.getNome()+", Stato: "+stato;
    }

}