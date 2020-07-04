package com.gruppodieci.farming4u.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gruppodieci.farming4u.MainActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.launchBasicActivity();
    }

    private void launchBasicActivity(){
        this.basicActivityIntent = new Intent(this, MainActivity.class);
        startActivity(this.basicActivityIntent);
        finish();
    }

    private Intent basicActivityIntent;
}
