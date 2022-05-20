/*
 * Copyright (c) 2021-2021.
 * Authors: Ghirimoldi Luca && Carlomagno Stefano && Esposito Andrea
 */

package Library.logica;

import Library.attori.Utente;

import java.util.Objects;

class Login {
    private String inputPwA;

    protected Login(String pwA) {
        this.inputPwA = pwA;
    }
    public Utente LoginUtente (Logica l, String n, String pw) {
        for (Account acc : l.accounts) {
            if (Objects.equals(acc.getNome(), n) && Objects.equals(acc.getPassword(), pw)) {
                return acc.getUser();
            }
        }return null;
    }

    public Login() {}
    protected boolean check() {
        return this.inputPwA.equals("admin");
    }
}