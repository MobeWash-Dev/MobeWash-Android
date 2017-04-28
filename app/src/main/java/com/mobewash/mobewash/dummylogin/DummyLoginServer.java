package com.mobewash.mobewash.dummylogin;

import android.os.AsyncTask;

import com.facebook.AccessToken;

/**
 * Created by sidney on 4/28/17.
 */

public class DummyLoginServer {
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
                Thread.sleep(2000);
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
