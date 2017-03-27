package com.example.swat_john.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.swat_john.myfirstapp.models.LoginModel;

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
                LoginModel credential = new LoginModel();
                boolean isEmpty = isValid(username.getText().toString(),password.getText().toString());
                Log.d("RES", String.valueOf(isEmpty));
                if (isEmpty == true) {
                    credential.setUsername(username.getText().toString());
                    credential.setPassword(password.getText().toString());
                    Toast toast = Toast.makeText(getApplicationContext(), "Access Granted.", Toast.LENGTH_SHORT);
                    toast.show();

                    Intent intent = new Intent("com.example.swat_john.myfirstapp.UserActivity");
                    startActivity(intent);
                } else {
                    username.setText("");
                    password.setText("");
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input valid Username/Password", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

    public boolean isValid(String username,String password){
        boolean is_valid;
        Log.d("User",username);
        Log.d("Pass",password);
        if (username.equals("user") && password.equals("pass")) {
            is_valid = true;
        } else {
            is_valid = false;
        }
        return is_valid;
    }
}
