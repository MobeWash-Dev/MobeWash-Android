package com.mobewash.mobewash;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luyin on 05/04/17.
 */

public class GeoAutoCompleteAdapter extends BaseAdapter implements Filterable {

    private static final int MAX_RESULTS = 10;
    private Context mContext;
    private List<GeoSearchResult> resultList;

    public GeoAutoCompleteAdapter(Context context) {
        mContext = context;
        resultList = new ArrayList();
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public GeoSearchResult getItem(int index) {
        return (GeoSearchResult) resultList.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.geo_search_result_item, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.geo_search_result_text)).setText(getItem(position).getAddress());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    Log.d("here", "constraint not null");
                    List<GeoSearchResult> locations = findLocations(mContext, constraint.toString());
                    Log.d("there", " "+ locations);
                    // Assign the data to the FilterResults
                    filterResults.values = locations;
                    if(locations == null){
                        Log.d("nolocation", "there is no location");
                    }
                    filterResults.count = locations.size();
                }
                else{
                    filterResults.count = 0;
                    filterResults.values = new ArrayList<>();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count >= 0) {
                    resultList = (List<GeoSearchResult>) results.values;
                    notifyDataSetChanged();
                    Log.e("results is not null", "result is not null");
                } else {
                    Log.e("results is null", "result is null");
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }

    private List<GeoSearchResult> findLocations(Context context, String query_text) {

        List<GeoSearchResult> geo_search_results = new ArrayList<GeoSearchResult>();
        Log.d("hereiam", " inside findlocation");

        Geocoder geocoder = new Geocoder(context, context.getResources().getConfiguration().locale);
        if (!Geocoder.isPresent()){
            Log.e("Geocoder", " not defined");
        }
        List<Address> addresses = null;

        try {
            // Getting a maximum of 15 Address that matches the input text
            addresses = geocoder.getFromLocationName(query_text, 15);
            Log.d("addresses", "" + addresses);

            for(int i=0;i<addresses.size();i++){
                Address address = (Address) addresses.get(i);
                if(address.getMaxAddressLineIndex() != -1)
                {
                    geo_search_results.add(new GeoSearchResult(address));
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("iamhere", " " + geo_search_results.size());
        return geo_search_results;
    }
}
