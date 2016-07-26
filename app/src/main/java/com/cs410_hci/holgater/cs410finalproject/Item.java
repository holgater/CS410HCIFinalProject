package com.cs410_hci.holgater.cs410finalproject;

import java.io.Serializable;

/**
 * Created by Richard on 7/18/2016.
 */
public class Item implements Serializable{
    //both Products and Materials have:
    // names, images
    private String name;
    private int image;

    //constructor
    public Item(String nameIn, int imageIn) {
        this.name = nameIn;
        this.image = imageIn;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

}
