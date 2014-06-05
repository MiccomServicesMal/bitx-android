package com.twentytwoseven.android.bitx;

import com.twentytwoseven.android.bitx.model.*;
import retrofit.Callback;
import retrofit.http.*;

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

    @FormUrlEncoded
    @POST("/postorder")
    void postOrder(
        @Header("Authorization") String auth,
        @Field("pair") String pair,
        @Field("type") String type,
        @Field("volume") String volume,
        @Field("price") String price,
        Callback<Order> callback);

    @FormUrlEncoded
    @POST("/stoporder")
    void stopOrder(
        @Header("Authorization") String auth,
        @Field("order_id") String orderId,
        Callback<Object> callback);

    @GET("/balance?asset="+mAsset)
    void balance(
        @Header("Authorization") String auth,
        Callback<BalanceList> callback);

    @GET("/funding_address?asset="+mAsset)
    void fundingAddress(
            @Header("Authorization") String auth,
            Callback<FundingAddress> callback);

    @FormUrlEncoded
    @POST("/funding_address")
    void createFundingAddress(
            @Header("Authorization") String auth,
            @Field("asset") String asset,
            Callback<FundingAddress> callback);

    @GET("/transactions?asset="+mAsset)
    void transactions(
            @Header("Authorization") String auth,
            @Query("offset") int offset,
            @Query("limit") int limit,
            Callback<TransactionList> callback);

}
