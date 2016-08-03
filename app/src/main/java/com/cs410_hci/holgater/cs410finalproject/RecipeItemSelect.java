package com.cs410_hci.holgater.cs410finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RecipeItemSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_item_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //create and populate the table
        TableLayout table = (TableLayout) findViewById(R.id.itemSelectTable);
        //create a row for each component
        for(int i = 0; i < Test.cNameId.length; ++i) {
            //define the icon, name, and amount of each component
            //icon
            ImageView icon = new ImageView(this);
            icon.setImageResource(Test.cImageId[i]);
            //name
            TextView name = new TextView(this);
            name.setText(Test.cNameId[i]);
            name.setTextSize(20);
            //amount
            TextView amount = new TextView(this);
            amount.setText(String.valueOf(Test.cItemInStockNumId[i]));
            amount.setTextSize(20);
            //make a row
            TableRow row = new TableRow(this);

            //add icon
            row.addView(icon);
            //edit icon size
            icon.setLayoutParams(new TableRow.LayoutParams(200, 200));
            //add name
            row.addView(name);
            //edit name size
            name.setLayoutParams(new TableRow.LayoutParams(400, 200));
            //add quantity
            row.addView(amount);
            //center items vertically
            row.setGravity(Gravity.FILL_HORIZONTAL);
            //edit quantity size
            // amount.setLayoutParams(new TableRow.LayoutParams(200, 200));
            //set row id
            row.setId(i);
            row.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           int id = view.getId();

                                           Intent intent = new Intent();
                                           intent.putExtra("ComponentReturn", Test.cComponents[id]);
                                           setResult(RecipeItemSelect.RESULT_OK, intent);
                                           finish();
                                       }
                                   });
            //add the row to the table
            table.addView(row);
        }


    }

}
