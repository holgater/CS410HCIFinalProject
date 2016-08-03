package com.cs410_hci.holgater.cs410finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ComponentListMenu extends AppCompatActivity {
    //gridView
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //AppCompatActivity setup
        super.onCreate(savedInstanceState);
        //setup layout
        setContentView(R.layout.activity_component_list_menu);

        dataBase = new DataBase();

        //load Products
        dataBase.loadProducts();
        //load materials
        dataBase.loadComponents();
        //test - hard code products


        for (int i = 0; i < 15; ++i) {
            dataBase.components.add(new Component(Test.cNameId[i], Test.cImageId[i], Test.cItemInStockNumId[i], Test.cDescription[i]));
        }

        //setup toolbar
        ExpandableGridView eGridView = (ExpandableGridView) findViewById(R.id.eGridView);
        eGridView.setAdapter(new GridViewAdapter(this, dataBase.components));
        eGridView.setExpanded(true);

        //setup grid items with onClick listener
        eGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //create intent to start ComponentEditMenu activity
                Intent intent = new Intent(ComponentListMenu.this, ComponentEditMenu.class);
                intent.putExtra("component", dataBase.components.get(position));
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ComponentListMenu.this, AddComponent.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //save products and materials for next time
        dataBase.saveProducts();
        dataBase.saveComponents();
    }
}
