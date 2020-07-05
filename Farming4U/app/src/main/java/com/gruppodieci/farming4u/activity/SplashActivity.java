package com.gruppodieci.farming4u.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.launchLoginActivity();
    }

    private void launchLoginActivity(){
        this.loginActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(this.loginActivityIntent);
        finish();
    }

    private Intent loginActivityIntent;
}

