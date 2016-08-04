package com.cs410_hci.holgater.cs410finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

public class ProductListMenu extends AppCompatActivity {
    //recipes for products
    ExpandableGridView eGridView;
    GridViewAdapter adapter;
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Perhaps set content view here

        prefs = getSharedPreferences("com.cs410_hci.holgater.cs410finalproject", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            DataBase.loadProducts();
            //load materials
            DataBase.loadComponents();

            //Fill in database on a first run
            for (int i = 0; i < 14; ++i) {
                DataBase.products.add(new Product(Test.pNameId[i], BitmapFactory.decodeResource(getResources(), Test.pImageId[i]), Test.pItemInStockNumId[i], Test.pDescription[i]));
            }

            for (int i = 0; i < 14; ++i) {
                DataBase.components.add(new Component(Test.cNameId[i], BitmapFactory.decodeResource(getResources(), Test.cImageId[i]), Test.cItemInStockNumId[i], Test.cDescription[i], Test.cUnitType[i]));
            }
            prefs.edit().putBoolean("firstrun", false).apply();
        }
        //setup layout
        setContentView(R.layout.activity_product_list_menu);

        //setup toolbar
        adapter = new GridViewAdapter(this, DataBase.products);
        eGridView = (ExpandableGridView) findViewById(R.id.eGridView);
        eGridView.setAdapter(adapter);
        eGridView.setExpanded(true);

        //setup grid items with onClick listener
        eGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //create intent to start ProductEditMenu activity
                Intent intent = new Intent(ProductListMenu.this, ProductEditMenu.class);
                intent.putExtra("position", position);
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
                startActivityForResult(intent, 55);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 55 && resultCode == RESULT_OK) {
            //Update grid view with new updated List.
            eGridView.setAdapter(new GridViewAdapter(this, DataBase.products));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //save products and materials for next time
        DataBase.saveProducts();
        DataBase.loadComponents();
    }
}
