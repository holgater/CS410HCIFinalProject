package com.cs410_hci.holgater.cs410finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ComponentListMenu extends AppCompatActivity {
    //gridView
    GridView gridView;
    //input stream for loading products and materials
    ObjectInputStream input;
    //output stream for saving products and materials
    ObjectOutputStream out = null;
    //array of products and materials user has created
    Product[] products;
    Component[] components;

    //test - hard code products
    ///*
    String[] pNameId = {
            "name1",
            "name2",
            "name3",
            "name4",
            "name5",
            "name1",
            "name2",
            "name3",
            "name4",
            "name5",
            "name1",
            "name2",
            "name3",
            "name4",
            "name5"

    };

    int[] pImageId = {
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5
    };

    int[] pItemInStockNumId = {
            12,
            2,
            6,
            15,
            4,
            12,
            2,
            6,
            15,
            4,
            12,
            2,
            6,
            15,
            4
    };
    //*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //AppCompatActivity setup
        super.onCreate(savedInstanceState);
        //setup layout
        setContentView(R.layout.activity_component_list_menu);
        //load Products
        loadProducts();
        //load materials
        loadComponents();
        //test - hard code products
        ///*
        components = new Component[15];

        for (int i = 0; i < 15; ++i) {
            components[i] = new Component(pNameId[i], pImageId[i], pItemInStockNumId[i]);
        }
        //*/
        //setup toolbar
        ExpandableGridView eGridView = (ExpandableGridView) findViewById(R.id.eGridView);
        eGridView.setAdapter(new GridViewAdapter(this, components));
        eGridView.setExpanded(true);

        //setup grid items with onClick listener
        eGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //TODO
                //create intent to start ProductEditMenu activity
                Intent intent = new Intent(ComponentListMenu.this, ComponentEditMenu.class);
                intent.putExtra("component", components[position]);
                startActivity(intent);
            }
        });

        //setup "products" button in toolbar
        Button productsButton = (Button) findViewById(R.id.productsButton);
        productsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ComponentListMenu.this, ProductListMenu.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //save products and materials for next time
        saveProducts();
        saveComponents();
    }

    //load products from serialized file
    protected void loadProducts() {
        //product serialized file
        String filename = "products.srl";
        //try to get objectInputStream, then load products
        try {
            input = new ObjectInputStream(new FileInputStream(filename));
            products = (Product[]) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //load materials from serialize file
    protected void loadComponents() {
        //material serialized file
        String filename = "materials.srl";
        //try to get objectInputStream, then load materials
        try {
            input = new ObjectInputStream(new FileInputStream(filename));
            components = (Component[]) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //save products to serialized file
    protected void saveProducts() {
        //product serialized file
        String filename = "products.srl";
        //try to get the objectOutputSteam, then save products
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(products);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //save materials to serialized file
    protected void saveComponents() {
        //material serialized file
        String filename = "materials.srl";
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
