package com.cs410_hci.holgater.cs410finalproject;

import java.io.Serializable;

/**
 * Created by Richard on 7/18/2016.
 */
public class Component extends Item implements Serializable{

    //constructor
    public Component(String nameIn, int imageIn, int inStockNumIn) {
        //initiate name, image as an Item
        super(nameIn, imageIn, inStockNumIn);
    }
}
