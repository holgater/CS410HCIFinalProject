package com.cs410_hci.holgater.cs410finalproject;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Richard on 7/17/2016.
 */
public class Product extends Item implements Serializable {

    private int inStockNum = 0;
    private List<Item> recipe;

    //constructor
    public Product(String nameIn, Bitmap imageIn, int inStockNumIn, String descriptionIn) {
        //initiate name, image as an Item
        super(nameIn, imageIn, inStockNumIn, descriptionIn);
        //recipe = recipeIn;
    }

   /* public List<Item> getRecipe() {
        return recipe;
    }

    public void addToRecipe(Item itemIn) {
        recipe.add(itemIn);
    }*/

}