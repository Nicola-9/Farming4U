package com.gruppodieci.farming4u.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.business.Note;
import com.gruppodieci.farming4u.business.SavingFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.futured.donut.DonutProgressView;
import app.futured.donut.DonutSection;


public class RiepilogoFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteSaved=(ArrayList<Note>) SavingFiles.loadFile("fileNotes");
        random=new Random();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.riepilogo_fragment, container, false);
        primaNotaRiepilogo=view.findViewById(R.id.primaNotaRiepilogo);
        secondaNotaRiepilogo=view.findViewById(R.id.secondaNotaRiepilogo);
        nrNoteSalvateRiepilogo=view.findViewById(R.id.nrNoteSalvateRiepilogo);

        String primaNota=noteSaved.get(0).getNota();
        String secondaNota=noteSaved.get(1).getNota();

        nrNoteSalvateRiepilogo.setText(""+noteSaved.size()+" note salvate");
        primaNotaRiepilogo.setText(primaNota!=null?primaNota:"");
        secondaNotaRiepilogo.setText(secondaNota!=null?secondaNota:"");


        meteo1=view.findViewById(R.id.immagineMeteo1);
        meteo2=view.findViewById(R.id.immagineMeteo2);
        meteo3=view.findViewById(R.id.immagineMeteo3);
        meteo4=view.findViewById(R.id.immagineMeteo4);
        meteo5=view.findViewById(R.id.immagineMeteo5);

        assegnaMeteo();

        donut=view.findViewById(R.id.donut_view);
        DonutSection section1=new DonutSection("section 1", Color.parseColor(getResources().getString(0+R.color.colorDonutValue)),50f);
        donut.setCap(100f);
        donut.setAnimationDurationMs(2000);
        List<DonutSection> donuts= new ArrayList<>();
        donuts.add(section1);
        donut.submitData(donuts);


        return view;
    }

    private void assegnaMeteo() {
        int meteoGiorno[]={R.drawable.meteo_1,R.drawable.meteo_2,R.drawable.meteo_3,R.drawable.meteo_4,R.drawable.meteo_5,R.drawable.meteo_6,R.drawable.meteo_7,R.drawable.meteo_8,R.drawable.meteo_9,R.drawable.meteo_10};
        int meteoSera[]={R.drawable.meteo_luna_1,R.drawable.meteo_luna_2,R.drawable.meteo_luna_3,R.drawable.meteo_luna_4,R.drawable.meteo_luna_5,R.drawable.meteo_luna_6};

        meteo1.setImageResource(meteoGiorno[random.nextInt(10)]);
        meteo2.setImageResource(meteoGiorno[random.nextInt(10)]);
        meteo3.setImageResource(meteoGiorno[random.nextInt(10)]);
        meteo4.setImageResource(meteoGiorno[random.nextInt(10)]);
        meteo5.setImageResource(meteoSera[random.nextInt(6)]);

    }


    private Random random;
    private ImageView meteo1;
    private ImageView meteo2;
    private ImageView meteo3;
    private ImageView meteo4;
    private ImageView meteo5;
    private DonutProgressView donut;
    private ArrayList<Note> noteSaved;
    private TextView primaNotaRiepilogo;
    private TextView secondaNotaRiepilogo;
    private TextView nrNoteSalvateRiepilogo;
}
