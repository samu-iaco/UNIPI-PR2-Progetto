package com.company;

public class User {
    //classe che controlla i dati di accesso

    private String passw;

    public boolean checkPassword(String passw,String passw2){
        return this.passw.equals(passw2);
    }
}
