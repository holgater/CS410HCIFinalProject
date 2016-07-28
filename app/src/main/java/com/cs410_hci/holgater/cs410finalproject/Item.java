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
    private int inStockNum;

    //constructor
    public Item(String nameIn, int imageIn, int inStockNum) {
        this.name = nameIn;
        this.image = imageIn;
        this.inStockNum = inStockNum;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getInStockNum() {
        return inStockNum;
    }

}
