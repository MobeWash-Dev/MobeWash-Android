package com.mobewash.mobewash;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    //
    // Member Variables
    //
    private OnSignupFragmentInteractionListener mListener;

    //
    // Sign up
    //
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mSignUpButton;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        mEmailEditText = (EditText) view.findViewById(R.id.edittext_signup_email);
        mPasswordEditText = (EditText) view.findViewById(R.id.edittext_signup_password);
        mSignUpButton = (Button) view.findViewById(R.id.button_signup);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                if (loginIsValid(email, password)) {
                    mListener.onSignup(email, password);
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSignupFragmentInteractionListener) {
            mListener = (OnSignupFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEmailLoginFragmentInteractionListener");
        }
    }

    private boolean loginIsValid(String email, String password) {
        boolean emailValid = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
        boolean passwordValid = !TextUtils.isEmpty(password);
        if (!emailValid || !passwordValid) {
            Toast.makeText(getContext(), "Must enter a valid email and password", Toast.LENGTH_LONG).show();
        }
        return emailValid && passwordValid;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSignupFragmentInteractionListener {
        void onSignup(String email, String password);
    }

}
