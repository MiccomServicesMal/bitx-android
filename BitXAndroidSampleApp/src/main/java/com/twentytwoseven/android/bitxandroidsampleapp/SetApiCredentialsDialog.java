package com.twentytwoseven.android.bitxandroidsampleapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class SetApiCredentialsDialog extends DialogFragment {
    public static final String TAG = SetApiCredentialsDialog.class.getSimpleName();

    private View mView;
    private EditText mEditTextKeyId;
    private EditText mEditTextKeySecret;
    private SharedPreferences mPreferences;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.set_api_credentials_dialog, null);
        mEditTextKeyId = (EditText) mView.findViewById(R.id.edit_text_id);
        mEditTextKeySecret = (EditText) mView.findViewById(R.id.edit_text_secret);
        mEditTextKeyId.setText(mPreferences.getString(MainActivity.PREF_API_KEY_ID, ""));
        mEditTextKeySecret.setText(mPreferences.getString(MainActivity.PREF_API_KEY_SECRET, ""));
        builder.setView(mView)
            .setMessage(R.string.action_set_api_credentials)
            .setPositiveButton(R.string.ok, saveCredentials)
            .setNegativeButton(R.string.cancel, revertCredentials);

        return builder.create();
    }

    private DialogInterface.OnClickListener saveCredentials = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            getActivity().getPreferences(Context.MODE_PRIVATE)
                .edit()
                .putString(
                    MainActivity.PREF_API_KEY_ID,
                    mEditTextKeyId.getText().toString())
                .putString(
                    MainActivity.PREF_API_KEY_SECRET,
                    mEditTextKeySecret.getText().toString())
                .apply();
        }
    };

    private DialogInterface.OnClickListener revertCredentials = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            //:/
        }
    };
}

