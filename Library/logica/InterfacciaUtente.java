/*
 * Copyright (c) 2021-2021.
 * Authors: Ghirimoldi Luca && Carlomagno Stefano && Esposito Andrea
 */

package Library.logica;

import Library.BibliotecaCollectioInterface;
import Library.attori.*;
import Library.attori.materialedisponibile.Film;
import Library.attori.materialedisponibile.Libro;
import Library.attori.materialedisponibile.Musica;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class InterfacciaUtente extends Logica implements BibliotecaCollectioInterface {
    private Logica l;
    private ImpostaSistema is;

    public void mainloop(Logica l,ImpostaSistema is) throws IOException {
        this.l = l;
        this.is = is;
        menu();
    }

    public void menu() throws IOException {

        int scelta;
        System.out.println("-------------| GESTIONE BIBLIOTECA |-------------");
        System.out.println("| Menu:");
        System.out.println("| [1] Utente  |  [2] Amministratore |");
        System.out.print("| Scegli un'opzione: ");
        scelta = inputInt();
        switch (scelta) {
            case 1: {
                loginUtente();
                break;
            }
            case 2: {
                loginAdmin();
                break;
            }
        }
    }

    private void loginUtente() throws IOException {
        ImpostaSistema is = new ImpostaSistema();
        int attempt = 0;
        System.out.print("Hai un account? Y/N: ");
        char risp = Character.toLowerCase(sc.next().charAt(0));
        if (risp == 'y') {
            do {
                if (l.accounts.size() != 0) {
                    Login ln = new Login();
                    System.out.print("Inserisci il nome utente: ");
                    String nomeUtente = sc.next();
                    System.out.print("Inserisci la password: ");
                    String password = sc.next();
                    Utente userLogged = ln.LoginUtente(l, nomeUtente, password); //se esiste un account con questi dati
                    if (userLogged != null) {
                        userPanel(userLogged);  //accesso al pannello utente loggato
                        attempt=3;
                    } else {
                        System.out.println("Hai sbagliato le credenziali di accesso, Riprova! ");
                        attempt++;
                    }
                } else attempt = 3;
            } while (attempt < 3);
        } else {
            System.out.println("Registrati! ");
            Utente utente = is.newUtente(l);
            userPanel(utente);
        }
    }

    private void loginAdmin() throws IOException {
        int attempt = 0;
        do {
            System.out.print("Inserisci la password: ");
            Login lg = new Login(sc.next());
            boolean check = lg.check();
            if (check) {
                adminPanel();  //accesso al pannello admin
                attempt = 3;
            } else {
                System.out.println("Password sbagliata, riprova!");
                attempt++;
            }
        } while (attempt < 3);
    }

    private void userPanel(Utente user) throws IOException {
        int idUser = l.utenti.indexOf(user);
        boolean ripeti = true;
        do {
            System.out.println("|-------- PANNELLO UTENTE --------|");
            System.out.println("|-------------------------------- |");
            System.out.println("|   [0] Visualizza Info utente    |");
            System.out.println("|   [1] Visualizza Provincia      |");
            System.out.println("|   [2] Visualizza Sedi           |");
            System.out.println("|   [3] Visualizza Personale      |");
            System.out.println("|   [4] Visualizza Libri          |");
            System.out.println("|   [5] Visualizza Cd audio       |");
            System.out.println("|   [6] Visualizza Film           |");
            System.out.println("|   [7] Visualizza Autori         |");
            System.out.println("|   [8] Visualizza Editori        |");
            System.out.println("|   [9] Prenota Libri             |");
            System.out.println("|   [10] Prenota Cd audio         |");
            System.out.println("|   [11] Prenota Film             |");
            System.out.println("|   [12] Visualizza Orario        |");
            System.out.println("|   [] Premi un tasto per uscire: |");
            System.out.println("|---------------------------------|");
            System.out.print(" Scegli un opzione: ");
            switch (sc.next()) {
                case "0":{
                    try {
                        boolean flag = false;
                        if (user.getMusica()!=0){
                            flag=true;
                            System.out.println("Hai in prestito questo cd audio: "+ l.musica.get(user.getMusica()).toString());
                        }
                        if (user.getFilm()!=0) {
                            flag=true;
                            System.out.println("Hai in prestito questo film: "+ l.film.get(user.getFilm()));
                        }
                        if(user.getLibriPrenotati().size()!=0){
                            flag=true;
                            System.out.println("Hai prenotato questi libri: ");
                            for (Libro i:user.getLibriPrenotati()){
                                System.out.println("- "+i.toString());
                            }
                        }
                        if (!flag) throw new Exception();
                    }
                    catch (Exception e) {
                        System.out.println("Non hai nessun dato");
                    }
                    break;
                }
                case "1":{
                    l.stampaProvincia();
                    break;
                }
                case "2":{
                    for (Sede i : l.sedi) System.out.println(i.toString());
                    break;
                }
                case "3":{
                    for (Sede i : l.sedi) {
                        System.out.println("[ Sede: "+(l.sedi.indexOf(i)+1)+" "+ i.getNome()+" ]");
                            for(Personale j:i.getPersonale()) System.out.println(j.toString());
                    }
                    break;
                }
                case "4" : {
                    for (Libro i : l.libri) System.out.println(i.stampaLibro(l));
                    break;
                }
                case "5" : {
                    for (Musica i : l.musica) System.out.println(i.toString());
                    break;
                }
                case "6" : {
                    for (Film i : l.film) System.out.println(i.toString());
                    break;
                }
                case "7":{
                    for (Autore i : l.autori) System.out.println(i.toString());
                    break;
                }
                case "8":{
                    for (Editore i : l.editori) System.out.println(i.toString());
                    break;
                }
                case "9" : {
                    System.out.println("Per cosa vuoi cercare il libro: \n [1] Per titolo, [2] Per autore, [3] Per editore");
                    int y = inputInt();
                    switch (y) {
                        case 1 : {
                            for (Libro i : l.libri) System.out.println("[" + l.libri.indexOf(i) + "] " + i.getTitolo() + " " + i.getStato());
                            System.out.print("Quale libro vuoi prenotare?: ");
                            prenotazioni(user,idUser,inputInt());
                            break;
                        }
                        case 2:{
                            int j =0;
                            for (Autore i:l.autori ){
                                System.out.println("["+j+"] "+i.getNome() + " "+i.getCognome());
                                j++;
                            }
                            System.out.print("Scegli un autore: ");
                            Autore auScelto = l.autori.get(sc.nextInt());
                            if(auScelto.getLibri().size()!=0){
                                for (Libro i : auScelto.getLibri()) System.out.println("["+auScelto.getLibri().indexOf(i)+"] "+i.getTitolo()+" "+i.getStato());
                                System.out.print("Quale libro vuoi prenotare?: ");
                                prenotazioni(user,idUser,l.libri.indexOf(l.libri.get(inputInt())));
                            } else System.out.println("Questo autore non ha scritto libri");
                            break;
                        }
                        case 3:{
                            int j =0;
                            for (Editore i:l.editori ){
                                System.out.println("["+j+"] "+i.getNome());
                                j++;
                            }
                            System.out.print("Scegli un editore: ");
                            Editore edScelto = l.editori.get(sc.nextInt());

                            if(edScelto.getLibri().size()!=0){
                                for (Libro i : edScelto.getLibri()) System.out.println("["+edScelto.getLibri().indexOf(i)+"] "+i.getTitolo()+" "+i.getStato());
                                System.out.print("Quale libro vuoi prenotare?: ");
                                prenotazioni(user,idUser,l.libri.indexOf(l.libri.get(inputInt())));
                            } else System.out.println("Questo editore non ha scritto libri");
                        }
                    }
                    break;
                }
                case "10" : {
                    for (Musica i : l.musica) {
                        System.out.println("[" + l.musica.indexOf(i) + "] " + i.getTitolo() + " " + i.getStato());
                    }
                    System.out.println("Quale cd audio vuoi prenotare?: ");
                    int x = inputInt();
                    switch (l.musica.get(x).getStato()) {
                        case STATO_DISPONIBILE: {
                            l.musica.get(x).setStato(STATO_PRENOTATO);
                            editFile(MUSICAFILE, x, STATO_PRENOTATO);
                            user.setMusica(x);
                            System.out.println("Il cd audio è stato prenotato da: " + user.getCognome());
                            break;
                        }
                        case STATO_A_CONSULTAZIONE: {
                            System.out.println("Il cd audio e' in consultazione e non può essere prenotato");
                            break;
                        }
                        case STATO_PRENOTATO: {
                            System.out.println("Il cd audio e' gia stato prenotato e non puo' essere prenotato");
                            break;
                        }
                        case STATO_IN_PRESTITO: {
                            System.out.println("Il cd audio è in prestito e non può essere prenotato");
                            break;
                        }
                    }

                    break;
                }
                case "11" : {
                    for (Film i : l.film) {
                        System.out.println("[" + l.film.indexOf(i) + "] " + i.getTitolo() + " " + i.getStato());
                    }
                    System.out.println("Quale film vuoi prenotare?: ");
                    int x = inputInt();
                    switch (l.film.get(x).getStato()) {
                        case STATO_DISPONIBILE: {
                            l.film.get(x).setStato(STATO_PRENOTATO);
                            editFile(FILMFILE, x, STATO_PRENOTATO);
                            user.setFilm(x);
                            System.out.println("Il film è stato prenotato da: " + user.getCognome());
                            break;
                        }
                        case STATO_A_CONSULTAZIONE: {
                            System.out.println("Il film e' in consultazione e non può essere prenotato");
                            break;
                        }
                        case STATO_PRENOTATO: {
                            System.out.println("Il film e' gia stato prenotato e non puo' essere prenotato");
                            break;
                        }
                        case STATO_IN_PRESTITO: {
                            System.out.println("Il film è in prestito e non può essere prenotato");
                            break;
                        }
                    }

                    break;
                }
                case "12":{
                    l.sedi.get(0).stampaOrario();
                    break;
                }
                default : {
                    System.out.println("Sei uscito dal Pannello Utente");
                    ripeti = false;
                    break;
                }
            }

        } while (ripeti);
    }

    private void adminPanel() throws IOException {
        boolean ripeti = true;
        do {
            System.out.println("|---------------- PANNELLO AMMINISTRATORE ---------------|");
            System.out.println("|--------------------------------------------------------|");
            System.out.println("|                 [0] Modifica provincia                 |");
            System.out.println("|   [1] Aggiungi sede        |   [8] Rimuovi sede        |");
            System.out.println("|   [2] Aggiungi personale   |   [9] Licenzia personale  |");
            System.out.println("|   [3] Aggiungi libro       |   [10] Rimuovi libro      |");
            System.out.println("|   [4] Aggiungi cd audio    |   [11] Rimuovi cd audio   |");
            System.out.println("|   [5] Aggiungi film        |   [12] Rimuovi film       |");
            System.out.println("|   [6] Aggiungi autore      |   [13] Rimuovi autore     |");
            System.out.println("|   [7] Aggiungi editor      |   [14] Rimuovi editore    |");
            System.out.println("|                                                        |");
            System.out.println("|   [15] Visualizza utenti   |   [16] Visualizza Account |");
            System.out.println("|              [] Premi un tasto per uscire:             |");
            System.out.println("|--------------------------------------------------------|");
            System.out.print("| Scegli un opzione: ");
            switch (sc.next()) {
                case "0": {
                    is.newProvincia();
                    break;
                }
                case "1": {
                    System.out.println("Inserisci i dati della nuova sede:");
                    is.newSede();
                    break;
                }
                case "2": {
                    System.out.println("Scegli la sede a cui aggiungere un personale: ");
                    for (Sede i : l.sedi) System.out.println("[" + l.sedi.indexOf(i) + "] " + i.getNome());
                    int scelta = inputInt();
                    System.out.println("Inserisci i dati del personale:");
                    Personale p = is.newPersonale(scelta);
                    l.sedi.get(scelta).addPersonale(p);
                    break;
                }
                case "3": {
                    System.out.println("Inserisci i dati del nuovo libro:");
                    is.newLibro();
                    break;
                }
                case "4": {
                    System.out.println("Inserisci i dati del nuovo cd audio:");
                    is.newMusica();
                    break;
                }
                case "5": {
                    System.out.println("Inserisci i dati del nuovo film:");
                    is.newFilm();
                    break;
                }
                case "6": {
                    System.out.println("Inserisci i dati del nuovo autore:");
                    is.newAutore();
                    break;
                }
                case "7": {
                    System.out.println("Inserisci i dati del nuovo editore:");
                    is.newEditore();
                    break;
                }
                case "8": {
                    System.out.println("Scegli la sede da rimuovere: ");
                    for (Sede i : l.sedi) System.out.println("[" + l.sedi.indexOf(i) + "] " + i.getNome());
                    int toRemove = inputInt();
                    if(deleteElementi(SEDIFILE,toRemove)){
                        l.sedi.remove(toRemove);
                        System.out.println("La sede è stata eliminata");
                    } else{
                        System.out.println("C'è stato un errore nell'eliminazione della sede");
                    }
                    break;
                }
                case "9": {
                    System.out.println("Scegli la sede da dove licenziare il personale: ");
                    for (Sede i : l.sedi) System.out.println("[" + l.sedi.indexOf(i) + "] " + i.getNome());
                    int sedeSelected =inputInt();
                    System.out.println("Chi vuoi licenziare: ");
                    for(Personale i:l.sedi.get(sedeSelected).getPersonale()) System.out.println("["+l.sedi.get(sedeSelected).getPersonale().indexOf(i)+"] " + i.getCognome()+" "+i.getNome());
                    int toRemove=inputInt();
                    if(deleteElementi(sedeSelected,toRemove)){
                        l.sedi.get(sedeSelected).getPersonale().remove(toRemove);
                        System.out.println("La persona è stata licenziata");
                    } else System.out.println("C'è stato un errore nel licenziamento della persona");
                    break;
                }
                case "10": {
                    System.out.println("Scegli il libro da rimuovere: ");
                    for (Libro i : l.libri) System.out.println("[" + l.libri.indexOf(i) + "] " + i.getTitolo());
                    int toRemove = inputInt();
                    if(deleteElementi(LIBRIFILE,toRemove)){
                        l.libri.remove(toRemove);
                        System.out.println("Il libro è stato eliminato");
                    } else System.out.println("C'è stato un errore nell'eliminazione del libro");
                    break;
                }
                case "11": {
                    System.out.println("Scegli il Cd da rimuovere: ");
                    for (Musica i : l.musica) System.out.println("[" + l.musica.indexOf(i) + "] " + i.getTitolo());
                    int toRemove = inputInt();
                    if(deleteElementi(MUSICAFILE,toRemove)){
                        l.musica.remove(toRemove);
                        System.out.println("Il cd è stato eliminato");
                    } else System.out.println("C'è stato un errore nell'eliminazione del cd");
                    break;
                }
                case "12": {
                    System.out.println("Scegli il film da rimuovere: ");
                    for (Film i : l.film) System.out.println("[" + l.film.indexOf(i) + "] " + i.getTitolo());
                    int toRemove = inputInt();
                    if(deleteElementi(FILMFILE,toRemove)){
                        l.film.remove(toRemove);
                        System.out.println("Il film è stato eliminato");
                    } else System.out.println("C'è stato un errore nell'eliminazione del film");
                    break;
                }
                case "13": {
                    System.out.println("Scegli l'autore da rimuovere: ");
                    for (Autore i : l.autori) System.out.println("[" + l.autori.indexOf(i) + "] " + i.getNome());
                    int toRemove = inputInt();
                    if(deleteElementi(AUTORIFILE,toRemove)){
                        l.autori.remove(toRemove);
                        System.out.println("L'autore è stato rimosso");
                    } else System.out.println("C'è stato un errore nella rimozione dell'autore");

                    break;
                }
                case "14": {
                    System.out.println("Scegli l'editore da rimuovere: ");
                    for (Editore i : l.editori) System.out.println("[" + l.editori.indexOf(i) + "] " + i.getNome());
                    int toRemove = inputInt();
                    if(deleteElementi(EDITORIFILE,toRemove)){
                        l.editori.remove(toRemove);
                        System.out.println("L'editore è stato rimosso");
                    } else System.out.println("C'è stato un errore nella rimozione dell'editore");

                    break;
                }
                case "15":{
                    for (Utente i:l.utenti) System.out.println(i.toString());
                    break;
                }
                case "16":{
                    for (Account i:l.accounts) System.out.println(i.toString());
                    break;
                }
                default: {
                    System.out.println("Sei uscito dal Pannello Amministratore");
                    ripeti = false;
                    break;
                }
            }
        } while (ripeti);
    }

    private void editFile(File file, int edit,String stato) throws IOException {
        List<String> listFile = Files.readAllLines(Paths.get(String.valueOf(file)));
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        String toEdit = listFile.get(edit);

        for (String i:listFile){
            String[] arr = i.split(" ");
            arr[0] = stato;
            if(i.equals(toEdit)){
                bw.write(Arrays.toString(arr).replace("[","").replace("]","").replace(",","")+"\n");
            }else bw.write(i+"\n");

        }
        bw.close();
    }

    private void prenotazioni(Utente user,int idUser,int x) throws IOException {
        switch (l.libri.get(x).getStato()) {
            case STATO_DISPONIBILE: {
                l.libri.get(x).setStato(STATO_PRENOTATO);
                editFile(LIBRIFILE, x, STATO_PRENOTATO);
                editUserFile(x, idUser);
                l.libri.get(x).setProprietario(idUser);
                user.addLibro(l.libri.get(x));
                System.out.println("Il libro è stato prenotato da: " + user.getCognome());
                break;
            }
            case STATO_A_CONSULTAZIONE: {
                System.out.println("Il libro non può essere prenotato");
                break;
            }
            case STATO_PRENOTATO: {
                l.libri.get(x).prenotato(idUser);
                break;
            }
            case STATO_IN_PRESTITO: {
                System.out.println("Il libro è in prestito e non può essere prenotato");
                break;
            }

        }
    }

    private void editUserFile ( int edit, int indexOf) throws IOException {
        List<String> listFile = Files.readAllLines(Paths.get(String.valueOf(LIBRIFILE)));
        BufferedWriter bw = new BufferedWriter(new FileWriter(LIBRIFILE));
        String toEdit = listFile.get(edit);

        for (String i:listFile){
            String[] arr = i.split(" ");
            arr[3] = String.valueOf(indexOf);
            if(i.equals(toEdit)){ //se diverso aggiorna la riga
                bw.write(Arrays.toString(arr).replace("[","").replace("]","").replace(",","")+"\n");
            }else {
                bw.write(i+"\n");
            }
        }
        bw.close();
    }

    //eliminare una determinata riga
    private boolean deleteElementi(File file, int edit)  {
        try{
            List<String> old = Files.readAllLines(Paths.get(String.valueOf(file)));
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            String toRemove = old.get(edit);
            for (String i:old) if(!i.equals(toRemove)) bw.write(i+"\n");
            bw.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }
    //eliminare una determinata riga
    private boolean deleteElementi(int sede, int edit) {
        try{
            List<String> old = Files.readAllLines(Paths.get(String.valueOf(PERSONALIFILE)));
            BufferedWriter bw = new BufferedWriter(new FileWriter(PERSONALIFILE));
            String toRemove ="";
            int pos = -1;
            for (String k:old){
                String[] arr = k.split(" ");
                if (Integer.parseInt(arr[0]) == sede){
                    pos++;
                    if(pos == edit) toRemove=k;
                }
            }
            System.out.println("toremove: "+toRemove);
            for (String i:old) if(!i.equals(toRemove)) bw.write(i+"\n");
            bw.close();
            return true;
        } catch (Exception e){
            return false;
       }
    }

}