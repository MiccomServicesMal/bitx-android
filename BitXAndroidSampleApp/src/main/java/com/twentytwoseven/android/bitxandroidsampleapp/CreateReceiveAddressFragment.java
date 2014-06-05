package com.twentytwoseven.android.bitxandroidsampleapp;

import com.twentytwoseven.android.bitx.model.FundingAddress;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CreateReceiveAddressFragment extends ApiMethodFragment {

    @Override
    protected void execute() {
        ((MainActivity) getActivity()).getBitXClient().createFundingAddress(
            "XBT",
            new Callback<FundingAddress>() {
            @Override
            public void success(FundingAddress fundingAddress, Response response) {
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
