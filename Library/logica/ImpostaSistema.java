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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ImpostaSistema extends Logica implements BibliotecaCollectioInterface {
    static Scanner sc = new Scanner(System.in);
    private Logica logica;

    public void imposta(Logica l) throws IOException {
        this.logica = l;
        provincia();
        sediPersonale();
        loadUtenti();
        loadAccounts();
        risorse();
    }

    // Crea provincia
    public void provincia() throws IOException {
        if (PROVFILE.length() != 0) {
            logica.provincia =new Provincia(Files.readAllLines(Paths.get(String.valueOf(PROVFILE))).get(0));
            System.out.print("INPUTS:\n| Provincia OK | ");
        } else newProvincia();
    }

    // Crea sedi
    public void sediPersonale() throws IOException {
        try { //prova se ci sono le sedi e se riesce a prendere i dati
            List<String> listSedi = Files.readAllLines(Paths.get(String.valueOf(SEDIFILE)));
            if (listSedi.size() != 0) {
                for (int i = 0; i < listSedi.size(); i++) {
                    String[] iS = listSedi.get(i).split(" ");
                    Sede sede = new Sede(iS[0], iS[1], iS[2], Integer.parseInt(iS[3]), iS[4], Long.parseLong(iS[5]));
                    logica.sedi.add(sede);
                    personale(sede, i);
                }
                System.out.print("Sedi OK | Personale OK | ");
            } else throw new Exception();

        } catch (Exception e) { //altrimenti crea le sedi
            System.out.print("Inserisci il numero delle sedi:");
            int risp = inputInt();
            for (int i = 0; i < risp; i++) {
                System.out.println("Inserisci i dati della sede " + ++i + ": ");
                Sede sede = newSede();
                personale(sede, i);
            }
        }
    }

    // Crea personale
    public void personale(Sede sede, int i) throws IOException {
        try {
            boolean flag = false;
            if (PERSONALIFILE.length() != 0) {
                List<String> listPersonale = Files.readAllLines(Paths.get(String.valueOf(PERSONALIFILE)));
                for (String j : listPersonale) {
                    String[] iP = j.split(" ");
                    if (Integer.parseInt(iP[0]) == i) {
                        flag = true;
                        Personale personale = new Personale(iP[1], iP[2], iP[3], iP[4], iP[5], iP[6], Integer.parseInt(iP[7]));
                        logica.sedi.get(i).addPersonale(personale);
                    }
                }
            }
            if (!flag) throw new Exception();
        } catch (Exception e) {
            System.out.print("Inserisci il numero del personale della sede " + (i + 1) + ": ");
            int risp = inputInt();
            for (int j = 0; j < risp; j++) {
                System.out.println("Inserisci i dati del personale " + (j + 1) + ": ");
                Personale personale = newPersonale(i);
                sede.addPersonale(personale);
            }
        }
    }

    // Crea risorse
    public void risorse() throws IOException {
        autore();
        editore();
        libro();
        film();
        musica();
    }

    //Crea autori
    protected void autore() throws IOException {
        try {
            List<String> listAutori = Files.readAllLines(Paths.get(String.valueOf(AUTORIFILE)));
            if (listAutori.size() != 0) {
                for (String i : listAutori) {
                    String[] iA = i.split(" ");
                    Autore autore = new Autore(iA[0], iA[1], iA[2], iA[3]);
                    logica.autori.add(autore);
                }
            } else throw new Exception();
            System.out.print("autori OK | ");
        } catch (Exception e) {
            System.out.print("Quanti autori vuoi inserire?: ");
            int risp = inputInt();
            System.out.println(risp);
            for (int j = 0; j < risp; j++) {
                System.out.println("Inserisci i dati dell'autore: " + (j + 1)+": ");
                newAutore();
            }
        }
    }

    // Crea editori
    protected void editore() throws IOException {
        try {
            List<String> listEditori = Files.readAllLines(Paths.get(String.valueOf(EDITORIFILE)));
            if (listEditori.size() != 0) {
                for (String i : listEditori) {
                    String[] iE = i.split(" ");
                    Editore editore = new Editore(iE[0], iE[1], iE[2], iE[3], Long.parseLong(iE[4]));
                    logica.editori.add(editore);
                }
            } else throw new Exception();
            System.out.print("editori OK | ");
        } catch (Exception e) {
            System.out.print("Quanti editori vuoi inserire?: ");
            int risp = inputInt();
            System.out.println(risp);
            for (int j = 0; j < risp; j++) {
                System.out.println("Inserisci i dati dell'editore: " + (j + 1)+": ");
                newEditore();
            }
        }
    }

    // Crea libri
    protected void libro() throws IOException {
        try {
            if (LIBRIFILE.length() != 0) {
                List<String> listLibro = Files.readAllLines(Paths.get(String.valueOf(LIBRIFILE)));
                for (String i : listLibro) {
                    String[] iL = i.split(" ");
                    Libro libro = new Libro(iL[0], iL[1], Long.parseLong(iL[2]),Integer.parseInt(iL[3]), logica.sedi.get(Integer.parseInt(iL[4])), logica.autori.get(Integer.parseInt(iL[5])), logica.editori.get(Integer.parseInt(iL[6])));
                    logica.libri.add(libro);
                    if(Integer.parseInt(iL[3])!=-1){
                        logica.utenti.get(Integer.parseInt(iL[3])).addLibro(libro);
                    }
                    logica.autori.get(Integer.parseInt(iL[5])).addLibro(libro);
                    logica.editori.get(Integer.parseInt(iL[6])).addLibro(libro);
                }
                System.out.print("libri OK | ");
            }else throw new Exception();
       } catch (Exception e) {
            System.out.print("Quanti Libri vuoi inserire?: ");
            int risp = inputInt();
            System.out.println(risp);
            for (int j = 0; j < risp; j++) {
                System.out.println("Inserisci i dati del libro: " + (j + 1)+": ");
                newLibro();
            }
        }
    }

    // Crea film
    protected void film() throws IOException {
        try {
            if (FILMFILE.length() != 0) {
                List<String> listFilm = Files.readAllLines(Paths.get(String.valueOf(FILMFILE)));
                for (String i : listFilm) {
                    String[] iF = i.split(" ");
                    Film film = new Film(iF[0], iF[1], iF[2], iF[3], iF[4], Integer.parseInt(iF[5]), Integer.parseInt(iF[6]), logica.sedi.get(Integer.parseInt(iF[7])));
                    logica.film.add(film);
                }
                System.out.print("film OK | ");
            } else throw new Exception();

        } catch (Exception e) {
            System.out.print("Quanti film vuoi inserire?: ");
            int risp = inputInt();
            System.out.println(risp);
            for (int j = 0; j < risp; j++) {
                System.out.println("Inserisci i dati dell' film: " + (j + 1)+": ");
                newFilm();
            }
        }
    }

    // Crea audio
    protected void musica() throws IOException {
        try {
            if (MUSICAFILE.length() != 0) {
                List<String> listMusica = Files.readAllLines(Paths.get(String.valueOf(MUSICAFILE)));
                for (String i : listMusica) {
                    String[] iM = i.split(" ");
                    Musica musica = new Musica(iM[0], iM[1], iM[2], iM[3], iM[4], logica.sedi.get(Integer.parseInt(iM[5])));
                    logica.musica.add(musica);
                }
                System.out.println("musica OK |");
            } else throw new Exception();
        } catch (Exception e) {
            System.out.print("Quanti cd mucisali vuoi inserire?: ");
            int risp = inputInt();
            System.out.println(risp);
            for (int j = 0; j < risp; j++) {
                System.out.println("Inserisci i dati del cd musicale: " + (j + 1)+": ");
                newMusica();
            }
        }
    }

    // Carica utenti
    public void loadUtenti() {
        try {
            List<String> listUtenti = Files.readAllLines(Paths.get(String.valueOf(UTENTIFILE)));
            if (listUtenti.size() != 0) {
                for (String i : listUtenti) {
                    String[] iU = i.split(" ");
                    Utente user = new Utente(iU[1], iU[2], iU[3], Long.parseLong(iU[4]));
                    logica.utenti.add(user);
                }
                System.out.print(" utenti OK | ");
            } else throw new Exception();
        } catch (Exception e) {
            System.out.println("Errore nel caricare gli utenti!");
        }
    }

    // Carica account
    public void loadAccounts() {
        try {
            List<String> listAccount = Files.readAllLines(Paths.get("inputdata/account.txt"));
            if (listAccount.size() != 0) {
                for (String i : listAccount) {
                    String[] iA = i.split(" ");
                    Account acc = new Account(logica.utenti.get(Integer.parseInt(iA[0])), iA[1], iA[2]);
                    logica.accounts.add(acc);
                }
                System.out.print("account OK | ");
            } else throw new Exception();
        } catch (Exception e) {
            System.out.println("Errore nel caricare gli account!");
        }
    }

    // Crea provincia
    protected void newProvincia() throws IOException {
        System.out.print("Inserisci il nome della provincia: ");
        logica.provincia = new Provincia(sc.next());
        FileWriter provWrite = new FileWriter(PROVFILE);
        provWrite.write(logica.provincia.getProvincia());
        provWrite.close();
    }

    // Crea Sede
    protected Sede newSede() throws IOException {
        Sede s = new Sede();

        System.out.print("Inserisci il nome: ");
        s.setNome(sc.next());
        System.out.print("Inserisci la città: ");
        s.setCitta(sc.next());
        System.out.print("Inserisci l'indirizzo (via): ");
        s.setIndirizzoVia(sc.next());
        System.out.print("Inserisci l'indirizzo (numero civico): ");
        s.setIndirizzoCivico(inputInt());
        System.out.print("Inserisci la mail: ");
        s.setMail(sc.next());
        System.out.print("Inserisci il numero di telefono: ");
        s.setTelefono(inputLong());

        FileWriter sediWrite = new FileWriter(SEDIFILE, true);
        sediWrite.write(s.getNome() + " " + s.getCitta() + " " + s.getIndirizzoVia() + " " + s.getIndirizzoCivico() + " " + s.getMail() + " " + s.getTelefono() + "\n");
        sediWrite.close();
        logica.sedi.add(s);

        return s;
    }

    // Crea Personale
    protected Personale newPersonale(int i) throws IOException {
        Personale p = new Personale();

        System.out.print("Inserisci il nome: ");
        p.setNome(sc.next());
        System.out.print("Inserisci il cognome: ");
        p.setCognome(sc.next());
        System.out.print("Inserisci il ruolo: ");
        p.setRuolo(sc.next());
        System.out.print("Inserisci il turno (mattino/pomeriggio): ");
        p.setTurno(sc.next());
        System.out.print("Inserisci la data di nascità: ");
        p.setDataNascita(sc.next());
        System.out.print("Inserisci la città di nascità: ");
        p.setCittaNascita(sc.next());
        System.out.print("Inserisci il salario: ");
        p.setSalario(inputInt());

        FileWriter fwP = new FileWriter(PERSONALIFILE, true);
        BufferedWriter bwP = new BufferedWriter(fwP);
        bwP.write(i + " " + p.getNome() + " " + p.getCognome() + " " + p.getRuolo() + " " + p.getTurno() + " " + p.getDataNascita() + " " + p.getCittaNascita() + " " + p.getSalario() + "\n");
        bwP.close();
        return p;
    }

    // Crea Autore
    protected void newAutore() throws IOException {
        Autore au = new Autore();

        System.out.print("Inserisci il nome: ");
        au.setNome(sc.next());
        System.out.print("Inserisci il cognome: ");
        au.setCognome(sc.next());
        System.out.print("Inserisci la nazionalita: ");
        au.setNazionalita(sc.next());
        System.out.print("Inserisci la data di nascita: ");
        au.setDataNascita(sc.next());

        FileWriter fwA = new FileWriter(AUTORIFILE, true);
        BufferedWriter bwA = new BufferedWriter(fwA);

        bwA.write(au.getNome() + " " + au.getCognome() + " " + au.getNazionalita() + " " + au.getDataNascita() + "\n");
        bwA.close();
        logica.autori.add(au);
    }

    // Crea Editore
    protected void newEditore() throws IOException {
        Editore ed = new Editore();

        System.out.print("Inserisci il nome: ");
        ed.setNome(sc.next());
        System.out.print("Inserisci lo stato dove si trova: ");
        ed.setStato(sc.next());
        System.out.print("Inserisci il nome della sede Principale: ");
        ed.setSedePrincipale(sc.next());
        System.out.print("Inserisci il settore: ");
        ed.setSettore(sc.next());
        System.out.print("Inserisci il codice isin: ");
        ed.setIsin(inputLong());

        FileWriter fwE = new FileWriter(EDITORIFILE, true);
        BufferedWriter bwE = new BufferedWriter(fwE);

        bwE.write(ed.getNome() + " " + ed.getStato() + " " + ed.getSedePrincipale() + " " + ed.getSettore() + " " + ed.getIsin() + "\n");
        bwE.close();
        logica.editori.add(ed);
    }

    // Crea Libri
    protected void newLibro() throws IOException {
        Libro lb = new Libro();

        System.out.print("Inserisci il titolo: ");
        lb.setTitolo(sc.next());
        System.out.print("Inserisci l'ISBN: ");
        lb.setIsbn(sc.nextLong());
        System.out.println("Scegli la sede: ");
        lb.setSedeAppartenenza(choiseSede(logica.sedi));
        System.out.println("Scegli l'autore: ");
        Autore au = choiseAutore(logica.autori);
        lb.setAutore(au);
        System.out.println("Scegli l'editore: ");
        Editore ed = choiseEditore(logica.editori);
        lb.setEditore(ed);

        au.addLibro(lb);
        ed.addLibro(lb);

        FileWriter fwL = new FileWriter(LIBRIFILE, true);
        BufferedWriter bwL = new BufferedWriter(fwL);

        bwL.write(lb.getStato() + " " + lb.getTitolo() + " " + lb.getIsbn() + " " + lb.getProprietario() + "" + logica.sedi.indexOf(lb.getSedeAppartenenza()) + " " + logica.autori.indexOf(lb.getAutore()) + " " + logica.editori.indexOf(lb.getEditore()) + "\n");
        bwL.close();
        logica.libri.add(lb);
    }

    // Crea Film
    protected void newFilm() throws IOException {
        Film fs = new Film();
        System.out.print("Inserisci il titolo: ");
        fs.setTitolo(sc.next());
        System.out.print("Inserisci la lingua: ");
        fs.setLingua(sc.next());
        System.out.print("Inserisci il genere: ");
        fs.setGenere(sc.next());
        System.out.print("Inserisci il regista: ");
        fs.setRegista(sc.next());
        System.out.print("Inserisci l'anno di pubblicazione: ");
        fs.setAnno(sc.nextInt());
        System.out.print("Inserisci la durata: ");
        fs.setDurata(sc.nextInt());
        System.out.println("Scegli la sede: ");
        fs.setSedeAppartenenza(choiseSede(logica.sedi));

        FileWriter fwF = new FileWriter(FILMFILE, true);
        BufferedWriter bwF = new BufferedWriter(fwF);

        bwF.write(fs.getStato() + " " + fs.getTitolo() + " " + fs.getLingua() + " " + fs.getGenere() + " " + fs.getRegista() + " " + fs.getAnno() + " " + fs.getDurata() + " " + logica.sedi.indexOf(fs.getSedeAppartenenza()) + "\n");
        bwF.close();
        logica.film.add(fs);
    }

    // Crea Musica
    protected void newMusica() throws IOException {
        Musica ms = new Musica();

        System.out.print("Inserisci il titolo: ");
        ms.setTitolo(sc.next());
        System.out.print("Inserisci l'artista: ");
        ms.setArtista(sc.next());
        System.out.print("Inserisci il genere: ");
        ms.setGenere(sc.next());
        System.out.print("Inserisci la durata: ");
        ms.setDurata(sc.next());
        System.out.println("Scegli la sede: ");
        ms.setSedeAppartenenza(choiseSede(logica.sedi));

        FileWriter fwM = new FileWriter(MUSICAFILE, true);
        BufferedWriter bwM = new BufferedWriter(fwM);

        bwM.write(ms.getStato() + " " + ms.getTitolo() + " " + ms.getArtista() + " " + ms.getGenere() + " " + ms.getDurata() + " " + logica.sedi.indexOf(ms.getSedeAppartenenza()) + "\n");
        bwM.close();
        logica.musica.add(ms);

    }

    // Crea utente
    public Utente newUtente(Logica logica) throws IOException {
        Utente utente = new Utente();
        System.out.print("Inserisci il nome: ");
        utente.setNome(sc.next());
        System.out.print("Inserisci il cognome: ");
        utente.setCognome(sc.next());
        System.out.print("Inserisci la citta: ");
        utente.setCitta(sc.next());
        System.out.print("Inserisci il numero di telefono: ");
        utente.setNumerotelefono(inputLong());
        FileWriter userFW = new FileWriter(UTENTIFILE, true);
        BufferedWriter userBW = new BufferedWriter(userFW);
        logica.utenti.add(utente);
        newAccount(logica,utente);
        userBW.write(logica.utenti.indexOf(utente) + " " + utente.getNome() + " " + utente.getCognome() + " " + utente.getCitta() + " " + utente.getNumerotelefono() + "\n");
        userBW.close();

        return utente;
    }

    // Crea account
    protected void newAccount(Logica logica, Utente utente) throws IOException {
        System.out.println("Registrati");
        System.out.print("Inserisci il nome utente: ");
        String nomeUtente = sc.next();
        System.out.print("Inserisci la password: ");
        String password = sc.next();

        FileWriter aFW = new FileWriter("inputdata/account.txt", true);
        BufferedWriter aBW = new BufferedWriter(aFW);

        aBW.write(logica.utenti.indexOf(utente) + " " + nomeUtente + " " + password + "\n");
        aBW.close();
    }

    // Scegli sede
    private static Sede choiseSede(List<Sede> s) {
        int i = 1;
        for (Sede x : s) {
            System.out.println("[" + i + "] " + x.getNome());
            i++;
        }
        System.out.print("Scegli una sede: ");
        return s.get(inputInt() - 1);
    }

    // Scegli autore
    private static Autore choiseAutore(List<Autore> a) {
        int i = 1;
        for (Autore x : a) {
            System.out.println("[" + i + "] " + x.getNome());
            i++;
        }
        System.out.print("Scegli un'autore: ");
        return a.get(inputInt() - 1);
    }

    // Scegli editore
    private static Editore choiseEditore(List<Editore> e) {
        int i = 1;
        for (Editore x : e) {
            System.out.println("[" + i + "] " + x.getNome());
            i++;
        }
        System.out.print("Scegli un'editore: ");
        return e.get(inputInt() - 1);
    }
}