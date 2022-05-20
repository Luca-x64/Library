/*
 * Copyright (c) 2021-2021.
 * Authors: Ghirimoldi Luca && Carlomagno Stefano && Esposito Andrea
 */

package Library.attori;

import Library.logica.ImpostaSistema;

public class Provincia extends ImpostaSistema {

    private String provincia;

    public Provincia(String prov) {
        this.provincia = prov;
    }

    public Provincia() {}

    public String getProvincia() {
        return provincia;
    }

    @Override public String toString() {
        return "La provincia è: "+provincia;
    }
}