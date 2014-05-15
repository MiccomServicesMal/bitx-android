package com.twentytwoseven.android.bitx;

import android.util.Base64;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twentytwoseven.android.bitx.model.BalanceList;
import com.twentytwoseven.android.bitx.model.FundingAddress;
import com.twentytwoseven.android.bitx.model.Ticker;
import com.twentytwoseven.android.bitx.util.LogUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import java.io.UnsupportedEncodingException;

public class BitXClient {
    private static final String TAG = BitXClient.class.getSimpleName();
    private static final String URL_BASE = "https://bitx.co.za/api/1";
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

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(URL_BASE)
                .setConverter(new GsonConverter(gson))
                .build();

        mRestService = restAdapter.create(BitXService.class);
    }

    public void ticker(Callback<Ticker> callback) {
        LogUtil.i(TAG, "API: Ticker");
        mRestService.ticker(callback);
    }

    public void balance(Callback<BalanceList> callback) {
        LogUtil.i(TAG, "API: Balance");
        mRestService.balance(mAuth, callback);
    }

    public void fundingAddress(Callback<FundingAddress> callback) {
        LogUtil.i(TAG, "API: Funding Address");
        mRestService.fundingAddress(mAuth, callback);
    }
}
