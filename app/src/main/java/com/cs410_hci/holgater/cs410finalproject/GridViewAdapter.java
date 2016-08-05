package com.cs410_hci.holgater.cs410finalproject;

import android.content.Context;
import android.util.Log;
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
public class GridViewAdapter extends BaseAdapter{
    private final String param;
    //context
    private Context context;
    //array of products and materials that will be displayed
    private final List<? extends Item> items;

    public GridViewAdapter(Context c, List<? extends Item> items, String param) {
        this.context = c;
        this.items = items;
        this.param = param;
    }

    public int getCount() {
        if(items != null) {
            return items.size();
        } else {
            return 0;
        }
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
            int number = 0;
            if( items.get(position).getClass().equals(Component.class) ){
                number = ((Component)items.get(position)).getCounts();
            }

            if(!param.contains("withCount")) {
                gridView = new View(context);
                gridView = inflater.inflate(R.layout.item_grid_single, null);
                TextView textView = (TextView) gridView.findViewById(R.id.gridText);
                ImageView imageView = (ImageView) gridView.findViewById(R.id.gridImage);
                textView.setText(items.get(position).getName());
                imageView.setImageBitmap(items.get(position).getImage());
            } else {
                gridView = new View(context);
                gridView = inflater.inflate(R.layout.item_grid_single_with_numbers, null);
                TextView textView = (TextView) gridView.findViewById(R.id.gridText);
                ImageView imageView = (ImageView) gridView.findViewById(R.id.gridImage);
                textView.setText(items.get(position).getName());
                imageView.setImageBitmap(items.get(position).getImage());

                TextView textViewTotal = (TextView) gridView.findViewById(R.id.gridTextTotal);
                textViewTotal.setText("Needs: " + ((Component)items.get(position)).getCounts());
            }
        } else {
            gridView = convertView;
        }

        return gridView;
    }
}
