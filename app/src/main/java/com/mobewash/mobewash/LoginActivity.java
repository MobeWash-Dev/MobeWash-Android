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
import com.facebook.login.LoginManager;
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

        // Check if user is already logged in with Facebook
        AccessToken token = AccessToken.getCurrentAccessToken();
        if (token != null) {
            Intent loggedInIntent = new Intent(LoginActivity.this, LoggedInActivity.class);
            startActivity(loggedInIntent);
            finish();
        }

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
    public void onSignup(String email, String password) {
        // Attempt serverside email sign up

        //
        // This is an imitation of a call to the back end for email sign up.
        // I expect to make a POST request to the back end, sending an email and password.
        // I expect the server to return a JSON containing user info, errors, and a result.
        // If the sign up was successful, redirect to a main activity.
        // Otherwise, stay on the email login page.
        //
        DummyLoginServer.SignUpServerLogin signUpServer = new DummyLoginServer.SignUpServerLogin(
                email, password, new DummyLoginServer.OnServerCompleteListener() {
            @Override
            public void onComplete(Exception err, boolean result) {
                // Network call is complete

                // Hide progress bar
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
                if (err != null) {
                    Log.e(TAG, "Email sign up error", err);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Sorry. An error occurred during sign up. Please try again", Toast.LENGTH_LONG).show();
                        }
                    });
                } else if (!result) {
                    Log.d(TAG, "Email sign up returned false result");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Invalid email or password.", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent loggedInIntent = new Intent(LoginActivity.this, LoggedInActivity.class);
                            startActivity(loggedInIntent);
                            finish();
                        }
                    });
                }
            }
        });

        // Prepare UI for a network call
        mProgressBar.setVisibility(View.VISIBLE);

        // Start the network call
        new Thread(signUpServer).start();
    }

    @Override
    public void onEmailLogin(String email, String password) {

        // Attempt serverside email login

        //
        // This is an imitation of a call to the back end for email login.
        // I expect to make a POST request to the back end, sending an email and password.
        // I expect the server to return a JSON containing user info, errors, and a result.
        // If the login was successful, redirect to a main activity.
        // Otherwise, stay on the email login page.
        //
        DummyLoginServer.EmailServerLogin loginServer = new DummyLoginServer.EmailServerLogin(
                email, password, new DummyLoginServer.OnServerCompleteListener() {
            @Override
            public void onComplete(Exception err, boolean result) {

                // Network call is complete

                // Hide progress bar
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
                if (err != null) {
                    Log.e(TAG, "Email login error", err);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Sorry. An error occurred during login. Please try again", Toast.LENGTH_LONG).show();
                        }
                    });
                } else if (!result) {
                    Log.d(TAG, "Email login returned false result");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Incorrect email or password.", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent loggedInIntent = new Intent(LoginActivity.this, LoggedInActivity.class);
                            startActivity(loggedInIntent);
                            finish();
                        }
                    });
                }
            }
        });

        // Prepare UI for a network call
        mProgressBar.setVisibility(View.VISIBLE);

        // Start the network call
        new Thread(loginServer).start();
    }

    @Override
    public void onFacebookLogin(LoginResult loginResult) {
        AccessToken token = loginResult.getAccessToken();

        // Attempt serverside token login

        //
        // This is an imitation of a call to the back end for Facebook token authentication.
        // I expect to make a POST request to the back end, sending the AccessToken and uid.
        // I expect the server to return a JSON containing user info, errors, and a result.
        // If the login was successful, redirect to a main activity.
        // Otherwise, go back to the login page and log out of Facebook locally.
        //
        DummyLoginServer.FBServerLogin fbServerLogin = new DummyLoginServer.FBServerLogin(token,
                new DummyLoginServer.OnServerCompleteListener() {
            @Override
            public void onComplete(Exception err, boolean result) {

                // Network call is complete

                // Hide progress bar
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
                if (err != null) {
                    Log.e(TAG, "FB Server Login Error", err);
                    LoginManager.getInstance().logOut();
                    LoginFragment loginFragment = new LoginFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framelayout_fragment_container, loginFragment).commit();

                    // Indicate to the user that there was an error when logging in
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Sorry. An error occurred during login. Please try again", Toast.LENGTH_LONG).show();
                        }
                    });
                } else if (!result) {
                    Log.d(TAG, "FB Server Login returned false result");
                    LoginManager.getInstance().logOut();
                    LoginFragment loginFragment = new LoginFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.framelayout_fragment_container, loginFragment).commit();

                    // Indicate to the user that the login was not successful
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Sorry. The login was unsuccessful. Please try again.", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent loggedInIntent = new Intent(LoginActivity.this, LoggedInActivity.class);
                            startActivity(loggedInIntent);
                            finish();
                        }
                    });
                }
            }
        });

        // Prepare UI for a network call
        mProgressBar.setVisibility(View.VISIBLE);
        BlankFragment blankFragment = new BlankFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_fragment_container, blankFragment).commit();

        // Start the network call
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
