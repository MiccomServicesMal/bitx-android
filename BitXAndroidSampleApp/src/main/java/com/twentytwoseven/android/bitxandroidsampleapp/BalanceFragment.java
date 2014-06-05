package com.twentytwoseven.android.bitxandroidsampleapp;

import com.twentytwoseven.android.bitx.model.BalanceList;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class BalanceFragment extends ApiMethodFragment {

    @Override
    protected void execute() {
        ((MainActivity) getActivity()).getBitXClient().balance(new Callback<BalanceList>() {
            @Override
            public void success(BalanceList balanceList, Response response) {
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
