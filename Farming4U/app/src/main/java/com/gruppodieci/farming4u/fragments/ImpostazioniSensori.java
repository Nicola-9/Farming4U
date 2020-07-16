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

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gruppodieci.farming4u.BottomNavigationMenu;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.activity.BasicActivity;

import java.util.Random;

public class ImpostazioniSensori extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isBeacon=true;
        random=new Random();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.impostazioni_sensori, container, false);


        switchSensorsLeft=view.findViewById(R.id.switchSensorsLeft);
        switchSensorsRight=view.findViewById(R.id.switchSensorsRight);
        imageSensore1=view.findViewById(R.id.imageSensore1);
        imageSensore2=view.findViewById(R.id.imageSensore2);
        imageSensore3=view.findViewById(R.id.imageSensore3);
        imageSensore4=view.findViewById(R.id.imageSensore4);
        imageSensore5=view.findViewById(R.id.imageSensore5);
        imageSensore6=view.findViewById(R.id.imageSensore6);
        sensorsImage=view.findViewById(R.id.sensorsImage);
        sensorName=view.findViewById(R.id.sensorName);
        floatingAggiungiSensore=view.findViewById(R.id.floatingAggiungiSensore);


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

        floatingAggiungiSensore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aggiungiSensore();
            }
        });

        setToolbar();
        return view;
    }

    private void aggiungiSensore() {
        BottomNavigationMenu.replaceFragment(new AggiungiNuovoSensore(),true);

    }

    private void cambiaSensore(){
        if(isBeacon){
            sensorsImage.setImageResource(R.drawable.sensore_sonda);
            sensorName.setText("Sensore Vigneto");
        }
        else {
            sensorsImage.setImageResource((R.drawable.beacon_ble));
            sensorName.setText("Sonda Oliveto");

        }
        isBeacon=!isBeacon;

        imageSensore1.setImageResource(meteoGiorno[random.nextInt(10)]);
        imageSensore2.setImageResource(meteoGiorno[random.nextInt(10)]);
        imageSensore3.setImageResource(meteoGiorno[random.nextInt(10)]);
        imageSensore4.setImageResource(meteoGiorno[random.nextInt(10)]);
        imageSensore5.setImageResource(meteoSera[random.nextInt(6)]);
        imageSensore6.setImageResource(meteoSera[random.nextInt(6)]);

    }


    private void setToolbar() {

        ((BasicActivity)getActivity()).showToolbarMenu(false);
        MaterialToolbar toolbar= BasicActivity.getToolbar();
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle("Gestione Sensori");
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

    private Random random;
    private int meteoGiorno[]={R.drawable.meteo_1,R.drawable.meteo_2,R.drawable.meteo_3,R.drawable.meteo_4,R.drawable.meteo_5,R.drawable.meteo_6,R.drawable.meteo_7,R.drawable.meteo_8,R.drawable.meteo_9,R.drawable.meteo_10};
    private int meteoSera[]={R.drawable.meteo_luna_1,R.drawable.meteo_luna_2,R.drawable.meteo_luna_3,R.drawable.meteo_luna_4,R.drawable.meteo_luna_5,R.drawable.meteo_luna_6};
    private boolean isBeacon;
    private FloatingActionButton floatingAggiungiSensore;
    private TextView sensorName;
    private ImageView sensorsImage;
    private ImageView imageSensore1;
    private ImageView imageSensore2;
    private ImageView imageSensore3;
    private ImageView imageSensore4;
    private ImageView imageSensore5;
    private ImageView imageSensore6;
    private ImageButton switchSensorsRight;
    private ImageButton switchSensorsLeft;
    private View view;
}
