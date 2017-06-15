package com.mobewash.mobewash.booking;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.mobewash.mobewash.R;
import com.mobewash.mobewash.adapters.CustomTimeAdapter;
import com.mobewash.mobewash.models.Appointment;
import com.mobewash.mobewash.models.BookingDataSingleton;
import com.mobewash.mobewash.models.SlotTime;
import com.mobewash.mobewash.utils.RestRequester;

import org.json.JSONArray;

import java.util.ArrayList;


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
        Button button = (Button) view.findViewById(R.id.button_edit_date);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onEditCalendar();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //populateTimes("2017-05-25");
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

    public void populateTimes(String datetime) {
        RestRequester restRequester = new RestRequester(getContext());
        String url = "https://mobe-server.herokuapp.com/api/appointment/" + datetime;
        restRequester.getArray(url, new RestRequester.OnArrayRequestCompleteListener() {
            @Override
            public void onArrayRequestComplete(Exception err, JSONArray jsonArray) {
                ArrayList<Appointment> appointments = Appointment.parseAppointmentList(jsonArray);
                updateList(appointments);
            }
        });
        /*
        String[] myTimes = { "10:00am", "12:00pm", "6:00pm" };
        ListAdapter timeAdapter= new CustomTimeAdapter(getContext(),myTimes);
        ListView timeListView = (ListView) getView().findViewById(R.id.timeListView);
        timeListView.setAdapter(timeAdapter);
        */
    }

    private void updateList(ArrayList<Appointment> appointments) {
        ArrayList<SlotTime> slots = new ArrayList<>();
        BookingDataSingleton singleton = BookingDataSingleton.getInstance();
        int slotCap = singleton.getCompanyData().getSetting().getslotCap();
        int start = singleton.getCompanyData().getSetting().getStartTime();
        int end = singleton.getCompanyData().getSetting().getEndTime();
        int increment = singleton.getCompanyData().getSetting().getIncrement();
        for (int i = start; i < end; i += increment) {
            slots.add(new SlotTime(i, slotCap, slotCap));
        }
        for (int i = 0; i < appointments.size(); i++) {
            int apptTime = getParsedTime(appointments.get(i).getTime());
            for (SlotTime slotTime : slots) {
                if (slotTime.getTime() == apptTime) {
                    slotTime.setCount(slotTime.getCount() - 1);
                }
            }
        }
        /*
        String[] myTimes = new String[slots.size()];
        for (int i = 0; i < slots.size(); i++) {
            myTimes[i] = TIMES[slots.get(i).getTime()];
        }
        */
        ListAdapter timeAdapter= new CustomTimeAdapter(getContext(), slots);
        ListView timeListView = (ListView) getView().findViewById(R.id.timeListView);
        timeListView.setAdapter(timeAdapter);
        timeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onDateTimeSelected();
            }
        });
    }

    private static final String[] TIMES = {"12:00am","1:00am","2:00am","3:00am","4:00am","5:00am","6:00am",
            "7:00am","8:00am","9:00am","10:00am","11:00am","12:00pm","1:00pm","2:00pm","3:00pm","4:00pm",
            "5:00pm","6:00pm","7:00pm","8:00pm","9:00pm","10:00pm","11:00pm"};

    private int getParsedTime(String time) {
        for (int i = 0; i < TIMES.length; i++) {
            if (time.equals(TIMES[i])) {
                return i;
            }
        }
        return 0;
    }

    public interface OnDateTimeFragmentInteractionListener {
        // TODO: Update argument type and name
        void onDateTimeSelected();
        void onEditCalendar();
    }
}
