package com.gruppodieci.farming4u.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.business.GroundStatusBusiness;

public class GroundStatusFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate Fragment layout
        this.groundStatus = inflater.inflate(R.layout.stato_terreno, container, false);

        // Find all components
        this.leftSwitcher = this.groundStatus.findViewById(R.id.switchSensorsLeft);
        this.sensorImage = this.groundStatus.findViewById(R.id.sensorsImage);
        this.rightSwitcher = this.groundStatus.findViewById(R.id.switchSensorsRight);
        this.firstButtonHeader = this.groundStatus.findViewById(R.id.firstButtonHeader);
        this.firstSensorInformationButton= this.groundStatus.findViewById(R.id.firstSensorInformationButton);
        this.secondButtonHeader = this.groundStatus.findViewById(R.id.secondButtonHeader);
        this.secondSensorInformationButton= this.groundStatus.findViewById(R.id.secondSensorInformationButton);
        this.thirdButtonHeader = this.groundStatus.findViewById(R.id.thirdButtonHeader);
        this.thirdSensorInformationButton= this.groundStatus.findViewById(R.id.thirdSensorInformationButton);

        // Initialize business variable
        this.groundStatusBusiness = new GroundStatusBusiness();

        return this.groundStatus;
    }

    private View groundStatus;
    private ImageButton leftSwitcher;
    private ImageView sensorImage;
    private ImageButton rightSwitcher;
    private TextView firstButtonHeader;
    private MaterialButton firstSensorInformationButton;
    private TextView secondButtonHeader;
    private MaterialButton secondSensorInformationButton;
    private TextView thirdButtonHeader;
    private MaterialButton thirdSensorInformationButton;
    private GroundStatusBusiness groundStatusBusiness;
}
