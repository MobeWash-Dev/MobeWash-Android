package com.mobewash.mobewash.booking;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobewash.mobewash.R;
import com.mobewash.mobewash.adapters.CompanyArrayAdapter;
import com.mobewash.mobewash.models.BookingDataSingleton;
import com.mobewash.mobewash.models.CompanyData;
import com.mobewash.mobewash.models.JSONParser;
import com.mobewash.mobewash.utils.RestRequester;

import org.json.JSONArray;

import java.util.ArrayList;


public class CompanyFragment extends Fragment {

    private static final String TAG = "CompanyFragment";

    private ListView listView;
    BookingDataSingleton sharedData = BookingDataSingleton.getInstance();

    private OnCompanyFragmentInteractionListener mListener;

    public CompanyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_company, container, false);
        listView = (ListView) view.findViewById(R.id.List);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RestRequester myReq = new RestRequester(this.getContext());
        myReq.getArray("https://mobe-server.herokuapp.com/api/company", new RestRequester.OnArrayRequestCompleteListener() {
            @Override
            public void onArrayRequestComplete(Exception err, JSONArray jsonArray) {
                if(err == null) {
                    final ArrayList<CompanyData> companies = JSONParser.parseCompanyData(jsonArray);
                    Log.d(TAG, "Companies: " + companies);
                    /*
                    ArrayList<String> names = new ArrayList<String>();
                    for( int i = 0; i < data.size(); i++){
                        String coke = data.get(i).getServiceName() + ":  " + data.get(i).getaddress();
                        names.add(coke);
                    }

                    String [] items = names.toArray(new String[names.size()]);

                    ArrayAdapter<String> itemsAdapter =
                            new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,items);
                    */
                    CompanyArrayAdapter itemsAdapter = new CompanyArrayAdapter(getContext(), companies);
                    listView.setAdapter(itemsAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                            // Your code for item clicks
                            sharedData.setCompanyData(companies.get(pos));
                            mListener.onCompanySelected();
                        }
                    });
                } else {
                    Log.e(TAG, "REQUEST ERROR", err);
                }
            }

        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCompanyFragmentInteractionListener) {
            mListener = (OnCompanyFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCompanyFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnCompanyFragmentInteractionListener {
        void onCompanySelected();
    }
}