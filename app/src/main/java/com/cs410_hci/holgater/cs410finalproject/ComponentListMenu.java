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
import java.util.List;

public class ComponentListMenu extends AppCompatActivity {
    //gridView
    GridView gridView;
    //input stream for loading products and materials
    ObjectInputStream input;
    //output stream for saving products and materials
    ObjectOutputStream out = null;
    //array of products and materials user has created
    List<Product> products;
    List<Component> components;

    //test - hard code products
    ///*
    String[] cNameId = {
            "Black Lace",
            "White Lace",
            "Purple Lace",
            "Red Lace",
            "Blue Fabric",
            "Red Fabric",
            "Plaid Fabric",
            "White Elastic",
            "Green Elastic",
            "Red Button",
            "Blue Button",
            "Yellow Button",
            "Green Button",
            "Patterned Fabric 1",
            "Patterned Fabric 2"

    };

    int[] cImageId = {
            R.drawable.component01,
            R.drawable.component02,
            R.drawable.component03,
            R.drawable.component04,
            R.drawable.component05,
            R.drawable.component06,
            R.drawable.component07,
            R.drawable.component08,
            R.drawable.component09,
            R.drawable.component10,
            R.drawable.component11,
            R.drawable.component12,
            R.drawable.component13,
            R.drawable.component14,
            R.drawable.component15
    };

    int[] cItemInStockNumId = {
            12,
            2,
            6,
            15,
            4,
            1,
            0,
            13,
            19,
            2,
            1,
            0,
            25,
            21,
            4
    };

    String[] cDescription = {
            "Some black lace - coolio",
            "Some white lace - coolio",
            "Some purple lace - coolio",
            "Some red lace - coolio",
            "Some blue fabric - coolio",
            "Some red fabric - coolio",
            "Some plaid fabric - coolio",
            "Some white elastic - coolio",
            "Some green elastic - coolio",
            "A red button - coolio",
            "A blue button - coolio",
            "A yellow button - coolio",
            "A green button - coolio",
            "Some patterned fabric - coolio",
            "Some patterned fabric - coolio"

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
        /*

        for (int i = 0; i < 15; ++i) {
            components.add(new Component(cNameId[i], cImageId[i], cItemInStockNumId[i], cDescription[i]));
        }
        */
        //setup toolbar
        ExpandableGridView eGridView = (ExpandableGridView) findViewById(R.id.eGridView);
        eGridView.setAdapter(new GridViewAdapter(this, components));
        eGridView.setExpanded(true);

        //setup grid items with onClick listener
        eGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //create intent to start ComponentEditMenu activity
                Intent intent = new Intent(ComponentListMenu.this, ComponentEditMenu.class);
                intent.putExtra("component", components.get(position));
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
            products = (List<Product>) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //load materials from serialize file
    protected void loadComponents() {
        //material serialized file
        String filename = "components.srl";
        //try to get objectInputStream, then load materials
        try {
            input = new ObjectInputStream(new FileInputStream(filename));
            components = (List<Component>) input.readObject();
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
