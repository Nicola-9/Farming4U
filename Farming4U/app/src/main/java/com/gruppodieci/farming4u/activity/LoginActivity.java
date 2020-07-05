package com.gruppodieci.farming4u.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.gruppodieci.farming4u.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        this.loginUsernameLayout = findViewById(R.id.loginUsernameBox);
        this.loginUsername = findViewById(R.id.loginUsername);
        this.loginPasswordLayout = findViewById(R.id.loginPasswordBox);
        this.loginPassword = findViewById(R.id.loginPassword);
        this.loginButton = findViewById(R.id.loginButton);

        this.onTextChange();
        this.setOnLoginButtonClick();
    }

    private void onTextChange(){
        this.loginUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(loginUsernameLayout.isErrorEnabled()){
                    loginUsernameLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        this.loginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(loginPasswordLayout.isErrorEnabled()){
                    loginPasswordLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setOnLoginButtonClick(){
        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyUsernameAndPassword();
            }
        });
    }

    private void verifyUsernameAndPassword(){
        if(loginUsername.getText() != null && loginPassword.getText() != null){
            if(this.loginUsername.getText().toString().trim().equals("gruppo10")){
                if(this.loginPassword.getText().toString().trim().equals("gruppo10")){

                    this.launchBasicActivityIntent = new Intent(this, BasicActivity.class);
                    startActivity(this.launchBasicActivityIntent);
                    finish();

                } else {
                    this.loginPasswordLayout.setErrorEnabled(true);
                    this.loginPasswordLayout.setError("Password errata");
                }
            } else {
                this.loginUsernameLayout.setErrorEnabled(true);
                this.loginUsernameLayout.setError("Username errato");
            }
        }
    }

    private TextInputLayout loginUsernameLayout;
    private TextInputEditText loginUsername;
    private TextInputLayout loginPasswordLayout;
    private TextInputEditText loginPassword;
    private MaterialButton loginButton;
    private Intent launchBasicActivityIntent;
}
