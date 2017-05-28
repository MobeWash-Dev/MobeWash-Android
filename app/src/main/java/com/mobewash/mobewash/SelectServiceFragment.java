package com.mobewash.mobewash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.CallbackManager;

import java.util.ArrayList;

/**
 * Created by Luyin on 05/27/17.
 */

public class SelectServiceFragment extends android.support.v4.app.Fragment{

    private ListView mListView;
    private CallbackManager mCallbackManager;
    private OnSelectServiceFragmentInteractionListener mListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCallbackManager = CallbackManager.Factory.create();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_select_service, container, false);

        mListView = (ListView) view.findViewById(R.id.service_list_view);
        Service mobewash = new Service("Mobe", "The full exterior car wash service 55min", "24");
        Service mobewashPlus = new Service("MobePlus", "The full exterior and interior car wash service", "35");
        ArrayList<Service> services = new ArrayList<Service>();
        services.add(mobewash);
        services.add(mobewashPlus);
        ServiceListAdapter serviceListAdapter = new ServiceListAdapter(getActivity(), services);
        mListView.setAdapter(serviceListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service result = (Service) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(), result.getTitle(), Toast.LENGTH_LONG).show();
            }
        });

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSelectServiceFragmentInteractionListener) {
            mListener = (OnSelectServiceFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSelectServiceFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSelectServiceFragmentInteractionListener {
        void onServiceSelect();
    }

}
