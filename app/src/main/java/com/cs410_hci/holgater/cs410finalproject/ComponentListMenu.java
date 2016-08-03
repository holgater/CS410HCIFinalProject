package com.cs410_hci.holgater.cs410finalproject;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

public class ComponentListMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //AppCompatActivity setup
        super.onCreate(savedInstanceState);
        //setup layout
        setContentView(R.layout.activity_component_list_menu);

        //load Products
        DataBase.loadProducts();
        //load materials
        DataBase.loadComponents();
        //test - hard code products


        for (int i = 0; i < 15; ++i) {
            DataBase.components.add(new Component(Test.cNameId[i], BitmapFactory.decodeResource(getResources(), Test.cImageId[i]), Test.cItemInStockNumId[i], Test.cDescription[i]));
        }

        //setup toolbar
        ExpandableGridView eGridView = (ExpandableGridView) findViewById(R.id.eGridView);
        eGridView.setAdapter(new GridViewAdapter(this, DataBase.components));
        eGridView.setExpanded(true);

        //setup grid items with onClick listener
        eGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //create intent to start ComponentEditMenu activity
                Intent intent = new Intent(ComponentListMenu.this, ComponentEditMenu.class);
                intent.putExtra("component", DataBase.components.get(position));
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
        DataBase.saveProducts();
        DataBase.saveComponents();
    }
}
