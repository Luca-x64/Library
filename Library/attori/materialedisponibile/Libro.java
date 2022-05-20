/*
 * Copyright (c) 2021-2021.
 * Authors: Ghirimoldi Luca && Carlomagno Stefano && Esposito Andrea
 */

package Library.attori.materialedisponibile;

import Library.BibliotecaCollectioInterface;
import Library.attori.Autore;
import Library.attori.Editore;
import Library.attori.Sede;
import Library.logica.Logica;

import java.util.ArrayList;
import java.util.List;

public class Libro implements BibliotecaCollectioInterface {
    private String titolo, stato = STATO_DISPONIBILE;
    private long isbn;
    int proprietario = -1;
    private Sede sedeAppartenenza;
    private Autore autore;
    private Editore editore;
    private final List<Integer> prenotato = new ArrayList<>();

    public Libro(String stato, String titolo, long isbn,int prop, Sede sedeAppartenenza, Autore autore, Editore editore) {
        this.stato=stato;
        this.titolo = titolo;
        this.isbn = isbn;
        this.proprietario=prop;
        this.sedeAppartenenza = sedeAppartenenza;
        this.autore = autore;
        this.editore = editore;
    }

    public Libro() {
    }


    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public Sede getSedeAppartenenza() {
        return sedeAppartenenza;
    }

    public void setSedeAppartenenza(Sede sedeAppartenenza) {
        this.sedeAppartenenza = sedeAppartenenza;
    }

    public Autore getAutore() {
        return autore;
    }

    public void setAutore(Autore autore) {
        this.autore = autore;
    }

    public Editore getEditore() {
        return editore;
    }

    public void setEditore(Editore editore) {
        this.editore = editore;
    }

    public int getProprietario() {
        return proprietario;
    }

    public void setProprietario(int proprietario) {
        this.proprietario = proprietario;
    }

    public void prenotato(int idUser){
        if (proprietario == idUser) System.out.println("Non puoi prenotare questo libro, perchè lo hai gia in posseso.");
        else {
            if(prenotato.size() > 3) System.out.println("Il libro non può essere prenotato perchè già altri 3 utenti lo hanno prenotato.");
            else{
                prenotato.add(idUser);
                System.out.println("Sei stato messo il lista di attesa per questo libro.");
            }
        }
    }

    private String prestito (Logica l) {
        String r ="Nessuno";
        if(proprietario >=0)
            r = l.utenti.get(proprietario).getNome() + " "+l.utenti.get(proprietario).getCognome();
        return r;
    }

    public String stampaLibro(Logica l) {
        return "Titolo: "+titolo+", Isbn: "+isbn+",In prestito a: "+prestito(l)+", Sede: "+sedeAppartenenza.getNome()+", Autore: "+autore.getNome()+", Editore: "+editore.getNome()+", Stato: "+stato;
    }

    @Override
    public String toString() {
        return "Titolo: "+titolo+", Isbn: "+isbn+", Sede: "+sedeAppartenenza.getNome()+", Autore: "+autore.getNome()+", Editore: "+editore.getNome()+", Stato: "+stato;

    }

}