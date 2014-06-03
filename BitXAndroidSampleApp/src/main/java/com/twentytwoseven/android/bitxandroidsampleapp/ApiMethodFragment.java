package com.twentytwoseven.android.bitxandroidsampleapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.twentytwoseven.android.bitx.model.Ticker;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ApiMethodFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_METHOD_NAME = "method_name";

    private String mMethodName;

    private TextView mResultTextView;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ApiMethodFragment newInstance(String methodName) {
        ApiMethodFragment fragment = new ApiMethodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_METHOD_NAME, methodName);
        fragment.setArguments(args);
        return fragment;
    }

    public ApiMethodFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_api_method, container, false);
        Button executeButton = (Button) rootView.findViewById(R.id.execute_button);
        executeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execute();
            }
        });
        mResultTextView = (TextView)rootView.findViewById(R.id.result_text_view);

        return rootView;
    }

    protected void execute() {
        ((MainActivity) getActivity()).getBitXClient().ticker(new Callback<Ticker>() {
            @Override
            public void success(Ticker ticker, Response response) {
                try {
                    mResultTextView.setText(ticker.toString());
                } catch (Exception e) {
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //shite
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMethodName = getArguments().getString(ARG_METHOD_NAME);
        ((MainActivity) activity).onSectionAttached(mMethodName);
    }
}
