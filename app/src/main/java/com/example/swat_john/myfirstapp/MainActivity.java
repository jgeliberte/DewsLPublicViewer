package com.example.swat_john.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.swat_john.myfirstapp.models.LoginModel;
import com.loopj.android.http.*;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONArray;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(this);

    }

    public void onClick(View view)
    {
        final EditText username = (EditText) findViewById(R.id.txt_username);
        final EditText password = (EditText) findViewById(R.id.txt_password);
        Button button = (Button) view;
        switch (button.getId())
        {
            case R.id.btn_login:
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put("username", username);
                params.put("password", password);

                client.post("http://www.dewslandslide.com/lin/mobile_login/", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = new String(responseBody);
                        Log.d("RAW", String.valueOf(responseBody));
                        Log.d("RESPONSE",response);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        String err = new String(responseBody);
                        Log.d("ERROR", err);
                        Log.d("EXCE", String.valueOf(error));
                    }
                });


//                LoginModel credential = new LoginModel();
//                boolean isEmpty = isValid(username.getText().toString(),password.getText().toString());
//                if (isEmpty == true) {
//                    credential.setUsername(username.getText().toString());
//                    credential.setPassword(password.getText().toString());
//                    Toast toast = Toast.makeText(getApplicationContext(), "Access Granted.", Toast.LENGTH_SHORT);
//                    toast.show();
//
//                    Intent intent = new Intent("com.example.swat_john.myfirstapp.UserActivity");
//                    startActivity(intent);
//                } else {
//                    username.setText("");
//                    password.setText("");
//                    Toast toast = Toast.makeText(getApplicationContext(), "Please input valid Username/Password", Toast.LENGTH_SHORT);
//                    toast.show();
//                }
                break;
        }
    }

    public boolean isValid(String username,String password){
        boolean is_valid;
        if (username.equals("user") && password.equals("pass")) {
            is_valid = true;
        } else {
            is_valid = false;
        }
        return is_valid;
    }
}
