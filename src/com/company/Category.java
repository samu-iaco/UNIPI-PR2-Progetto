package com.company;

import java.util.Vector;

public class Category<E> {
    //classe riservata alla gestione della categoria Category

    private String nomeCat; //nome categoria
    private Vector<E> datiCat;  //dati categoria
    private Vector<String> amiciCat; //amici con accesso alla categoria

    public Category(String nomeCat) { //non c'è bisogno di passare niente perche c'è gia la stringa nel metodo createCategory
        this.nomeCat = nomeCat;
        datiCat = new Vector<E>();   //inizializzo il vettore dei dati a vuoto
        amiciCat = new Vector<String>();
    }

    public String getNomeCat() {
        return nomeCat;
    }

    public Vector<String> getAmiciCat() {
        return amiciCat;
    }


    public void setDatiCat(Vector<E> datiCat) {
        this.datiCat = datiCat;
    }

    public Vector<E> getDatiCat() {
        return datiCat;
    }

}
