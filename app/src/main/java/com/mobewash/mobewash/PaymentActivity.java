package com.mobewash.mobewash;

import android.app.Activity;
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

import static java.security.AccessController.getContext;

public class PaymentActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    // Set up tag for logging
    public static final String TAG = "Payment Activity";

    // You will need to use your live API key even while testing
    public static final String PUBLISHABLE_KEY = "pk_live_XXX";

    // Unique identifiers for asynchronous requests:
    private static final int LOAD_MASKED_WALLET_REQUEST_CODE = 1000;
    private static final int LOAD_FULL_WALLET_REQUEST_CODE = 1001;

    //keep track of your current environment,
    //change to WalletConstants.ENVIRONMENT_PRODUCTION when you're ready to go live
    public static final int mEnvironment = WalletConstants.ENVIRONMENT_TEST;

    private SupportWalletFragment walletFragment;

    private GoogleApiClient mgoogleApiClient;

    // Idk how this works!!!!
    private IsReadyToPayRequest readyToPayRequest;

    CardInputWidget mCardInputWidget;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      /*   mCardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget);

        readyToPayRequest = IsReadyToPayRequest.newBuilder()
                .addAllowedCardNetwork(WalletConstants.CardNetwork.MASTERCARD)
                .addAllowedCardNetwork(WalletConstants.CardNetwork.VISA)
                .addAllowedCardNetwork(WalletConstants.CardNetwork.AMEX)
                .addAllowedCardNetwork(WalletConstants.CardNetwork.DISCOVER)
                .build();

        mgoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Wallet.API, new Wallet.WalletOptions.Builder()
                        .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
                        .setTheme(WalletConstants.THEME_LIGHT)
                        .build())
                .build();

        Wallet.Payments.isReadyToPay(mgoogleApiClient,readyToPayRequest).setResultCallback(
                new ResultCallback<BooleanResult>() {
                    @Override
                    public void onResult(@NonNull BooleanResult booleanResult) {
                        if (booleanResult.getStatus().isSuccess()) {
                            if (booleanResult.getValue()) {
                                showAndroidPay();
                            } else {
                                // Hide Android Pay buttons, show a message that Android Pay
                                // cannot be used yet, and display a traditional checkout button
                            }
                        } else {
                            // Error making isReadyToPay call
                            Log.e(TAG, "isReadyToPay:" + booleanResult.getStatus());
                        }
                    }
                }
        );*/

    }
    /*
    public void showAndroidPay() {
        setContentView(R.layout.activity_payment);

        walletFragment =
                (SupportWalletFragment) getSupportFragmentManager().findFragmentById(R.id.wallet_fragment);

        MaskedWalletRequest maskedWalletRequest = MaskedWalletRequest.newBuilder()

                // Request credit card tokenization with Stripe by specifying tokenization parameters:
                .setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters.newBuilder()
                        .setPaymentMethodTokenizationType(PaymentMethodTokenizationType.PAYMENT_GATEWAY)
                        .addParameter("gateway", "stripe")
                        .addParameter("stripe:publishableKey", PUBLISHABLE_KEY)
                        .addParameter("stripe:version", com.stripe.android.BuildConfig.VERSION_NAME)
                        .build())

                // You want the shipping address:
                .setShippingAddressRequired(true)

                // Price set as a decimal:
                .setEstimatedTotalPrice("20.00")
                .setCurrencyCode("USD")
                .build();

        // Set the parameters:
        WalletFragmentInitParams initParams = WalletFragmentInitParams.newBuilder()
                .setMaskedWalletRequest(maskedWalletRequest)
                .setMaskedWalletRequestCode(LOAD_MASKED_WALLET_REQUEST_CODE)
                .build();

        // Initialize the fragment:
        walletFragment.initialize(initParams);


    }*/

    public void onStart() {
        super.onStart();
        mgoogleApiClient.connect();
    }

    public void onStop() {
        super.onStop();
        mgoogleApiClient.disconnect();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*if (requestCode == LOAD_MASKED_WALLET_REQUEST_CODE) { // Unique, identifying constant
            if (resultCode == Activity.RESULT_OK) {
                MaskedWallet maskedWallet = data.getParcelableExtra(WalletConstants.EXTRA_MASKED_WALLET);
                FullWalletRequest fullWalletRequest = FullWalletRequest.newBuilder()
                        .setCart(Cart.newBuilder()
                                .setCurrencyCode("USD")
                                .setTotalPrice("20.00")
                                .addLineItem(LineItem.newBuilder() // Identify item being purchased
                                        .setCurrencyCode("USD")
                                        .setQuantity("1")
                                        .setDescription("Premium Llama Food")
                                        .setTotalPrice("20.00")
                                        .setUnitPrice("20.00")
                                        .build())
                                .build())
                        .setGoogleTransactionId(maskedWallet.getGoogleTransactionId())
                        .build();
                Wallet.Payments.loadFullWallet(mgoogleApiClient, fullWalletRequest, LOAD_FULL_WALLET_REQUEST_CODE);
            }
        } else if (requestCode == LOAD_FULL_WALLET_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                FullWallet fullWallet = data.getParcelableExtra(WalletConstants.EXTRA_FULL_WALLET);
                String tokenJSON = fullWallet.getPaymentMethodToken().getToken();

                //A token will only be returned in production mode,
                //i.e. WalletConstants.ENVIRONMENT_PRODUCTION
                if (mEnvironment == WalletConstants.ENVIRONMENT_PRODUCTION) {
                    try {
                        Token token = TokenParser.parseToken(tokenJSON);
                        // TODO: send token to your server
                    } catch (JSONException jsonException) {
                        // Log the error and notify Stripe helpß
                    }
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }*/
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {}

    @Override
    public void onConnected(Bundle bundle) {}

    @Override
    public void onConnectionSuspended(int i) {}

}
