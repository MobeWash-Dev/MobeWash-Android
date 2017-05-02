package com.mobewash.mobewash.dummylogin;

import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.Profile;

/**
 * Created by sidney on 4/28/17.
 */

public class DummyLoginServer {
    private static final String TAG = "DummyLoginServer";

    public static class FBServerLogin implements Runnable {

        private OnServerCompleteListener listener;
        private AccessToken token;

        public FBServerLogin(AccessToken token, OnServerCompleteListener listener) {
            this.listener = listener;
            this.token = token;
        }

        @Override
        public void run() {
            // Simulate network
            try {
                Thread.sleep(1000);
                listener.onComplete(null, true);
                Log.d(TAG, "Facebook login with token: " + this.token.getToken());
            } catch (InterruptedException exception) {
                listener.onComplete(exception, false);
            }
        }

    }

    public static class EmailServerLogin implements Runnable {

        private static final String EMAIL = "bob@gmail.com";
        private static final String PASS = "asdf";

        private OnServerCompleteListener listener;
        private String email;
        private String password;

        public EmailServerLogin(String email, String password, OnServerCompleteListener listener) {
            this.listener = listener;
            this.email = email;
            this.password = password;
        }

        @Override
        public void run() {
            // Simulate network
            try {
                Thread.sleep(1000);
                if (this.email.equals(EMAIL) && this.password.equals(PASS)) {
                    listener.onComplete(null, true);
                } else {
                    listener.onComplete(null, false);
                }
            } catch (InterruptedException exception) {
                listener.onComplete(exception, false);
            }
        }
    }

    public static class SignUpServerLogin implements Runnable {
        private OnServerCompleteListener listener;
        private String email;
        private String password;

        public SignUpServerLogin(String email, String password, OnServerCompleteListener listener) {
            this.listener = listener;
            this.email = email;
            this.password = password;
        }

        @Override
        public void run() {
            // Simulate network
            try {
                Thread.sleep(1000);
                listener.onComplete(null, true);
            } catch (InterruptedException exception) {
                listener.onComplete(exception, false);
            }
        }
    }

    public interface OnServerCompleteListener {
        void onComplete(Exception err, boolean result);
    }
}
