package com.cs410_hci.holgater.cs410finalproject;

import java.io.Serializable;

/**
 * Created by Richard on 7/18/2016.
 */
public class Material extends Item implements Serializable{

    //constructor
    public Material(String nameIn, int imageIn) {
        //initiate name, image as an Item
        super(nameIn, imageIn);
    }
}
