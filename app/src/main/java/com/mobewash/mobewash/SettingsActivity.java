package com.mobewash.mobewash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsActivity extends AppCompatActivity {

    private EditText FirstNameText;
    private EditText LastNameText;
    private EditText PhoneText;
    private EditText EmailText;
    private EditText MakeText;
    private EditText ColorText;
    private EditText LicenseText;
    private EditText ExtrasText;
    private Button mNextButton;

    private DetailsFragment.OnDetailsFragmentInteractionListener mListener;

    DataSingletonClass sharedData = DataSingletonClass.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Validate that the inputs to the EditTexts are all valid
        FirstNameText = (EditText) findViewById(R.id.SettingsFirstName);
        LastNameText = (EditText) findViewById(R.id.SettingsLastName);
        PhoneText = (EditText) findViewById(R.id.SettingsPhone);
        EmailText = (EditText) findViewById(R.id.SettingsEmail);
        MakeText = (EditText) findViewById(R.id.SettingsMake);
        ColorText = (EditText) findViewById(R.id.SettingsColor);
        LicenseText = (EditText) findViewById(R.id.SettingsLicense);
        ExtrasText = (EditText) findViewById(R.id.SettingsExtraInfo);

        mNextButton = (Button) findViewById(R.id.Settingsbutton_next);

        FirstNameText.setText(sharedData.deets.getFirstName());
        LastNameText.setText(sharedData.deets.getLastName());
        PhoneText.setText(sharedData.deets.getPhone());
        EmailText.setText(sharedData.deets.getEmail());
        MakeText.setText(sharedData.deets.getCarMake());
        ColorText.setText(sharedData.deets.getCarColor());
        LicenseText.setText(sharedData.deets.getLicense());
        ExtrasText.setText(sharedData.deets.getExtras());

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasError = false;

                final String email = EmailText.getText().toString();
                if (!isValidEmail(email)) {
                    EmailText.setError("Invalid Email");
                    hasError = true;
                }

                final String firstName = FirstNameText.getText().toString();
                if (!isValidName(firstName)) {
                    FirstNameText.setError("Invalid Name");
                    hasError = true;
                }

                final String lastName = LastNameText.getText().toString();
                if (!isValidName(lastName)) {
                    LastNameText.setError("Invalid Name");
                    hasError = true;
                }

                final String phone = PhoneText.getText().toString();
                if (!isValidPhone(phone)) {
                    PhoneText.setError("Invalid Phone, Please Enter 10 Digit phone number");
                    hasError = true;
                }

                final String License = LicenseText.getText().toString();
                if (!isValidName(License)) {
                    LicenseText.setError("Invalid License");
                    hasError = true;
                }

                final String Make = MakeText.getText().toString();
                final String Color = ColorText.getText().toString();
                if (!isValidName(Make)) {
                    MakeText.setError("Invalid");
                    hasError = true;
                }
                if (!isValidName(Color)) {
                    ColorText.setError("Invalid");
                    hasError = true;
                }

                if (!hasError) {
                    mListener.onDetailsFragmentInteraction();
                    sharedData.deets.setFirstName(FirstNameText.getText().toString());
                    sharedData.deets.setLastName(LastNameText.getText().toString());
                    sharedData.deets.setPhone(PhoneText.getText().toString());
                    sharedData.deets.setEmail(EmailText.getText().toString());
                    sharedData.deets.setCarMake(MakeText.getText().toString());
                    sharedData.deets.setCarColor(ColorText.getText().toString());
                    sharedData.deets.setLicense(LicenseText.getText().toString());
                    sharedData.deets.setExtras(ExtrasText.getText().toString());
                }
                //mListener.onDetailsFragmentInteraction();
            }
        });

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
}
