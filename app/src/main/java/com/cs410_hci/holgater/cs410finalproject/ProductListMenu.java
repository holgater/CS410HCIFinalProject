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
    //recipes for products

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

        List[] pRecipe = new List[]{
                asList(new Component(Test.cNameId[1], BitmapFactory.decodeResource(getResources(),Test.cImageId[1]), Test.cItemInStockNumId[1], Test.cDescription[1]), new Component(Test.cNameId[2], BitmapFactory.decodeResource(getResources(),Test.cImageId[2]), Test.cItemInStockNumId[2], Test.cDescription[2])),
            asList(new Component(Test.cNameId[1], BitmapFactory.decodeResource(getResources(),Test.cImageId[1]), Test.  cItemInStockNumId[1], Test.cDescription[1]), new Component(Test.cNameId[3], BitmapFactory.decodeResource(getResources(),Test.cImageId[3]), Test.cItemInStockNumId[3], Test.cDescription[3]), new Component(Test.cNameId[4], BitmapFactory.decodeResource(getResources(),Test.cImageId[4]), Test.cItemInStockNumId[4], Test.cDescription[4])),
            asList(new Component(Test.cNameId[3], BitmapFactory.decodeResource(getResources(),Test.cImageId[3]), Test.cItemInStockNumId[3], Test.cDescription[3]), new Component(Test.cNameId[5], BitmapFactory.decodeResource(getResources(),Test.cImageId[5]), Test.cItemInStockNumId[5], Test.cDescription[5]), new Component(Test.cNameId[7], BitmapFactory.decodeResource(getResources(),Test.cImageId[7]), Test.cItemInStockNumId[7], Test.cDescription[7]), new Component(Test.cNameId[10], BitmapFactory.decodeResource(getResources(),Test.cImageId[10]), Test.cItemInStockNumId[10], Test.cDescription[10])),
            asList(new Component(Test.cNameId[6], BitmapFactory.decodeResource(getResources(),Test.cImageId[6]), Test.cItemInStockNumId[6], Test.cDescription[6]), new Component(Test.cNameId[9], BitmapFactory.decodeResource(getResources(),Test.cImageId[9]), Test.cItemInStockNumId[9], Test.cDescription[9])),
            asList(new Component(Test.cNameId[5], BitmapFactory.decodeResource(getResources(),Test.cImageId[5]), Test.cItemInStockNumId[5], Test.cDescription[5]), new Component(Test.cNameId[11], BitmapFactory.decodeResource(getResources(),Test.cImageId[11]), Test.cItemInStockNumId[11], Test.cDescription[11]), new Component(Test.cNameId[3], BitmapFactory.decodeResource(getResources(),Test.cImageId[3]), Test.cItemInStockNumId[3], Test.cDescription[3])),
            asList(new Component(Test.cNameId[13], BitmapFactory.decodeResource(getResources(),Test.cImageId[13]), Test.cItemInStockNumId[13], Test.cDescription[13]), new Component(Test.cNameId[12], BitmapFactory.decodeResource(getResources(),Test.cImageId[12]), Test.cItemInStockNumId[12], Test.cDescription[12]), new Component(Test.cNameId[7], BitmapFactory.decodeResource(getResources(),Test.cImageId[7]), Test.cItemInStockNumId[7], Test.cDescription[7]), new Component(Test.cNameId[8], BitmapFactory.decodeResource(getResources(),Test.cImageId[8]), Test.cItemInStockNumId[8], Test.cDescription[8])),
            asList(new Component(Test.cNameId[2], BitmapFactory.decodeResource(getResources(),Test.cImageId[2]), Test.cItemInStockNumId[2], Test.cDescription[2]), new Component(Test.cNameId[9], BitmapFactory.decodeResource(getResources(),Test.cImageId[9]), Test.cItemInStockNumId[9], Test.cDescription[9]), new Component(Test.cNameId[8], BitmapFactory.decodeResource(getResources(),Test.cImageId[8]), Test.cItemInStockNumId[8], Test.cDescription[8])),
            asList(new Component(Test.cNameId[4], BitmapFactory.decodeResource(getResources(),Test.cImageId[4]), Test.cItemInStockNumId[4], Test.cDescription[4]), new Component(Test.cNameId[5], BitmapFactory.decodeResource(getResources(),Test.cImageId[5]), Test.cItemInStockNumId[5], Test.cDescription[5])),
            asList(new Component(Test.cNameId[5], BitmapFactory.decodeResource(getResources(),Test.cImageId[5]), Test.cItemInStockNumId[5], Test.cDescription[5]), new Component(Test.cNameId[14], BitmapFactory.decodeResource(getResources(),Test.cImageId[14]), Test.cItemInStockNumId[14], Test.cDescription[14]), new Component(Test.cNameId[13], BitmapFactory.decodeResource(getResources(),Test.cImageId[13]), Test.cItemInStockNumId[13], Test.cDescription[13]), new Component(Test.cNameId[10], BitmapFactory.decodeResource(getResources(),Test.cImageId[10]), Test.cItemInStockNumId[10], Test.cDescription[10])),
            asList(new Component(Test.cNameId[13], BitmapFactory.decodeResource(getResources(),Test.cImageId[13]), Test.cItemInStockNumId[13], Test.cDescription[13]), new Component(Test.cNameId[3], BitmapFactory.decodeResource(getResources(),Test.cImageId[3]), Test.cItemInStockNumId[3], Test.cDescription[3])),
            asList(new Component(Test.cNameId[14], BitmapFactory.decodeResource(getResources(),Test.cImageId[14]), Test.cItemInStockNumId[14], Test.cDescription[14]), new Component(Test.cNameId[3], BitmapFactory.decodeResource(getResources(),Test.cImageId[3]), Test.cItemInStockNumId[3], Test.cDescription[3]), new Component(Test.cNameId[7], BitmapFactory.decodeResource(getResources(),Test.cImageId[7]), Test.cItemInStockNumId[7], Test.cDescription[7])),
            asList(new Component(Test.cNameId[6], BitmapFactory.decodeResource(getResources(),Test.cImageId[6]), Test.cItemInStockNumId[6], Test.cDescription[6])),
            asList(new Component(Test.cNameId[3], BitmapFactory.decodeResource(getResources(),Test.cImageId[3]), Test.cItemInStockNumId[3], Test.cDescription[3])),
            asList(new Component(Test.cNameId[6], BitmapFactory.decodeResource(getResources(),Test.cImageId[6]), Test.cItemInStockNumId[6], Test.cDescription[6]), new Component(Test.cNameId[3], BitmapFactory.decodeResource(getResources(),Test.cImageId[3]), Test.cItemInStockNumId[3], Test.cDescription[3]))
        };

        for (int i = 0; i < 14; ++i) {
            DataBase.products.add(new Product(Test.pNameId[i], BitmapFactory.decodeResource(getResources(), Test.pImageId[i]), Test.pItemInStockNumId[i], Test.pDescription[i], pRecipe[i]));
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
