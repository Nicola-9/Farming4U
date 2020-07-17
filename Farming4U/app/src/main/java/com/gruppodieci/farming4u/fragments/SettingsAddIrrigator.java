package com.gruppodieci.farming4u.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.business.SettingsAddIrrigatorBusiness;

public class SettingsAddIrrigator extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate Fragment layout
        this.settingsAddIrrigator = inflater.inflate(R.layout.new_irrigator_settings, container, false);

        // Find all components
        this.saveButton = this.settingsAddIrrigator.findViewById(R.id.irrigatorSettingAddButtonSave);
        this.dismissButton = this.settingsAddIrrigator.findViewById(R.id.irrigatorSettingAddButtonDismiss);
        this.sensorName = this.settingsAddIrrigator.findViewById(R.id.sensorNameSettings);
        this.sensorType = this.settingsAddIrrigator.findViewById(R.id.sensorTypeSettings);
        this.sensorMaxRadius = this.settingsAddIrrigator.findViewById(R.id.sensorMaxRadiusSettings);
        this.sensorMinRadius = this.settingsAddIrrigator.findViewById(R.id.sensorMinRadiusSettings);
        this.switchLeft = this.settingsAddIrrigator.findViewById(R.id.switchSensorsSettingsLeft);
        this.switchRight = this.settingsAddIrrigator.findViewById(R.id.switchSensorsSettingsRight);

        // Initialize business variable
        this.settingsBusiness = new SettingsAddIrrigatorBusiness();

        MaterialToolbar toolbar = BasicActivity.getToolbar();

        toolbar.setVisibility(View.VISIBLE);

        ((BasicActivity) getActivity()).showToolbarMenu(false);

        toolbar.setTitle("Aggiunta irrigatore");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        this.settingsBusiness.setOnSaveButtonClick(this.saveButton);
        this.settingsBusiness.setOnDismissButtonClick(this.dismissButton, this.sensorName, this.sensorType,
                this.sensorMaxRadius, this.sensorMinRadius);

        this.switchLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(BasicActivity.getIstance(), "Nessun altro irrigatore disponibile", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
            }
        });

        this.switchRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(BasicActivity.getIstance(), "Nessun altro irrigatore disponibile", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
            }
        });

        return this.settingsAddIrrigator;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((BasicActivity) getActivity()).showToolbarMenu(true);
        MaterialToolbar toolbar = BasicActivity.getToolbar();
        toolbar.setNavigationIcon(null);
        toolbar.setNavigationOnClickListener(null);
    }

    private View settingsAddIrrigator;
    private MaterialButton saveButton;
    private MaterialButton dismissButton;
    private EditText sensorName;
    private EditText sensorType;
    private EditText sensorMinRadius;
    private EditText sensorMaxRadius;
    private ImageButton switchLeft;
    private ImageButton switchRight;
    private SettingsAddIrrigatorBusiness settingsBusiness;
}
