package com.twentytwoseven.android.bitxandroidsampleapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class StopOrderFragment extends ApiMethodFragment {

    private EditText mEditTextOrderId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        if (rootView != null) {
            View view = inflater.inflate(R.layout.view_stop_order, null);
            mEditTextOrderId = (EditText)view.findViewById(R.id.edit_text_order_id);
            mEditTextOrderId.setText("BXMC2CJ7HNB88U4");
            ((ViewGroup)rootView).addView(view, 0);
        }

        return rootView;
    }

    @Override
    protected void execute() {
        String orderId = mEditTextOrderId.getText().toString();
        if(!TextUtils.isEmpty(orderId)) {
            ((MainActivity) getActivity()).getBitXClient().stopOrder(
                orderId,
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
                }
            );
        }
    }
}
