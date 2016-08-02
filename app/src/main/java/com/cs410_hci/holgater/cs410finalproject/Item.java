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
    private String description;

    //constructor
    public Item(String nameIn, int imageIn, int inStockNum, String descriptionIn) {
        this.name = nameIn;
        this.image = imageIn;
        this.inStockNum = inStockNum;
        this.description = descriptionIn;
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

    public String getDescription() {
        return description;
    }

}
