package com.gruppodieci.farming4u.business;

import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.gruppodieci.farming4u.activity.BasicActivity;

public class SettingsAddIrrigatorBusiness {

    public SettingsAddIrrigatorBusiness(){

    }

    public void setOnSaveButtonClick(MaterialButton saveButton){
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BasicActivity.getBasicActivity().onBackPressed();
            }
        });
    }

    public void setOnDismissButtonClick(MaterialButton dismissButton, final EditText sensorName, final EditText sensorType,
                                        final EditText sensorMaxRadius, final EditText sensorMinRadius){

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorName.setText(null);
                sensorType.setText(null);
                sensorMaxRadius.setText(null);
                sensorMinRadius.setText(null);

                sensorName.setEnabled(false);
                sensorType.setEnabled(false);
                sensorMaxRadius.setEnabled(false);
                sensorMinRadius.setEnabled(false);

                sensorName.setEnabled(true);
                sensorType.setEnabled(true);
                sensorMaxRadius.setEnabled(true);
                sensorMinRadius.setEnabled(true);
            }
        });
    }
}
