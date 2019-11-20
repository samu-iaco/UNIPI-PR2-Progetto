package com.company;

import java.util.*;

public class DataFriend<E> {
//classe che si occupa di gestire tutti i dati che possono vedere gli amici

    private String nome_amico;
    private Vector<Data> amici_ammessi;

    public DataFriend(String nome_amico) {
        this.nome_amico = nome_amico;
        amici_ammessi = new Vector<Data>();
    }

    public Vector<Data> getAmici_ammessi() {
        return amici_ammessi;
    }
}
