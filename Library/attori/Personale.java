/*
 * Copyright (c) 2021-2021.
 * Authors: Ghirimoldi Luca && Carlomagno Stefano && Esposito Andrea
 */

package Library.attori;

public class Personale extends Sede {
    private String nome, cognome,ruolo, turno, dataNascita, cittaNascita;
    private int salario;

    public Personale(String nme, String cog, String ruo, String tur, String dn, String cn, int sal) {
        this.nome = nme;
        this.cognome = cog;
        this.ruolo = ruo;
        this.turno = tur;
        this.dataNascita = dn;
        this.cittaNascita = cn;
        this.salario = sal;
    }

    public Personale() {}

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getCittaNascita() {
        return cittaNascita;
    }

    public void setCittaNascita(String cittaNascita) {
        this.cittaNascita = cittaNascita;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Nome: "+nome+", Cognome: "+cognome+", Ruolo: "+ruolo+", Turno: "+turno+", Data di nascita: "+dataNascita +", Citta' di nascita "+cittaNascita+", Salario: "+salario;
    }
}