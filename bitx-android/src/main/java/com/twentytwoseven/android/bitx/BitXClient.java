package com.twentytwoseven.android.bitx;

import android.util.Base64;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.twentytwoseven.android.bitx.model.*;
import com.twentytwoseven.android.bitx.util.LogUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class BitXClient {
    private static final String TAG = BitXClient.class.getSimpleName();
    private static final String URL_BASE = "https://api.mybitx.com/api/1";
    private BitXService mRestService;
    private String mAuth;

    public BitXClient() {
        init();
    }

    public BitXClient(String key, String secretKey) {
        try {
            mAuth = "Basic " + Base64.encodeToString((key + ":" + secretKey).getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            mAuth = "";
            //TODO: handle
            e.printStackTrace();
        }

        init();
    }

    private void init() {

        Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

        OkHttpClient httpClient = new OkHttpClient();
        OkClient okClient = new OkClient(httpClient);

        RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(URL_BASE)
            .setClient(okClient)
            .setConverter(new GsonConverter(gson))
            //FULL log level required to be able to read response body input stream :/
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

        mRestService = restAdapter.create(BitXService.class);
    }

    /* PUBLIC API */

    public void ticker(Callback<Ticker> callback) {
        LogUtil.i(TAG, "API: Ticker");
        mRestService.ticker(callback);
    }

    public void orderBook(Callback<OrderBook> callback) {
        LogUtil.i(TAG, "API: OrderBook");
        mRestService.orderBook(callback);
    }

    public void trades(Callback<TradeList> callback) {
        LogUtil.i(TAG, "API: Trades");
        mRestService.trades(callback);
    }

    /* PRIVATE API */

    public void listOrders(Callback<OrderList> callback) {
        LogUtil.i(TAG, "API: List Orders");
        mRestService.listOrders(mAuth, callback);
    }

    public void postOrder(String pair, String type, String volume, String price, Callback<Order> callback) {
        LogUtil.i(TAG, "API: Posting Order");
        mRestService.postOrder(mAuth, pair, type, volume, price, callback);
    }

    public void stopOrder(String orderId, Callback<Object> callback) {
        LogUtil.i(TAG, "API: Stopping Order");
        mRestService.stopOrder(mAuth, orderId, callback);
    }

    public void balance(String asset, Callback<BalanceList> callback) {
        LogUtil.i(TAG, "API: Balance");
        mRestService.balance(mAuth, asset, callback);
    }

    public void fundingAddress(String asset, Callback<FundingAddress> callback) {
        LogUtil.i(TAG, "API: Funding Address");
        mRestService.fundingAddress(mAuth, asset, callback);
    }

    public void createFundingAddress(String asset, Callback<FundingAddress> callback) {
        LogUtil.i(TAG, "API: Create funding Address for asset %s", asset);
        mRestService.createFundingAddress(mAuth, asset, callback);
    }

    public void transactions(String asset, Callback<TransactionList> callback) {
        LogUtil.i(TAG, "API: Transactions");
        mRestService.transactions(mAuth, asset, "0", "10", callback);
    }

    public void transactions(String asset, String offset, String limit, Callback<TransactionList> callback) {
        LogUtil.i(TAG, "API: Transactions from %s to %s", offset, (offset + limit));
        mRestService.transactions(mAuth, asset, offset, limit, callback);
    }

    public void listWithdrawals(Callback<WithdrawalList> callback) {
        LogUtil.i(TAG, "API: Withdrawals");
        mRestService.listWithdrawals(mAuth, callback);
    }

    public void requestWithdrawal(String type, String amount, Callback<Withdrawal> callback) {
        LogUtil.i(TAG, "API: Request withdrawal of type %s for amount %s", type, amount);
        mRestService.requestWithdrawal(mAuth, type, amount, callback);
    }

    public void getWithdrawal(String id, Callback<Withdrawal> callback) {
        LogUtil.i(TAG, "API: Get withdrawal status");
        mRestService.getWithdrawal(mAuth, id, callback);
    }

    public void cancelWithdrawal(String id, Callback<Withdrawal> callback) {
        LogUtil.i(TAG, "API: Cancel withdrawal with id %s", id);
        mRestService.cancelWithdrawal(mAuth, id, callback);
    }
}
