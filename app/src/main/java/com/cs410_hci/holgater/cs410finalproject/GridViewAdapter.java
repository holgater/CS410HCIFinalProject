package com.cs410_hci.holgater.cs410finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Richard on 7/16/2016.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] nameId;
    private final int[] ImageId;

    public GridViewAdapter(Context c, String[] nameId, int[] imageId) {
        this.mContext = c;
        this.nameId = nameId;
        this.ImageId = imageId;
    }

    public int getCount() {
        return nameId.length;
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
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            gridView = new View(mContext);
            gridView = inflater.inflate(R.layout.item_grid_single, null);
            TextView textView = (TextView) gridView.findViewById(R.id.gridText);
            ImageView imageView = (ImageView) gridView.findViewById(R.id.gridImage);
            textView.setText(nameId[position]);
            imageView.setImageResource(ImageId[position]);
        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

}
