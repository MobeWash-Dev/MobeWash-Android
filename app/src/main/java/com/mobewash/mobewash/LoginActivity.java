package com.mobewash.mobewash;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginResult;
import com.mobewash.mobewash.dummylogin.DummyLoginServer;

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

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar_login);

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
        AccessToken token = loginResult.getAccessToken();

        // Attempt serverside token login
        DummyLoginServer.FBServerLogin fbServerLogin = new DummyLoginServer.FBServerLogin(token,
                new DummyLoginServer.OnServerCompleteListener() {
            @Override
            public void onComplete(Exception err, boolean result) {
                if (err != null) {
                    Log.e(TAG, "FB Server Login Error", err);
                    LoginFragment loginFragment = new LoginFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framelayout_fragment_container, loginFragment).commit();
                } else if (!result) {
                    Log.d(TAG, "FB Server Login returned false result");
                    LoginFragment loginFragment = new LoginFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framelayout_fragment_container, loginFragment).commit();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setVisibility(View.GONE);
                            LoginFragment loginFragment = new LoginFragment();
                            Intent loggedInIntent = new Intent(LoginActivity.this, LoggedInActivity.class);
                            startActivity(loggedInIntent);
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.framelayout_fragment_container, loginFragment).commit();
                        }
                    });
                }
            }
        });
        mProgressBar.setVisibility(View.VISIBLE);
        BlankFragment blankFragment = new BlankFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_fragment_container, blankFragment).commit();
        new Thread(fbServerLogin).start();
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
