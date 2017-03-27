package com.example.swat_john.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.example.swat_john.myfirstapp.service.PublicAlertService;

import layout.FragmentOne;
import layout.FragmentTwo;

public class UserActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        final Button f1 = (Button) findViewById(R.id.f1);
        f1.setOnClickListener(this);

        final Button f2 = (Button) findViewById(R.id.f2);
        f2.setOnClickListener(this);

    }

    public void onClick(View view) {
        Button button = (Button) view;
        Fragment fragment;
        switch (button.getId()) {
            case R.id.f1:
                fragment = new FragmentOne();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fplace, fragment);
                ft.commit();
                break;
            case R.id.f2:
                fragment = new FragmentTwo();
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.fplace, fragment);
                ft.commit();
                break;
        }
    }
}
