package com.twentytwoseven.android.bitxandroidsampleapp;

import com.twentytwoseven.android.bitx.model.Order;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class StopOrderFragment extends ApiMethodFragment {

    @Override
    protected void execute() {
        ((MainActivity) getActivity()).getBitXClient().stopOrder(
            "BXMC2CJ7HNB88U4",
            new Callback<Object>() {
            @Override
            public void success(Object result, Response response) {
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
