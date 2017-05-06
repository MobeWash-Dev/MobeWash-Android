package com.mobewash.mobewash;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;

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
    private EditText mCardInput;
    private EditText mCardCVC;
    private EditText mExpirationDate;
    private Button mBookButton;
    private EditText mCountry;
    private EditText mZipCode;

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
        View view =inflater.inflate(R.layout.fragment_payment, container, false);

        mCardInputWidget = (CardInputWidget) getActivity().findViewById(R.id.card_input_widget);

        // Set up Text Boxes
     /*   mCardInput = (EditText) view.findViewById(R.id.CardNumber);
        mCardCVC = (EditText) view.findViewById(R.id.CVC);
        mExpirationDate = (EditText) view.findViewById(R.id.ExpirationDate);*/
        mCountry = (EditText) view.findViewById(R.id.Country);
        mZipCode = (EditText) view.findViewById(R.id.ZipCode);

        // Set up Book Button
        mBookButton = (Button) view.findViewById(R.id.BookButton);

        //Set On Click Listener
        mBookButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Read in the strings for card info
             /*   String card= mCardInput.getText().toString();
                String cvc= mCardCVC.getText().toString();
                String exp= mExpirationDate.getText().toString();*/
                String zipCode = mZipCode.getText().toString();
                String countryCode = mCountry.getText().toString();

               // if(checkInput(card,cvc,exp)){
                    // Set Address for Country and Zip Code
                    userCard.setAddressCountry(countryCode);
                    userCard.setAddressZip(zipCode);

                    if(userCard.validateCard()) {
                        mListener.onBookPressed(userCard);
                    }
                //}

               // else{
                    // Show error message
                //}
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

    private boolean checkInput(String card, String cvc, String date){
        boolean cardCheck = !TextUtils.isEmpty(card);
        boolean cvcCheck = !TextUtils.isEmpty(cvc);
        boolean dateCheck = !TextUtils.isEmpty(date);
        return cardCheck && cvcCheck &&dateCheck;
    }

    /* Deciding which save card method to use*/
    private boolean validateCard(String cardNumber, Integer cardExpMonth, Integer cardExpYear, String cardCVC,
                                String billingName, String addressLine1, String addressLine2, String addressCity,
                                String addressState, String addressZip, String addressCountry) {
        userCard = new Card(
                cardNumber,
                cardExpMonth,
                cardExpYear,
                cardCVC,
                billingName,
                addressLine1,
                addressLine2,
                addressCity,
                addressState,
                addressZip,
                addressCountry,
                "USD"
        );
        if(userCard.validateNumber() && userCard.validateCVC() && userCard.validateExpiryDate()){

        }

        return true;
    }


    private void getCard(){
        Card card = mCardInputWidget.getCard();
        if (userCard == null) {
            return;
        }
    }
    private void chargeCard() {


        Stripe stripe = new Stripe(getView().getContext(), "pk_test_6pRNASCoBOKtIshFeQd4XMUh");
        stripe.createToken(
                userCard,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Send token to your server
                    }
                    public void onError(Exception error) {
                        // Show localized error message
                    }
                }
        );
    }
}
