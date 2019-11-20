package com.company;

import java.util.*;

public class Data {
    //classe riservata a gestire il singolo dato
    //bisogna che gestisca ogni dettaglio del singolo dato
    protected int like;
    protected Vector<String> amici;    //vettore che tiene traccia degli utenti che hanno gia messo like

    public Data() {
        this.amici = new Vector<String>();
        this.like = 0;
    }

    public Vector<String> getAmici() {
        return amici;
    }


    public int addLike(){
        return like++;
    }

    public int getLike() {
        return like;
    }

    @Override
    public String toString() {
        return "Data{" +
                "like=" + like +
                ", amici=" + amici + '}';
    }

    public void display(){
        System.out.println(toString());
    }
}
