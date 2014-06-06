package com.twentytwoseven.android.bitxandroidsampleapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.twentytwoseven.android.bitx.model.Order;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PostOrderFragment extends ApiMethodFragment {

    private EditText mEditTextPair;
    private Spinner mSpinnerType;
    private EditText mEditTextVolume;
    private EditText mEditTextPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        if (rootView != null) {
            View view = inflater.inflate(R.layout.view_post_order, null);
            mEditTextPair = (EditText)view.findViewById(R.id.edit_text_pair);
            mEditTextPair.setText("XBTZAR");
            mSpinnerType = (Spinner)view.findViewById(R.id.spinner_type);
            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_dropdown_item);
            adapter.addAll(Order.Type.ASK, Order.Type.BID);
            mSpinnerType.setAdapter(adapter);
            mEditTextVolume = (EditText)view.findViewById(R.id.edit_text_volume);
            mEditTextPrice = (EditText)view.findViewById(R.id.edit_text_price);
            ((ViewGroup)rootView).addView(view, 0);
        }

        return rootView;
    }

    @Override
    protected void execute() {
        String pair = mEditTextPair.getText().toString();
        String type = (String)mSpinnerType.getSelectedItem();
        String volume = mEditTextVolume.getText().toString();
        String price = mEditTextPrice.getText().toString();
        if (!(TextUtils.isEmpty(pair) || TextUtils.isEmpty(type) || TextUtils.isEmpty(volume) || TextUtils.isEmpty(price))) {
            ((MainActivity) getActivity()).getBitXClient().postOrder(
                pair,
                type,
                volume,
                price,
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
                }
            );
        }
    }
}
