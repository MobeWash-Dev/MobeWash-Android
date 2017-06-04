package com.mobewash.mobewash;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;


public class DateTimeFragment extends Fragment{

    private OnDateTimeFragmentInteractionListener mListener;

    public DateTimeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_date_time, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateTimes("date-time");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDateTimeFragmentInteractionListener) {
            mListener = (OnDateTimeFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDateTimeFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void populateTimes(String datetime) {

        /*
        String[] myTimes = { "10:00am", "12:00pm", "6:00pm" };
        ListAdapter timeAdapter= new CustomTimeAdapter(getContext(),myTimes);
        ListView timeListView = (ListView) getView().findViewById(R.id.timeListView);
        timeListView.setAdapter(timeAdapter);
        */
    }

    public interface OnDateTimeFragmentInteractionListener {
        // TODO: Update argument type and name
        void onDateTimeFragmentInteraction(Uri uri);
    }
}
