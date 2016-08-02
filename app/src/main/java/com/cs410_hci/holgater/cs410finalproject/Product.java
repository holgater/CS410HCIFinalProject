package com.cs410_hci.holgater.cs410finalproject;

import java.io.Serializable;

/**
 * Created by Richard on 7/17/2016.
 */
public class Product extends Item implements Serializable {

    private int inStockNum = 0;

    //constructor
    public Product(String nameIn, int imageIn, int inStockNumIn, String descriptionIn) {
        //initiate name, image as an Item
        super(nameIn, imageIn, inStockNumIn, descriptionIn);
    }

}
