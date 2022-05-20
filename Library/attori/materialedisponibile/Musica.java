/*
 * Copyright (c) 2021-2021.
 * Authors: Ghirimoldi Luca && Carlomagno Stefano && Esposito Andrea
 */

package Library.attori.materialedisponibile;

import Library.BibliotecaCollectioInterface;
import Library.attori.Sede;

public class Musica implements BibliotecaCollectioInterface {
    private String titolo, artista, genere, durata, stato = STATO_DISPONIBILE;
    private Sede sedeAppartenenza;

    public Musica(String stato, String titolo, String artista, String genere, String durata, Sede sede) {
        this.stato =stato;
        this.titolo = titolo;
        this.artista = artista;
        this.genere = genere;
        this.durata = durata;
        this.sedeAppartenenza = sede;
    }

    public Musica() {
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

    public Sede getSedeAppartenenza() {
        return sedeAppartenenza;
    }

    public void setSedeAppartenenza(Sede sedeAppartenenza) {
        this.sedeAppartenenza = sedeAppartenenza;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    @Override
    public String toString() {
        return "Titolo: "+titolo+", Artista: "+artista+", Genere: "+genere+", Durata: "+durata +", Sede: "+sedeAppartenenza.getNome()+", Stato: "+stato;
    }
}
