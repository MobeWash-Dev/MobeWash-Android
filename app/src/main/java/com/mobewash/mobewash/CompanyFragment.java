package com.mobewash.mobewash;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mobewash.mobewash.models.CompanyData;

import org.json.JSONArray;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCompanyFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CompanyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompanyFragment extends Fragment {

    private ListView listView;
    DataSingletonClass sharedData = DataSingletonClass.getInstance();

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

        RestRequester myReq = new RestRequester(this.getContext());
        myReq.getArray("https://mobe-server.herokuapp.com/api/company", new RestRequester.OnArrayRequestCompleteListener() {
            @Override
            public void onArrayRequestComplete(Exception err, JSONArray jsonArray) {
                if(err == null) {
                    final ArrayList<CompanyData> data = JSONParser.parseCompanyData(jsonArray);
                    ArrayList<String> names = new ArrayList<String>();
                    for( int i = 0; i < data.size(); i++){
                        String coke = data.get(i).getName() + ":  " + data.get(i).getaddress();
                        names.add(coke);
                    }
                    String [] items = names.toArray(new String[names.size()]);

                    ArrayAdapter<String> itemsAdapter =
                            new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,items);
                    listView.setAdapter(itemsAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                            // Your code for item clicks
                            sharedData.setCompanyName(data.get(pos).getName());
                            mListener.onCompanySelected();
                        }
                    });
                }
            }

        });

        return view;
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

    interface OnCompanyFragmentInteractionListener {
        void onCompanySelected();
    }
}