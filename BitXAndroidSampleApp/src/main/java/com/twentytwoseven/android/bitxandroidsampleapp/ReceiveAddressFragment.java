package com.twentytwoseven.android.bitxandroidsampleapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.twentytwoseven.android.bitx.model.Asset;
import com.twentytwoseven.android.bitx.model.FundingAddress;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ReceiveAddressFragment extends ApiMethodFragment {

    private Spinner mSpinnerAsset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        if (rootView != null) {
            View view = inflater.inflate(R.layout.view_receive_address, null);
            mSpinnerAsset = (Spinner)view.findViewById(R.id.spinner_asset);
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_dropdown_item);
            adapter.addAll(Asset.XBT, Asset.ZAR);
            mSpinnerAsset.setAdapter(adapter);
            ((ViewGroup)rootView).addView(view, 0);
        }

        return rootView;
    }

    @Override
    protected void execute() {
        ((MainActivity) getActivity()).getBitXClient().fundingAddress(
            mSpinnerAsset.getSelectedItem().toString(),
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
