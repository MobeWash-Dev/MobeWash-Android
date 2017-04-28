package com.mobewash.mobewash;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    //
    // Facebook
    //
    private CallbackManager mCallbackManager;
    private LoginButton mFBLoginButton;

    //
    // Email
    //
    private Button mLoginButton;
    private Button mSignUpButton;

    //
    // Member Variables
    //
    private OnLoginFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCallbackManager = CallbackManager.Factory.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Setup FB login button
        mFBLoginButton = (LoginButton) view.findViewById(R.id.fb_login_button);
        mFBLoginButton.setReadPermissions("email");
        mFBLoginButton.setFragment(LoginFragment.this);
        mFBLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mListener.onFacebookLogin(loginResult);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        //Setup email login/signup buttons
        mLoginButton = (Button) view.findViewById(R.id.button_login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onLoginButtonClick();
            }
        });
        mSignUpButton = (Button) view.findViewById(R.id.button_signup);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSignUpButtonClick();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginFragmentInteractionListener) {
            mListener = (OnLoginFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnLoginFragmentInteractionListener {
        void onSignUpButtonClick();
        void onLoginButtonClick();
        void onFacebookLogin(LoginResult loginResult);
    }
}
