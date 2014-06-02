package com.twentytwoseven.android.bitx;

import android.util.Base64;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twentytwoseven.android.bitx.model.*;
import com.twentytwoseven.android.bitx.util.LogUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.converter.GsonConverter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

public class BitXClient {
    private static final String TAG = BitXClient.class.getSimpleName();
    private static final String URL_BASE = "https://api.mybitx.com/api/1";
    private final BitXService mRestService;
    private String mAuth;

    public BitXClient(String key, String secretKey) {
        try {
            mAuth = "Basic " + Base64.encodeToString((key + ":" + secretKey).getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            mAuth = "";
            //TODO: handle
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        OkClient okClient = new OkClient() {

            @Override
            protected HttpURLConnection openConnection(Request request) throws IOException {
                HttpURLConnection connection = super.openConnection(request);
                connection.setReadTimeout(120000);
                connection.setConnectTimeout(120000);
                return connection;
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(URL_BASE)
                .setClient(okClient)
                .setConverter(new GsonConverter(gson))
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

    public void stopOrder(String orderId, Callback<Order> callback) {
        LogUtil.i(TAG, "API: Stopping Order");
        mRestService.stopOrder(mAuth, orderId, callback);
    }

    public void balance(Callback<BalanceList> callback) {
        LogUtil.i(TAG, "API: Balance");
        mRestService.balance(mAuth, callback);
    }

    public void fundingAddress(Callback<FundingAddress> callback) {
        LogUtil.i(TAG, "API: Funding Address");
        mRestService.fundingAddress(mAuth, callback);
    }

    public void createFundingAddress(Callback<FundingAddress> callback) {
        LogUtil.i(TAG, "API: Create funding Address");
        mRestService.createFundingAddress(mAuth, callback);
    }

    public void transactions(Callback<TransactionList> callback) {
        LogUtil.i(TAG, "API: Transactions");
        mRestService.transactions(mAuth, 0, 10, callback);
    }

    public void transactions(int offset, int limit, Callback<TransactionList> callback) {
        LogUtil.i(TAG, "API: Transactions from %s to %s", offset, (offset + limit));
        mRestService.transactions(mAuth, offset, limit, callback);
    }
}
