package com.cs410_hci.holgater.cs410finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Richard on 7/16/2016.
 */
public class GridViewAdapter extends BaseAdapter {
    //context
    private Context context;
    //array of products and materials that will be displayed
    private final List<? extends Item> items;

    public GridViewAdapter(Context c, List<? extends Item> items) {
        this.context = c;
        this.items = items;
    }

    public int getCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.item_grid_single, null);
            TextView textView = (TextView) gridView.findViewById(R.id.gridText);
            ImageView imageView = (ImageView) gridView.findViewById(R.id.gridImage);
            textView.setText(items.get(position).getName());
            imageView.setImageResource(items.get(position).getImage());


        } else {
            gridView = convertView;
        }

        return gridView;
    }
}
