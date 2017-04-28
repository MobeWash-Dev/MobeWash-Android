package com.mobewash.mobewash;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.login.LoginResult;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnLoginFragmentInteractionListener, FragmentManager.OnBackStackChangedListener {

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
    public void onSignUpButtonClick() {
        SignupFragment signupFragment = new SignupFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_fragment_container, signupFragment).addToBackStack("signup").commit();
    }

    @Override
    public void onLoginButtonClick() {
        EmailLoginFragment emailLoginFragment = new EmailLoginFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_fragment_container, emailLoginFragment).addToBackStack("emaillogin").commit();
    }

    @Override
    public void onFacebookLogin(LoginResult loginResult) {

    }

    @Override
    public void onBackStackChanged() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_fragment_container, loginFragment).commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        return true;
    }
}
