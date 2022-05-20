/*
 * Copyright (c) 2021-2021.
 * Authors: Ghirimoldi Luca && Carlomagno Stefano && Esposito Andrea
 */

package Library;

import Library.logica.Logica;
import java.io.IOException;

public class GestioneBiblioteca implements BibliotecaCollectioInterface {

    //  Non inserire parole con spazi

    //  Dati utenti :
    //      Nome: luca Pw: 1234
    //      Nome: espo Pw: ste

    //  Dati amministratore :
    //      Pw: admin

    public static void main(String[] args) throws IOException {
        final Logica logica = new Logica();
        logica.gestione(logica);
    }
}