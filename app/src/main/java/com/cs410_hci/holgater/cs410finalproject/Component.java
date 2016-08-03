package com.cs410_hci.holgater.cs410finalproject;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Richard on 7/18/2016.
 */
public class Component extends Item implements Serializable{

    //constructor
    public Component(String nameIn, Bitmap imageIn, int inStockNumIn, String descriptionIn) {
        //initiate name, image as an Item
        super(nameIn, imageIn, inStockNumIn, descriptionIn);
    }
}
