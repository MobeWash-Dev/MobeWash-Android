package com.mobewash.mobewash.booking;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobewash.mobewash.R;
import com.stripe.android.model.Card;
import com.stripe.android.view.CardInputWidget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PaymentFragment.OnPaymentFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PaymentFragment} factory method to
 * create an instance of this fragment.
 */
public class PaymentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Set up tag for logging
    public static final String TAG = "Payment Fragment";

    // Views
    private Button mBookButton;
    private EditText mCountry;
    private EditText mZipCode;
    private Spinner mCountrySpinner;
    private ArrayAdapter<String> mAdapter;

    private OnPaymentFragmentInteractionListener mListener;

    private Card userCard;
    private CardInputWidget mCardInputWidget;

    public PaymentFragment() {
        // Required empty public constructor
    }

 /*   public static PaymentFragment newInstance(String param1, String param2) {
        PaymentFragment fragment = new PaymentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        // Find views
        mCardInputWidget = (CardInputWidget) view.findViewById(R.id.card_input_widget);
        mZipCode = (EditText) view.findViewById(R.id.ZipCode);
        mCountrySpinner = (Spinner) view.findViewById(R.id.CountrySpinner);

        //Create Array and Adapter

        final String[] countryCode = getResources().getStringArray(R.array.country_code);
        final String[] countries = getResources().getStringArray(R.array.country_list);
        final HashMap<String, String> countryToCode = new HashMap<String, String>();
        for(int i = 0; i < countryCode.length; i++) {
            countryToCode.put(countries[i], countryCode[i]);


        }

        ArrayList<String> spinnerArray = new ArrayList<String>(Arrays.asList(countries));
        mAdapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item,spinnerArray);
        //Set dropdown type
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set up Spinner
        mCountrySpinner.setAdapter(mAdapter);

        // Set up Book Button
        mBookButton = (Button) view.findViewById(R.id.BookButton);

        //Set On Click Listener
        mBookButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Boolean hasError = false;

                // Handle Card Input
                userCard = mCardInputWidget.getCard();

                if(userCard == null){
                    Toast.makeText(getActivity(),
                            "Please Fill Out Fields",
                            Toast.LENGTH_LONG
                    ).show();
                    hasError = true;
                }

                // Handle Country Input
                String country = mCountrySpinner.getSelectedItem().toString();
                String code = countryToCode.get(country);

                // Handle Zip Code Input
                String zipCode = mZipCode.getText().toString();

                if(zipCode.isEmpty()) {
                    mZipCode.setError("Required");
                    hasError = true;
                }

                // Error Check Input
                if(!hasError) {
                    userCard.setAddressCountry(code);
                    userCard.setAddressZip(zipCode);

                    if (userCard.validateCard()) {

                        mListener.onBookPressed(userCard);

                    } else {

                        // Show error message

                    }
                }
            }
        });

        return view;
    }

    public interface OnPaymentFragmentInteractionListener {
        void onBookPressed(Card card);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPaymentFragmentInteractionListener) {
            mListener = (OnPaymentFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPaymentFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
