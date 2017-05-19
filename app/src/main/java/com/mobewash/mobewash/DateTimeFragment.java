package com.mobewash.mobewash;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DateTimeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DateTimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateTimeFragment extends Fragment implements DatePickerDialog.OnDateSetListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText dateEditText;
    private EditText timeEditText;

    private OnFragmentInteractionListener mListener;
    private Calendar firstDate;
    private DatePickerDialog dpd;
    private View datePickerView;

    public DateTimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DateTimeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DateTimeFragment newInstance(String param1, String param2) {
        DateTimeFragment fragment = new DateTimeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        Calendar now = Calendar.getInstance();
        final DatePickerDialog dpd = DatePickerDialog.newInstance(
                DateTimeFragment.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        int[] enabledDays = {2,3};
        Calendar[] enabledDates = new Calendar[enabledDays.length*100];
        Calendar firstDate = addEnabledDates(enabledDates,enabledDays);
        this.firstDate = firstDate;
        dpd.setSelectableDays(enabledDates);
        this.dpd = dpd;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_time, container, false);
        this.datePickerView = view;
        EditText dateEditText = (EditText)view.findViewById(R.id.dateEditText);
        //EditText timeEditText = (EditText)findViewById(R.id.timeEditText);
        this.dateEditText = dateEditText;
        //this.timeEditText = timeEditText;
        dateEditText.setText("" + firstDate.get(Calendar.DATE) + "/" + (firstDate.get(Calendar.MONTH) + 1) + "/" + firstDate.get(Calendar.YEAR));

        dpd.show(getActivity().getFragmentManager(), "Date Picker");
        dateEditText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dpd.show(getActivity().getFragmentManager(),"Date Picker");
            }
        });

        populateTimes();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        dateEditText.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        populateTimes();
    }

    private void populateTimes() {
        String[] myTimes = { "10:00am", "12:00pm", "6:00pm" };
        ListAdapter timeAdapter= new customTimeAdapter(getContext(),myTimes);
        ListView timeListView = (ListView)(datePickerView.findViewById(R.id.timeListView));
        timeListView.setAdapter(timeAdapter);
    }

    private Calendar addEnabledDates(Calendar[] enabledDates, int[] enabledDays) {

        //our format of JSON file has 0-6 for Monday to Sunday which isn't desirable
        boolean foundFirstDate = false;
        Calendar firstDate = Calendar.getInstance();
        boolean[] formattedEnabledDays = new boolean[7];
        for(int i = 0; i < enabledDays.length ; i++) {
            if(enabledDays[i] == 6)
                formattedEnabledDays[0] = true;
            else
                formattedEnabledDays[enabledDays[i]+1] = true;
        }//set formattedEnabledDays to be true
        for(int i = 0; i < 7; i++) {
            Log.d("myTag","" + i + " " + formattedEnabledDays[i]);
        }
        int counter = 0;
        for(int i = 0; i < 7; i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE,i);
            if(formattedEnabledDays[cal.get(Calendar.DAY_OF_WEEK)-1]) {
                if(!foundFirstDate) {
                    foundFirstDate = true;
                    firstDate = cal;
                }
                for (int j = 0; j < 100; j++) {
                    Calendar calToAdd = Calendar.getInstance();
                    calToAdd.add(Calendar.DATE,i);
                    calToAdd.add(Calendar.DATE,j*7);
                    enabledDates[counter] = calToAdd;
                    counter++;
                }
            }
        }
        return firstDate;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
