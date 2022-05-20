/*
 * Copyright (c) 2021-2021.
 * Authors: Ghirimoldi Luca && Carlomagno Stefano && Esposito Andrea
 */

package Library.logica;

import Library.attori.Utente;

public class Account extends InterfacciaUtente {
    private final Utente user;
    private final String nome;
    private final String password;

    public Account(Utente usr, String n, String pw) {
        this.user = usr;
        this.nome = n;
        this.password = pw;
    }
    protected Utente getUser() {
        return user;
    }

    protected String getNome() {
        return nome;
    }

    protected String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Utente: "+ user.getNome()+" "+user.getCognome()+", Nome utente: "+nome+", Password: "+password;
    }

}