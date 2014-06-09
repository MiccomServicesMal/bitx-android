package com.twentytwoseven.android.bitxandroidsampleapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.twentytwoseven.android.bitx.model.FundingAddress;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CreateReceiveAddressFragment extends ApiMethodFragment {

    private EditText mEditTextAsset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        if (rootView != null) {
            View view = inflater.inflate(R.layout.view_create_receive_address, null);
            mEditTextAsset = (EditText)view.findViewById(R.id.edit_text_asset);
            mEditTextAsset.setText("XBT");
            ((ViewGroup)rootView).addView(view, 0);
        }

        return rootView;
    }

    @Override
    protected void execute() {
        ((MainActivity) getActivity()).getBitXClient().createFundingAddress(
            mEditTextAsset.getText().toString(),
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
