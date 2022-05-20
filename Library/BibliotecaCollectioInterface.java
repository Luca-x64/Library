/*
 * Copyright (c) 2021-2021.
 * Authors: Ghirimoldi Luca && Carlomagno Stefano && Esposito Andrea
 */

package Library;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface BibliotecaCollectioInterface {

        File PROVFILE = new File("inputdata/provincia.txt");
        File SEDIFILE = new File("inputdata/sedi.txt");
        File PERSONALIFILE = new File("inputdata/personale.txt");
        File AUTORIFILE = new File("inputdata/autori.txt");
        File EDITORIFILE = new File("inputdata/editori.txt");
        File LIBRIFILE = new File("inputdata/libri.txt");
        File MUSICAFILE = new File("inputdata/musica.txt");
        File FILMFILE = new File("inputdata/film.txt");
        File UTENTIFILE = new File("inputdata/utenti.txt");

        String STATO_DISPONIBILE = "DISPONIBILE";
        String STATO_IN_PRESTITO = "PRESTITO";
        String STATO_PRENOTATO = "PRENOTATO";
        String STATO_IN_TRANSITO = "TRANSITO";
        String STATO_A_CONSULTAZIONE = "CONSULTAZIONE";

        List<String> ORARIO = new ArrayList<>(List.of("Lunedì:", "8:00-12:00", "Martedì:", "8:00-12:00 | 14:00-18:00", "Mercoledì:", "8:00-12:00 | 14:00-18:00", "Giovedì:", "8:00-12:00 | 14:00-18:00", "Venerdì:", "8:00-12:00 | 14:00-18:00", "Sabato:", "14:00-19:00", "Domenica:", "Chiuso"));
}