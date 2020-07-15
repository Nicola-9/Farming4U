package com.gruppodieci.farming4u.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.activity.BasicActivity;

import java.util.Random;

public class AggiungiNuovoSensore extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isBeacon=true;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.nuovo_sensore, container, false);
        saveButton=view.findViewById(R.id.saveButtonSensore);
        returnButton=view.findViewById(R.id.returnButtonSensore);
        switchSensorsLeft=view.findViewById(R.id.switchSensorsLeft);
        switchSensorsRight=view.findViewById(R.id.switchSensorsRight);
        sensorsImage=view.findViewById(R.id.sensorsImage);
        switchSensorsLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiaSensore();
            }
        });

        switchSensorsRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiaSensore();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });


        setToolbar();
        return view;
    }

    private void setToolbar() {

        ((BasicActivity)getActivity()).showToolbarMenu(false);
        MaterialToolbar toolbar= BasicActivity.getToolbar();
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle("Aggiunta sensore");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((BasicActivity)getActivity()).showToolbarMenu(true);
        MaterialToolbar toolbar= BasicActivity.getToolbar();
        toolbar.setNavigationIcon(null);

    }

    private void cambiaSensore() {
        if (isBeacon) {
            sensorsImage.setImageResource(R.drawable.sensore_sonda);
        } else {
            sensorsImage.setImageResource((R.drawable.beacon_ble));

        }
        isBeacon=!isBeacon;
    }

    private ImageView sensorsImage;
    private ImageButton switchSensorsRight;
    private ImageButton switchSensorsLeft;
    private boolean isBeacon;
    private MaterialButton returnButton;
    private MaterialButton saveButton;
    private View view;
}
