package com.company;
import org.junit.*;

import java.util.Iterator;

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
            gallery.addFriend("animali","a","fede");
            gallery.addFriend("animali","a","loris");
            //gallery.put("a",vid,"animali");
            for(int i = 0; i < titles.length; i++)
                gallery.put("a",titles[i],"animali");
        } catch (WrongLoginException | AlreadyExistsException | NotExistsException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = com.company.WrongLoginException.class)
    public void wrong_addCategory() throws AlreadyExistsException, WrongLoginException {
            gallery.createCategory("gatti_che_piangono","b");
    }

    @Test(expected = java.lang.UnsupportedOperationException.class)
    public void test_iterator(){
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

    @Test
    public void addLike() throws AlreadyLikedException, PermessionDeniedException {
        gallery.insertLike("loris",titles[0]);
        Assert.assertEquals(1,titles[0].like);
        Assert.assertTrue(titles[0].amici.contains("loris"));
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
                Assert.assertTrue(t.like >= it.next().like);
        }

        it.remove();
    }

    @Test
    public void test_remove() throws NotExistsException, WrongLoginException {
        gallery.remove("a",titles[0]);
        Iterator<Video> it = gallery.getFriendIterator("fede");
        int count = 0;

        while(it.hasNext()){
            //Assert.assertEquals(
              //      titles[count++].getTitle(),
              //      it.next().getTitle()
            //);
            count++;
            it.next().getTitle();
        }

        Assert.assertEquals(2,count);
        //it.remove();
    }
}
