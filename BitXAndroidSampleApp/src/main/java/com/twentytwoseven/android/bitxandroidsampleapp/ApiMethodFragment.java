package com.twentytwoseven.android.bitxandroidsampleapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class ApiMethodFragment extends Fragment {

    protected TextView mResultTextView;

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

    protected abstract void execute();

    protected String responseBodyInputStreamToString(Response response) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(response.getBody().in()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        String prettyJson = sb.toString();
        try {
            prettyJson = new JSONObject(sb.toString()).toString(2);
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return prettyJson;
    }

    protected void handleFailure(RetrofitError error) {
        mResultTextView.setText(error.getResponse().getReason());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
