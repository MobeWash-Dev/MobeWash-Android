package com.mobewash.mobewash;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobewash.mobewash.models.CompanyData;

import java.util.ArrayList;

/**
 * Created by sidney on 5/29/17.
 */

public class CompanyArrayAdapter extends ArrayAdapter<CompanyData> {

    public CompanyArrayAdapter(Context context, ArrayList<CompanyData> companies) {
        super(context, 0, companies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_company, parent, false);

        String companyName = getItem(position).getName();
        TextView companyNameTextView = (TextView)view.findViewById(R.id.textview_company_name);

        companyNameTextView.setText(companyName);
        return view;
    }
}
