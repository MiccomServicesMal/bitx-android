package com.twentytwoseven.android.bitx;

import com.twentytwoseven.android.bitx.model.BalanceList;
import com.twentytwoseven.android.bitx.model.FundingAddress;
import com.twentytwoseven.android.bitx.model.Ticker;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;

public interface BitXService {

    String mPair = "XBTZAR";
    String mAsset = "XBT";

    @GET("/balance?asset="+mAsset)
    void balance(@Header("Authorization") String authorization, Callback<BalanceList> callback);

    @GET("/funding_address?asset="+mAsset)
    void fundingAddress(@Header("Authorization") String authorization, Callback<FundingAddress> callback);

    @GET("/ticker?pair="+mPair)
    void ticker(Callback<Ticker> callback);
}
