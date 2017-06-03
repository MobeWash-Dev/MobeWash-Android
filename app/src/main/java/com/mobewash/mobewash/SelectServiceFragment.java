package com.mobewash.mobewash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.CallbackManager;
import com.mobewash.mobewash.models.WashService;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Luyin on 05/27/17.
 */

public class SelectServiceFragment extends android.support.v4.app.Fragment{

    private ListView mListView;
    private CallbackManager mCallbackManager;
    private OnSelectServiceFragmentInteractionListener mListener;
    private RestRequester restRequester;
    private static String TAG = "SelectServiceFragment";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restRequester = new RestRequester(getActivity());
        mCallbackManager = CallbackManager.Factory.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_select_service, container, false);
        mListView = (ListView) view.findViewById(R.id.service_list_view);

        /*Service mobewash = new Service("Mobe", "The full exterior car wash service 55min", "24");
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
                DataSingletonClass.getInstance().setSelectedService(result);
                mListener.onServiceSelect();
            }
        });
        */

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        restRequester.getArray("https://mobe-server.herokuapp.com/api/service", new RestRequester.OnArrayRequestCompleteListener() {
            @Override
            public void onArrayRequestComplete(Exception err, JSONArray jsonArray) {
                if (err != null) {
                    Log.e(TAG, "ARRAY REQUEST ERROR", err);
                } else {
                    Log.d(TAG, jsonArray.toString());
                    ArrayList<WashService> washServiceList = JSONParser.parseWashService(jsonArray);
                    Log.d(TAG, washServiceList.toString());
                    ServiceListAdapter serviceListAdapter = new ServiceListAdapter(getActivity(), washServiceList);
                    mListView.setAdapter(serviceListAdapter);
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            WashService result = (WashService) parent.getItemAtPosition(position);
                            DataSingletonClass.getInstance().setService(result);
                            mListener.onServiceSelect();
                        }
                    });
                }
            }
        });
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
