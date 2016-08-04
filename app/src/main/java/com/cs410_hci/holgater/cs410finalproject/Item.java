package com.cs410_hci.holgater.cs410finalproject;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Richard on 7/18/2016.
 */
public class Item implements Serializable{
    //both Products and Materials have:
    // names, images
    private String name;
    private Bitmap image;
    private int inStockNum;
    private String description;

    //constructor
    public Item(String nameIn, Bitmap imageIn, int inStockNum, String descriptionIn) {
        this.name = nameIn;
        this.image = imageIn;
        this.inStockNum = inStockNum;
        this.description = descriptionIn;
    }

    public String getName() {
        return name;
    }

    public Bitmap getImage() {
        return image;
    }

    public int getInStockNum() {
        return inStockNum;
    }

    public String getDescription() {
        return description;
    }

    public void setInStockNum(int inStockNum) {
        this.inStockNum = inStockNum;
    }
}
