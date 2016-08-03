package com.cs410_hci.holgater.cs410finalproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mtsvietukhin on 8/2/2016.
 */
public class DataBase {
    //input stream for loading products and materials
    static ObjectInputStream input;
    //output stream for saving products and materials
    static ObjectOutputStream out = null;

    //array of products and materials user has created
    public static List<Product> products = new LinkedList<>();
    public static List<Component> components = new LinkedList<>();

    //load products from serialized file
    public static void loadProducts() {
        //product serialized file
        String filename = "products.srl";
        File prodDBFile = new File(filename);
        if(!prodDBFile.exists() && prodDBFile.isDirectory()) {
            try {
                prodDBFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //try to get objectInputStream, then load products
        try {
            input = new ObjectInputStream(new FileInputStream(filename));
            products = (List<Product>) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //load materials from serialize file
    public static void loadComponents() {
        //material serialized file
        String filename = "components.srl";
        File compDBFile = new File(filename);
        if(!compDBFile.exists() && compDBFile.isDirectory()) {
            try {
                compDBFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            input = new ObjectInputStream(new FileInputStream(filename));
            components = (List<Component>) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //save products to serialized file
    public static void saveProducts() {
        //product serialized file
        String filename = "products.srl";
        //try to get the objectOutputSteam, then save products
        File compDBFile = new File(filename);
        if(!compDBFile.exists() && compDBFile.isDirectory()) {
            try {
                compDBFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(products);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //save materials to serialized file
    public static void saveComponents() {
        //material serialized file
        String filename = "materials.srl";

        File compDBFile = new File(filename);
        if(!compDBFile.exists() && compDBFile.isDirectory()) {
            try {
                compDBFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //try to get the objectOutputStream, then save materials
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(components);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
