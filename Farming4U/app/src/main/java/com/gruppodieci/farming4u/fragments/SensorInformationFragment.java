package com.gruppodieci.farming4u.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.business.SensorInformationBusiness;

public class SensorInformationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate Fragment layout
        this.sensorInformation = inflater.inflate(R.layout.sensors_information, container, false);

        // Find all components
        this.informationTitle = this.sensorInformation.findViewById(R.id.informationTitle);
        this.informationPriority = this.sensorInformation.findViewById(R.id.informationPriority);
        this.framePriorityColor = this.sensorInformation.findViewById(R.id.framePriorityColor);
        this.informationParameterImage = this.sensorInformation.findViewById(R.id.informationParameterImage);
        this.informationWarningImage = this.sensorInformation.findViewById(R.id.informationWarningImage);
        this.informationTextParameter = this.sensorInformation.findViewById(R.id.informationTextParameter);
        this.informationDescriptionTitle = this.sensorInformation.findViewById(R.id.informationDescriptionTitle);
        this.informationText = this.sensorInformation.findViewById(R.id.informationText);

        this.sensorInformationBusiness = new SensorInformationBusiness(this.informationTitle, this.informationPriority, this.framePriorityColor,
                                                                        this.informationParameterImage, this.informationWarningImage,
                                                                        this.informationTextParameter, this.informationDescriptionTitle,
                                                                        this.informationText);

        this.sensorInformationBusiness.setComponents();

        return this.sensorInformation;
    }

    private View sensorInformation;
    private TextView informationTitle;
    private TextView informationPriority;
    private FrameLayout framePriorityColor;
    private ImageView informationParameterImage;
    private ImageView informationWarningImage;
    private TextView informationTextParameter;
    private TextView informationDescriptionTitle;
    private TextView informationText;
    private SensorInformationBusiness sensorInformationBusiness;
}
