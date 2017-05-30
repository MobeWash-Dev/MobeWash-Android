package com.mobewash.mobewash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobewash.mobewash.models.WashService;

import java.util.ArrayList;

/**
 * Created by Luyin on 05/27/17.
 */

public class ServiceListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<WashService> mDataSource;

    public ServiceListAdapter(Context context, ArrayList<WashService> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.list_service_item, parent, false);
        // Get title element
        TextView titleTextView =
                (TextView) rowView.findViewById(com.mobewash.mobewash.R.id.recipe_list_title);

// Get subtitle element
        TextView subtitleTextView =
                (TextView) rowView.findViewById(com.mobewash.mobewash.R.id.recipe_list_subtitle);

// Get detail element
        TextView detailTextView =
                (TextView) rowView.findViewById(R.id.service_list_detail);

// Get thumbnail element
        /*ImageView thumbnailImageView =
                (ImageView) rowView.findViewById(R.id.service_list_thumbnail);*/
        WashService recipe = (WashService) getItem(position);

// 2
        titleTextView.setText(recipe.getServiceName());
        subtitleTextView.setText(recipe.getDescription());
        detailTextView.setText("" + recipe.getPaymentAmount());
        return rowView;
    }

}
