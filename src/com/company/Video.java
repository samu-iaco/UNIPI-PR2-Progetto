package com.company;

import com.company.Data;

public class Video extends Data {
    private int size;
    private int lenght;
    private String Title;

    public Video(int size, int lenght, String title) throws NullPointerException {
        super();
        if(title == null) throw new NullPointerException();
        this.size = size;
        this.lenght = lenght;
        Title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public String toString(){
        return super.toString() +
        " - com.company.Video{" + Title +
                " durata=" + lenght +
                        " dimensione= "+size+'}';
    }
}
