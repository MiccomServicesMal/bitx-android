package com.twentytwoseven.android.bitxandroidsampleapp;

import com.twentytwoseven.android.bitx.model.TransactionList;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TransactionsFragment extends ApiMethodFragment {

    @Override
    protected void execute() {
        ((MainActivity) getActivity()).getBitXClient().transactions(
            new Callback<TransactionList>() {
                @Override
                public void success(TransactionList transactionList, Response response) {
                    String prettyJson = responseBodyInputStreamToString(response);
                    mResultTextView.setText(prettyJson);
                }

                @Override
                public void failure(RetrofitError error) {
                    handleFailure(error);
                }
            }
        );
    }
}
