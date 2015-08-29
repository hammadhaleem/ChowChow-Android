package com.chowchow.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private final static String TAG = "LoginActivity";

    @Bind(R.id.login_name_input)
    EditText loginNameField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_button)
    void onLoginButtonPressed() {
        Log.d(TAG, "onLoginButonPressed()");
        String userName = loginNameField.getText().toString().trim();

        //TODO: Store user name in manager class which interacts with server

        proceedToNextActivity();
    }

    private void proceedToNextActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
