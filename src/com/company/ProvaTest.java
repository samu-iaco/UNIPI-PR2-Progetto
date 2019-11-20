package com.company;
import org.junit.*;
public class ProvaTest {

    @Test(expected = java.lang.AssertionError.class)
    public void test(){
        Video v = new Video(120,8,"Topolino e pippo");
        Video v1 = new Video(120,8,"Pippo tutto il giorno");
        Video v2 = new Video(120,8,"Topolino e pippo");
        Assert.assertEquals(v,v1);
    }

    @Test
    public void test_categoria(){

    }
}
