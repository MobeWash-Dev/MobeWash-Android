package com.mobewash.mobewash;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private EditText FirstNameText;
    private EditText LastNameText;
    private EditText PhoneText;
    private EditText EmailText;
    private EditText MakeText;
    private EditText ModelText;
    private EditText ColorText;
    private EditText LicenceText;


    private OnFragmentInteractionListener mListener;

    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        //Validate that the inputs to the EditTexts are all valid
        FirstNameText = (EditText) view.findViewById(R.id.FirstName);
        LastNameText = (EditText) view.findViewById(R.id.LastName);
        PhoneText = (EditText) view.findViewById(R.id.Phone);
        EmailText = (EditText) view.findViewById(R.id.Email);
        MakeText = (EditText) view.findViewById(R.id.Make);
        ModelText = (EditText) view.findViewById(R.id.Model);
        ColorText = (EditText) view.findViewById(R.id.Color);
        LicenceText = (EditText) view.findViewById(R.id.License);

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                final String email = EmailText.getText().toString();
                if (!isValidEmail(email)) {
                    EmailText.setError("Invalid Email");
                }

                final String firstName = FirstNameText.getText().toString();
                if (!isValidName(firstName)) {
                    FirstNameText.setError("Invalid Name");
                }

                final String lastName = LastNameText.getText().toString();
                if (!isValidName(lastName)) {
                    LastNameText.setError("Invalid Name");
                }

                final String phone = PhoneText.getText().toString();
                if (!isValidPhone(phone)) {
                    PhoneText.setError("Invalid Phone, Please Enter 10 Digit phone number");
                }

                final String License = LicenceText.getText().toString();
                if (!isValidName(License)) {
                    LicenceText.setError("Invalid License");
                }

                final String Make = MakeText.getText().toString();
                final String Model = ModelText.getText().toString();
                final String Color = ColorText.getText().toString();
                if (!isValidName(Make)) {
                    MakeText.setError("Invalid");
                }
                if (!isValidName(Model)) {
                    ModelText.setError("Invalid");
                }
                if (!isValidName(Color)) {
                    ColorText.setError("Invalid");
                }
            }
        });

        return view;
    }

    // validating Names
    private boolean isValidName(String name) {
        if (name != null && name.length() > 0) {
            return true;
        }
        return false;
    }

    // validating Phone
    private boolean isValidPhone(String phone) {
        if (phone != null && phone.length() > 9) {
            return true;
        }
        return false;
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
