package com.gruppodieci.farming4u.business;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.gruppodieci.farming4u.R;

public class GroundStatusBusiness {
    public GroundStatusBusiness(){

    }

    public void setSensorImage(ImageView sensorImage, String sensorDrawableName){
        sensorImage.setImageResource(R.drawable.beacon_ble);
    }

    public void setSwitchSensorListener(ImageButton leftSwitcher, ImageButton rightSwitcher){
        leftSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rightSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void setButtonHeader(TextView firstHeader, TextView secondHeader, TextView thirdHeader){

    }

    public void setInformationButtonsListeners(MaterialButton firstButton, MaterialButton secondButton, MaterialButton thirdButton){
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
