package com.twentytwoseven.android.bitxandroidsampleapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.twentytwoseven.android.bitx.model.Transaction;
import com.twentytwoseven.android.bitx.model.TransactionList;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TransactionsFragment extends ApiMethodFragment {

    private Spinner mSpinnerAsset;
    private EditText mEditTextOffset;
    private EditText mEditTextLimit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        if (rootView != null) {
            View view = inflater.inflate(R.layout.view_transactions, null);
            mSpinnerAsset = (Spinner)view.findViewById(R.id.spinner_asset);
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_dropdown_item);
            adapter.addAll(Transaction.Asset.XBT, Transaction.Asset.ZAR);
            mSpinnerAsset.setAdapter(adapter);
            mEditTextOffset = (EditText)view.findViewById(R.id.edit_text_offset);
            mEditTextOffset.setText("0");
            mEditTextLimit = (EditText)view.findViewById(R.id.edit_text_limit);
            mEditTextLimit.setText("10");
            ((ViewGroup)rootView).addView(view, 0);
        }

        return rootView;
    }

    @Override
    protected void execute() {
        ((MainActivity) getActivity()).getBitXClient().transactions(
            mSpinnerAsset.getSelectedItem().toString(),
            mEditTextOffset.getText().toString(),
            mEditTextLimit.getText().toString(),
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
