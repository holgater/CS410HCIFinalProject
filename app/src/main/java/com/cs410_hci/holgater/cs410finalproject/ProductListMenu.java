package com.cs410_hci.holgater.cs410finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

public class ProductListMenu extends AppCompatActivity {
    //gridView
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //AppCompatActivity setup
        super.onCreate(savedInstanceState);
        //setup layout
        setContentView(R.layout.activity_product_list_menu);
        dataBase = new DataBase();
        //load Products
        dataBase.loadProducts();
        //load materials
        dataBase.loadComponents();

        for (int i = 0; i < 14; ++i) {
            dataBase.products.add(new Product(Test.pNameId[i], Test.pImageId[i], Test.pItemInStockNumId[i], Test.pDescription[i], Test.pRecipe[i]));
        }

        //setup toolbar
        ExpandableGridView eGridView = (ExpandableGridView) findViewById(R.id.eGridView);
        eGridView.setAdapter(new GridViewAdapter(this, dataBase.products));
        eGridView.setExpanded(true);

        //setup grid items with onClick listener
        eGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //create intent to start ProductEditMenu activity
                Intent intent = new Intent(ProductListMenu.this, ProductEditMenu.class);
                intent.putExtra("product", dataBase.products.get(position));
                startActivity(intent);
            }
        });

        //setup "components" button in toolbar
        Button componentsButton = (Button) findViewById(R.id.compButton);
        componentsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProductListMenu.this, ComponentListMenu.class);
                startActivity(intent);
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProductListMenu.this, AddProduct.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //save products and materials for next time
        dataBase.saveProducts();
        dataBase.loadComponents();
    }
}
