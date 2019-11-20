package com.company;
import org.junit.*;

import java.util.Iterator;

public class ProvaTest {


    private MyDataBoard<Video> gallery = new MyDataBoard<Video>("a");
    private Video titles[] = {
            new Video(120,8,"Topolino e pippo"),
            new Video(120,8,"Pippo tutto il giorno")
    };

    @Before
    public void start(){
        try{
            gallery.createCategory("animali","a");
            gallery.addFriend("animali","a","fede");
            for(int i = 0; i < titles.length; i++)
                gallery.put("a",titles[i],"animali");
        } catch (WrongLoginException | AlreadyExistsException | NotExistsException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = com.company.WrongLoginException.class)
    public void wrong_addCategory() throws AlreadyExistsException, WrongLoginException {
            gallery.createCategory("ciao","b");
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

        Assert.assertEquals(2,count);
        it.remove();
    }
}
