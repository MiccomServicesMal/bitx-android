package com.twentytwoseven.android.bitxandroidsampleapp;

import com.twentytwoseven.android.bitx.model.Order;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PostOrderFragment extends ApiMethodFragment {

    @Override
    protected void execute() {
        ((MainActivity) getActivity()).getBitXClient().postOrder(
            "XBTZAR",
            "BID",
            "0.001",
            "1000",
            new Callback<Order>() {
            @Override
            public void success(Order order, Response response) {
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
