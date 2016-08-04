package com.cs410_hci.holgater.cs410finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Load_Items extends AppCompatActivity {

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

        Intent intent = new Intent(Load_Items.this, ProductListMenu.class);
        startActivity(intent);

    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load__items);




        Intent intent = new Intent(Load_Items.this, ProductListMenu.class);
        startActivity(intent);
    }*/
}
