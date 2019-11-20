package com.company;

import java.util.Vector;
import java.util.Iterator;
import java.util.Scanner;

import com.company.AlreadyExistsException;
import com.company.WrongLoginException;

public class TestCase {
    private static Scanner reader ;
    private static MyDataBoard<Video> lista ;

    public static void main(String[] args) {
        lista = new MyDataBoard<Video>("biketrial");
        reader= new Scanner(System.in);

        boolean run = true;

        while(run){
            stampaMenu();
            int op = chiediOperazione();

            switch (op){
                case 0:
                    run = false;
                    break;

                case 1:
                    creaCategoria();
                    break;

                case 2:
                    rimuoviCategoria();
                    break;
                case 3:
                    aggiungiAmico();
                    break;
                case 4:
                    rimuoviAmico();
                    break;
                case 5:
                    aggiungiDato();
                    break;
            }
        }

    }

    private static void stampaMenu() {
        System.out.println("0 - Esci dal programma");
        System.out.println("1 - Aggiungi Categoria");
        System.out.println("2 - Rimuovi categoria");
        System.out.println("3 - Aggiungi Amico");
        System.out.println("4 - Rimuovi Amico");
        System.out.println("5 - Aggiungi dato");
    }

    private static void aggiungiDato(){
        String categoria = chiediStringa("categoria");
        String pass_Cat = chiediStringa("password");
        String chiediDato = chiediStringa("Dato");
        Video dato = new Video(80,20,"porno");

        try{
            lista.put(pass_Cat,dato,categoria);

            System.out.println("video aggiunto!");
        }catch(WrongLoginException e){
            //System.out.println("password errata bro!");
            e.printStackTrace();
        }catch(NotExistsException e){
            System.out.println("categoria non presente bro!");
        }catch(AlreadyExistsException e){
            System.out.println("dato gia presente bro!");
        }
    }

    private static void rimuoviAmico(){
        String categoria = chiediStringa("categoria");
        String pass_Cat = chiediStringa("password");
        String amico = chiediStringa("nome amico");

        try{
            lista.removeFriend(categoria,pass_Cat,amico);

            System.out.println("amico rimosso");
        }catch(WrongLoginException e){
            System.out.println("password errata bro!");
        }catch(NotExistsException e){
            System.out.println("amico non presente");
        }
    }

    private static void aggiungiAmico(){
        String categoria = chiediStringa("categoria");
        String pass_Cat = chiediStringa("password");
        String amico = chiediStringa("nome amico");

        try{
            lista.addFriend(categoria,pass_Cat,amico);

            System.out.println("Amico aggiunto");
        }catch (WrongLoginException e){
            System.out.println("password errata!");
        }catch (AlreadyExistsException e){
            System.out.println("amico gia aggiunto");
        }
    }

    private static void rimuoviCategoria(){
        String categoria = chiediStringa("categoria");
        String pass_Cat = chiediStringa("password");
        try{
            lista.removeCategory(categoria,pass_Cat);

            System.out.println("Categoria rimossa");
        }catch (WrongLoginException e){
            System.out.println("password errata!");
        }catch (NotExistsException e){
            System.out.println("la categoria non esiste!");
        }
    }

    private static void creaCategoria(){
        String categoria = chiediStringa("categoria");
        String pass_Cat = chiediStringa("password");
        try{
            lista.createCategory(categoria,pass_Cat);

            System.out.println("Categoria creata correttamente");
        }catch (AlreadyExistsException e){
            System.out.println("categoria già esistente!");
        }
        catch (WrongLoginException e){
            System.out.println("password errata!");
        }
        catch (NullPointerException e){
            System.out.println("inserisci una categoria da inserire!");
        }
    }

    private static String chiediStringa(String message){
        System.out.print(message + ": ");
        return reader.next();
    }

    private static int chiediOperazione() {
        System.out.print("Inserisci operazione: ");
        return reader.nextInt();
    }
}
