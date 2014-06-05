package com.twentytwoseven.android.bitxandroidsampleapp;

import com.twentytwoseven.android.bitx.model.OrderBook;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OrderBookFragment extends ApiMethodFragment {

    @Override
    protected void execute() {
        ((MainActivity) getActivity()).getBitXClient().orderBook(new Callback<OrderBook>() {
            @Override
            public void success(OrderBook orderBook, Response response) {
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
