/*
 * Copyright (c) 2021-2021.
 * Authors: Ghirimoldi Luca && Carlomagno Stefano && Esposito Andrea
 */

package Library.logica;

import Library.attori.*;
import Library.attori.materialedisponibile.Film;
import Library.attori.materialedisponibile.Libro;
import Library.attori.materialedisponibile.Musica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Logica {
    static Scanner sc = new Scanner(System.in);
    public Provincia provincia;
    final public List<Sede> sedi = new ArrayList<>();
    final public List<Libro> libri = new ArrayList<>();
    final public List<Film> film = new ArrayList<>();
    final public List<Musica> musica = new ArrayList<>();
    final public List<Autore> autori = new ArrayList<>();
    final public List<Editore> editori = new ArrayList<>();
    final public List<Utente> utenti = new ArrayList<>();
    final protected List<Account> accounts = new ArrayList<>();

    public void gestione(Logica logica) throws IOException {
        final ImpostaSistema impostaSistema = new ImpostaSistema();
        final InterfacciaUtente interfaccia = new InterfacciaUtente();

        impostaSistema(logica,impostaSistema);
        interfaccia(logica,interfaccia,impostaSistema);
    }
    private void impostaSistema(Logica l,ImpostaSistema is) throws IOException {
        is.imposta(l);
    }

    private void interfaccia(Logica l, InterfacciaUtente iu, ImpostaSistema is) throws IOException {
        iu.mainloop(l,is);
    }

    // InputInt
    public static int inputInt() {
            while (!sc.hasNextInt()) sc.next();
            return sc.nextInt();
    }

    // InputLong
    public static long inputLong() {
        while (!sc.hasNextLong()) sc.next();
        return sc.nextLong();
    }
    public void stampaProvincia(){
        System.out.println("La provincia è: "+ provincia.getProvincia());
    }

}