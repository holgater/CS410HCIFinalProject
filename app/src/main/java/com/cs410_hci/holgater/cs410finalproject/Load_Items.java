package com.cs410_hci.holgater.cs410finalproject;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Load_Items extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load__items);

        DataBase.loadProducts();
        //load materials
        DataBase.loadComponents();

        //Fill in database on a first run
        for (int i = 0; i < 14; ++i) {
            DataBase.products.add(new Product(Test.pNameId[i], BitmapFactory.decodeResource(getResources(), Test.pImageId[i]), Test.pItemInStockNumId[i], Test.pDescription[i]));
        }

        for (int i = 0; i < 14; ++i) {
            DataBase.components.add(new Component(Test.cNameId[i], BitmapFactory.decodeResource(getResources(), Test.cImageId[i]), Test.cItemInStockNumId[i], Test.cDescription[i]));
        }

        Intent intent = new Intent(Load_Items.this, ProductListMenu.class);
        startActivity(intent);
    }
}
