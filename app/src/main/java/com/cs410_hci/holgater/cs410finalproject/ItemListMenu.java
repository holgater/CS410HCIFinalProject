package com.cs410_hci.holgater.cs410finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class ItemListMenu extends AppCompatActivity {

    GridView gridView;
    String[] nameId = {
        "name1",
        "name2",
        "name3",
        "name4",
        "name5"
    };

    int[] imageId = {
        R.drawable.sample_1,
        R.drawable.sample_2,
        R.drawable.sample_3,
        R.drawable.sample_4,
        R.drawable.sample_5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_menu);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new GridViewAdapter(this, nameId, imageId));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //TODO
                //create intent to start ItemEditMenu activity
                Intent intent = new Intent(ItemListMenu.this, ItemEditMenu.class);
                intent.putExtra("item", nameId[position]);
                startActivity(intent);
            }
        });
    }
}
