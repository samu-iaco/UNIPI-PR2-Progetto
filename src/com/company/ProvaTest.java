package com.company;
import org.junit.*;

import java.util.Iterator;
import java.util.List;

public class ProvaTest {


    private MyDataBoard<Video> gallery = new MyDataBoard<Video>("a");
    private Video vid = new Video(12,32,"cane");
    private Video titles[] = {
            new Video(120,8,"Topolino e pippo"),
            new Video(120,8,"Pippo tutto il giorno"),
            new Video(130,15,"Paperino")
    };

    @Before
    public void start(){
        try{
            gallery.createCategory("animali","a");
            gallery.createCategory("cani","a");
            gallery.addFriend("animali","a","fede");
            gallery.addFriend("animali","a","loris");
            gallery.addFriend("cani","a","giorgio");
            //gallery.put("a",vid,"animali");
            for(int i = 0; i < titles.length; i++)
                gallery.put("a",titles[i],"animali");
        } catch (WrongLoginException | AlreadyExistsException | NotExistsException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = com.company.WrongLoginException.class)
    public void wrongPassword_addCategory() throws AlreadyExistsException, WrongLoginException {
            gallery.createCategory("città","b");
    }

    @Test(expected = com.company.AlreadyExistsException.class)
    public void wrong_addCategory() throws AlreadyExistsException, WrongLoginException {
        gallery.createCategory("città","a");

        gallery.createCategory("città","a");
    }

    @Test
    public void test_removeCategory() throws NotExistsException, WrongLoginException {
        gallery.removeCategory("cani","a");

    }

    @Test(expected = com.company.NotExistsException.class)
    public void wrong_removeCategory() throws NotExistsException, WrongLoginException {
        gallery.removeCategory("cani","a");
        //rimuovo ancora
        gallery.removeCategory("cani","a");
    }

    @Test
    public void test_Addfriend() throws AlreadyExistsException, WrongLoginException {
        gallery.addFriend("animali","a","gino");
    }

    @Test(expected = com.company.AlreadyExistsException.class)
    public void wrong_Addfriend() throws AlreadyExistsException, WrongLoginException {
        gallery.addFriend("animali","a","gino");
        //provo a riaggiungere lo stesso amico
        gallery.addFriend("animali","a","gino");
    }

    @Test
    public void test_RemoveFriend() throws NotExistsException, WrongLoginException {
        gallery.removeFriend("animali","a","fede");
    }

    @Test(expected = com.company.NotExistsException.class)
    public void wrong_RemoveFriend() throws NotExistsException, WrongLoginException {
        gallery.removeFriend("animali","a","fede");
        //provo a rimuovere l'amico che ho gia rimosso
        gallery.removeFriend("animali","a","fede");
    }

    @Test(expected = com.company.AlreadyExistsException.class)
    public void test_Put() throws NotExistsException, WrongLoginException, AlreadyExistsException {
        Video prova = new Video(12,23,"il gatto e la volpe");
        gallery.put("a",prova,"animali");
        //provo a riaggiungere lo stesso dato
        gallery.put("a",prova,"animali");
    }

    @Test
    public void test_getDataCategory() throws NotExistsException, WrongLoginException {
        List<Video> list = gallery.getDataCategory("a","animali");

        Assert.assertEquals(3,list.size());
    }

    @Test(expected = java.lang.AssertionError.class)
    public void wrong_getDataCategory() throws NotExistsException, WrongLoginException {
        List<Video> list = gallery.getDataCategory("a","animali");

        Assert.assertEquals(2,list.size());
    }

    @Test(expected = java.lang.UnsupportedOperationException.class)
    public void test_iterator() throws NotExistsException {
        Iterator<Video> it = gallery.getFriendIterator("fede");
        int count = 0;

        while(it.hasNext()){
            Assert.assertEquals(
                    titles[count++].getTitle(),
                    it.next().getTitle()
            );
        }

        Assert.assertEquals(3,count);
        it.remove();
    }

    @Test(expected = com.company.NotExistsException.class)
    public void wrong_iterator() throws NotExistsException {
        Iterator<Video> it = gallery.getFriendIterator("fee");
        int count = 0;

        while(it.hasNext()){
            Assert.assertEquals(
                    titles[count++].getTitle(),
                    it.next().getTitle()
            );
        }

        Assert.assertEquals(3,count);
        it.remove();
    }

    @Test
    public void addLike() throws AlreadyLikedException, PermessionDeniedException {
        gallery.insertLike("loris",titles[0]);
        Assert.assertEquals(1,titles[0].like);
        Assert.assertTrue(titles[0].amici.contains("loris"));
    }

    @Test(expected = com.company.PermessionDeniedException.class)
    public void wrong_addLike() throws AlreadyLikedException, PermessionDeniedException {
        gallery.insertLike("giorgio",titles[0]);

    }

    @Test(expected = java.lang.UnsupportedOperationException.class)
    public void test_likeIterator() throws AlreadyLikedException, PermessionDeniedException, WrongLoginException {
        gallery.insertLike("loris",titles[0]);
        gallery.insertLike("fede",titles[0]);

        gallery.insertLike("loris",titles[1]);

        Iterator<Video> it = gallery.getIterator("a");
        while (it.hasNext()){
            Video t = it.next();
            if(it.hasNext())
                Assert.assertTrue(t.like >= it.next().like);    //controllo i dati siano ordinati in ordine di like
        }

        it.remove();
    }

    @Test
    public void test_get() throws NotExistsException, WrongLoginException {
        Video dato = gallery.get("a",titles[1]);
        Assert.assertTrue(dato.equals(titles[1]));
    }

    @Test(expected = java.lang.AssertionError.class)
    public void wrong_get() throws NotExistsException, WrongLoginException {
        Video dato = gallery.get("a",titles[1]);
        Assert.assertTrue(dato.equals(titles[0]));
    }

    @Test
    public void test_remove() throws NotExistsException, WrongLoginException {
        gallery.remove("a",titles[0]);
        Iterator<Video> it = gallery.getFriendIterator("fede");
        int count = 0;

        while(it.hasNext()){
            count++;
            it.next().getTitle();
        }

        Assert.assertEquals(2,count);
    }
}
