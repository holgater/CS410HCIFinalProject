package com.cs410_hci.holgater.cs410finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import static java.util.Arrays.asList;

public class ProductListMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //AppCompatActivity setup
        super.onCreate(savedInstanceState);
        //setup layout
        setContentView(R.layout.activity_product_list_menu);
        //load Products
        DataBase.loadProducts();
        //load materials
        DataBase.loadComponents();

        for (int i = 0; i < 14; ++i) {
            DataBase.products.add(new Product(Test.pNameId[i], BitmapFactory.decodeResource(getResources(), Test.pImageId[i]), Test.pItemInStockNumId[i], Test.pDescription[i], Test.pRecipe[i]));
        }

        //setup toolbar
        ExpandableGridView eGridView = (ExpandableGridView) findViewById(R.id.eGridView);
        eGridView.setAdapter(new GridViewAdapter(this, DataBase.products));
        eGridView.setExpanded(true);

        //setup grid items with onClick listener
        eGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //create intent to start ProductEditMenu activity
                Intent intent = new Intent(ProductListMenu.this, ProductEditMenu.class);
                intent.putExtra("product", DataBase.products.get(position));
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
                //Todo get result and update GridView
                startActivityForResult(intent, 55);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView imageView = (ImageView) findViewById(R.id.ImageView);
        if (requestCode == 1888 && resultCode == RESULT_OK) {
            //ImageView photo = (ImageView) data.getExtras().
            //imageView.setImageBitmap(photo);
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
