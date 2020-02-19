package com.bhsoft.musiconline.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bhsoft.musiconline.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_splash);
        start();
    }
    private void start() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
startActivity(new Intent(SplashActivity.this,HouseActivity.class));
finish();
            }
        },2000);
    }
}
