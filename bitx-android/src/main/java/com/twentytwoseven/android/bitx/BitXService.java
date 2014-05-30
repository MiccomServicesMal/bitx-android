package com.twentytwoseven.android.bitx;

import com.twentytwoseven.android.bitx.model.*;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

public interface BitXService {

    String mPair = "XBTZAR";
    String mAsset = "XBT";

    /* PUBLIC API */

    @GET("/ticker?pair="+mPair)
    void ticker(Callback<Ticker> callback);

    @GET("/orderbook?pair="+mPair)
    void orderBook(Callback<OrderBook> callback);

    @GET("/trades?pair="+mPair)
    void trades(Callback<TradeList> callback);

    /* PRIVATE API */

    @GET("/listorders?pair="+mPair)
    void listOrders(
        @Header("Authorization") String auth,
        Callback<OrderList> callback);

    @GET("/funding_address?asset="+mAsset)
    void fundingAddress(
            @Header("Authorization") String auth,
            Callback<FundingAddress> callback);

    @POST("/funding_address?asset="+mAsset)
    void createFundingAddress(
            @Header("Authorization") String auth,
            Callback<FundingAddress> callback);

    @GET("/balance?asset="+mAsset)
    void balance(
            @Header("Authorization") String auth,
            Callback<BalanceList> callback);

    @GET("/transactions?asset="+mAsset)
    void transactions(
            @Header("Authorization") String auth,
            @Query("offset") int offset,
            @Query("limit") int limit,
            Callback<TransactionList> callback);

}
