package com.company;

import java.util.*;

public interface DataBoard<E extends Data> {
    // Crea una categoria di dati
    // se vengono rispettati i controlli di identità
    public void createCategory(String category, String passw) throws NullPointerException, AlreadyExistsException,WrongLoginException;

    // Rimuove una categoria di dati
    // se vengono rispettati i controlli di identità
    public void removeCategory(String category, String passw) throws NullPointerException,WrongLoginException, NotExistsException;

    // Aggiunge un amico ad una categoria di dati
    // se vengono rispettati i controlli di identità
    public void addFriend(String category, String passw, String friend) throws NullPointerException, WrongLoginException, AlreadyExistsException;

    // rimuove un amico da una categoria di dati
    // se vengono rispettati i controlli di identità
    public void removeFriend(String category, String passw, String friend) throws NullPointerException,WrongLoginException, NotExistsException;;

    //Inserisce un dato in bacheca
    // se vengono rispettati i controlli di identità
    public boolean put(String passw, E dato, String categoria)throws NullPointerException,WrongLoginException,NotExistsException,AlreadyExistsException;

    //Restituisce una copia del dato in bacheca
    // se vengono rispettati i controlli di identità
    public E get(String passw, E dato) throws WrongLoginException,NotExistsException ;

    // Rimuove il dato dalla bacheca
    // se vengono rispettati i controlli di identità
    public E remove(String passw, E dato) throws WrongLoginException, NotExistsException;

    // Crea la lista dei dati in bacheca di una determinata categoria
    // se vengono rispettati i controlli di identità
    public List<E> getDataCategory(String passw, String category) throws NotExistsException, WrongLoginException;

    // Aggiunge un like a un dato
    // se vengono rispettati i controlli di identità
    void insertLike(String friend, E dato) throws AlreadyLikedException, PermessionDeniedException;

    // restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca ordinati rispetto al numero di like
    // se vengono rispettati i controlli di identità
    public Iterator<E> getIterator(String passw);

    // restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca condivisi
    public Iterator<E> getFriendIterator(String friend);
}
