package com.mobewash.mobewash;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.net.Uri;
import android.os.Bundle;
import android.view.View.OnClickListener;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

public class DetailsActivity extends FragmentActivity implements DetailsFragment.OnFragmentInteractionListener {

    private EditText FirstNameText;
    private EditText LastNameText;
    private EditText PhoneText;
    private EditText EmailText;
    private EditText MakeText;
    private EditText ModelText;
    private EditText ColorText;
    private EditText LicenceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
/*
        //Validate that the inputs to the EditTexts are all valid
        FirstNameText = (EditText) findViewById(R.id.FirstName);
        LastNameText = (EditText) findViewById(R.id.LastName);
        PhoneText = (EditText) findViewById(R.id.Phone);
        EmailText = (EditText) findViewById(R.id.Email);
        MakeText = (EditText) findViewById(R.id.Make);
        ModelText = (EditText) findViewById(R.id.Model);
        ColorText = (EditText) findViewById(R.id.Color);
        LicenceText = (EditText) findViewById(R.id.License);

        findViewById(R.id.button).setOnClickListener(new OnClickListener() {
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
        });*/

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
/*
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
*/
}
