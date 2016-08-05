package com.cs410_hci.holgater.cs410finalproject;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Richard on 7/18/2016.
 */
public class Component extends Item implements Serializable, Cloneable{

    protected String name;
    protected Bitmap image;
    protected int inStockNum;
    protected String description;

    String unitType;
    private int count = 0;

    //constructor
    public Component(String nameIn, Bitmap imageIn, int inStockNumIn, String descriptionIn, String unitTypeIn) {
        //initiate name, image as an Item
        super(nameIn, imageIn, inStockNumIn, descriptionIn);
        this.name = nameIn;
        this.image = imageIn;
        this.inStockNum = inStockNumIn;
        this.description = descriptionIn;
        this.unitType = unitTypeIn;
    }


    public void setCompUnit(String compUnit) {
        this.unitType = compUnit;
    }

    public String getUnit() {
        return unitType;
    }

    public int getCounts(){
        return this.count;
    }
    public void setCount(int count){
        this.count = count;
    }
    @Override
    public Component clone(){
        return new Component(name,image,inStockNum,description,unitType);
    }
}
