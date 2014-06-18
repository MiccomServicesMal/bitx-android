package com.twentytwoseven.android.bitxandroidsampleapp;

import com.twentytwoseven.android.bitx.model.TradeList;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RecentTradesFragment extends ApiMethodFragment {

    @Override
    protected void execute() {
        ((MainActivity) getActivity()).getBitXClient().recentTrades(new Callback<TradeList>() {
            @Override
            public void success(TradeList tradeList, Response response) {
                String prettyJson = responseBodyInputStreamToString(response);
                mResultTextView.setText(prettyJson);
            }

            @Override
            public void failure(RetrofitError error) {
                handleFailure(error);
            }
        });
    }
}
