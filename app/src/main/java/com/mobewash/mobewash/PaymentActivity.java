package com.mobewash.mobewash;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;

import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.LineItem;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.PaymentMethodTokenizationType;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import com.google.android.gms.wallet.fragment.SupportWalletFragment;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;

import org.json.JSONException;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.view.CardInputWidget;
import com.stripe.android.model.Token;
import com.stripe.android.net.TokenParser;

import java.security.AccessController;
import java.security.PublicKey;

import static java.security.AccessController.getContext;

public class PaymentActivity extends AppCompatActivity
        implements PaymentFragment.OnPaymentFragmentInteractionListener {

    // Set up tag for logging
    public static final String TAG = "Payment Activity";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

    }

    @Override
    public void onBookPressed(Card card) {
        Intent bookedIntent = new Intent(PaymentActivity.this, BookedActivity.class);
        bookedIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(bookedIntent);
    }
}
