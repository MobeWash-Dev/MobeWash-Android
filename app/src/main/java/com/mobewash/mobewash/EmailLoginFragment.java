package com.mobewash.mobewash;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobewash.mobewash.dummylogin.DummyLoginServer;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmailLoginFragment extends Fragment {

    private static final String TAG = "EmailLoginFragment";

    //
    // Member Variables
    //
    private OnEmailLoginFragmentInteractionListener mListener;

    private Button mLoginButton;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    public EmailLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email_login, container, false);
        mLoginButton = (Button) view.findViewById(R.id.button_login);
        mEmailEditText = (EditText) view.findViewById(R.id.edittext_login_email);
        mPasswordEditText = (EditText) view.findViewById(R.id.edittext_login_password);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                if (loginIsValid(email, password)) {
                    mListener.onEmailLogin(email, password);
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEmailLoginFragmentInteractionListener) {
            mListener = (OnEmailLoginFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEmailLoginFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private boolean loginIsValid(String email, String password) {
        boolean emailValid = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
        boolean passwordValid = !TextUtils.isEmpty(password);
        if (!emailValid || !passwordValid) {
            Toast.makeText(getContext(), "Must enter a valid email and password", Toast.LENGTH_LONG).show();
        }
        return emailValid && passwordValid;
    }

    public interface OnEmailLoginFragmentInteractionListener {
        void onEmailLogin(String email, String password);
    }

}
