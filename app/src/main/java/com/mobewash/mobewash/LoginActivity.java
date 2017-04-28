package com.mobewash.mobewash;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.login.LoginResult;

/**
 * Controller for:
 * LoginFragment
 * EmailLoginFragment
 * SignupFragment
 */
public class LoginActivity extends AppCompatActivity
        implements LoginFragment.OnLoginFragmentInteractionListener,
        EmailLoginFragment.OnEmailLoginFragmentInteractionListener,
        SignupFragment.OnSignupFragmentInteractionListener,
        FragmentManager.OnBackStackChangedListener {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.framelayout_fragment_container, loginFragment).commit();
    }

    @Override
    public void onBackStackChanged() {
        attemptDisplayHomeButton();
    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        attemptDisplayHomeButton();
        return true;
    }

    @Override
    public void onSignup() {
        // TODO: Handle Signup
    }

    @Override
    public void onEmailLogin() {
        // TODO: Handle Email Login
    }

    @Override
    public void onFacebookLogin(LoginResult loginResult) {
        // TODO: Handle Facebook Login
    }

    @Override
    public void onSignUpButtonClick() {
        SignupFragment signupFragment = new SignupFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_fragment_container, signupFragment)
                .addToBackStack("signup").commit();
    }

    @Override
    public void onLoginButtonClick() {
        EmailLoginFragment emailLoginFragment = new EmailLoginFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_fragment_container, emailLoginFragment)
                .addToBackStack("emaillogin").commit();
    }

    /**
     * Only displays up button if not displaying the main LoginFragment
     */
    private void attemptDisplayHomeButton() {
        boolean canBack = getSupportFragmentManager().getBackStackEntryCount() > 0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canBack);
    }

}
