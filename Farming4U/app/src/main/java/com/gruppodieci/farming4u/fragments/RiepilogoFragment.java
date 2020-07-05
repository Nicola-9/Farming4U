package com.gruppodieci.farming4u.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.gruppodieci.farming4u.BottomNavigationMenu;
import com.gruppodieci.farming4u.MainActivity;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.business.CerchioView;
import com.gruppodieci.farming4u.business.Note;
import com.gruppodieci.farming4u.business.RunnableWarning;
import com.gruppodieci.farming4u.business.SavingFiles;
import com.gruppodieci.farming4u.business.Warning;

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
        warnings=new ArrayList<>();
        frameWidth=-1;
        frameHeight=-1;
        handler = new Handler();

        aggiornaWarning();
        Log.d("DEBUG","oncreate riepilogofragment chiamata");
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.riepilogo_fragment, container, false);
        primaNotaRiepilogo=view.findViewById(R.id.primaNotaRiepilogo);
        secondaNotaRiepilogo=view.findViewById(R.id.secondaNotaRiepilogo);
        nrNoteSalvateRiepilogo=view.findViewById(R.id.nrNoteSalvateRiepilogo);

        String primaNota=noteSaved.get(0).getNota();
        String secondaNota=noteSaved.get(1).getNota();

        nrNoteSalvateRiepilogo.setText(""+noteSaved.size()+" note salvate");
        primaNotaRiepilogo.setText(primaNota!=null?primaNota:"");
        secondaNotaRiepilogo.setText(secondaNota!=null?secondaNota:"");

        textviewWarningAttivi=view.findViewById(R.id.textviewWarningAttivi);


        warningAttivi=view.findViewById(R.id.materialcardWarningAttivi);
        warningAttivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new WarningFragment();

                BottomNavigationMenu.replaceFragment(fragment,true);
                BottomNavigationMenu.setActiveFragment(fragment);
            }
        });

        noteSalvate=view.findViewById(R.id.materialcardNoteSalvate);
        noteSalvate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new NotesFragment();

                BottomNavigationMenu.replaceFragment(fragment,true);
                BottomNavigationMenu.setActiveFragment(fragment);
            }
        });

        m2Coltivati=view.findViewById(R.id.materialcardm2Coltivati);
        m2Coltivati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Fragment coltivazioni",Toast.LENGTH_LONG).show();
            }
        });

        assegnaMeteo();

        //mappa=view.findViewById(R.id.riepilogoImmagineMappa);

        frameWarning=view.findViewById(R.id.frameWarning);
        frameTreeObserver();




        donut=view.findViewById(R.id.donut_view);
        DonutSection section1=new DonutSection("section 1", Color.parseColor(getResources().getString(0+R.color.colorDonutValue)),50f);
        donut.setCap(100f);
        donut.setAnimationDurationMs(2000);
        List<DonutSection> donuts= new ArrayList<>();
        donuts.add(section1);
        donut.submitData(donuts);

        MaterialToolbar toolbar=MainActivity.getToolbar();
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle("Home");



        return view;
    }

    private void setTextviewWarningAttivi() {
        textviewWarningAttivi.setText(""+warnings.size()+" problemi attivi");
    }

    private void frameTreeObserver(){
        frameWarning.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                frameWarning.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                frameHeight=frameWarning.getHeight(); //height is ready
                frameWidth=frameWarning.getWidth();
                Log.d("DEBUG","onGlobalLayout called, framewidth "+frameWidth+" frameheight "+frameHeight);
            }
        });
    }



    private void assegnaMeteo() {
        meteo1=view.findViewById(R.id.immagineMeteo1);
        meteo2=view.findViewById(R.id.immagineMeteo2);
        meteo3=view.findViewById(R.id.immagineMeteo3);
        meteo4=view.findViewById(R.id.immagineMeteo4);
        meteo5=view.findViewById(R.id.immagineMeteo5);
        int meteoGiorno[]={R.drawable.meteo_1,R.drawable.meteo_2,R.drawable.meteo_3,R.drawable.meteo_4,R.drawable.meteo_5,R.drawable.meteo_6,R.drawable.meteo_7,R.drawable.meteo_8,R.drawable.meteo_9,R.drawable.meteo_10};
        int meteoSera[]={R.drawable.meteo_luna_1,R.drawable.meteo_luna_2,R.drawable.meteo_luna_3,R.drawable.meteo_luna_4,R.drawable.meteo_luna_5,R.drawable.meteo_luna_6};

        meteo1.setImageResource(meteoGiorno[random.nextInt(10)]);
        meteo2.setImageResource(meteoGiorno[random.nextInt(10)]);
        meteo3.setImageResource(meteoGiorno[random.nextInt(10)]);
        meteo4.setImageResource(meteoGiorno[random.nextInt(10)]);
        meteo5.setImageResource(meteoSera[random.nextInt(6)]);

    }

    @Override
    public void onResume() {
        super.onResume();
        frameTreeObserver();
        loadWarnings();
        runnable.run();
        setTextviewWarningAttivi();
        disegnaCerchi();



    }

    @Override
    public void onStop() {
        super.onStop();
        killRunnableWarning();
    }


    private void aggiornaWarning(){

        runnable = new Runnable() {
            public void run() {
                if (frameWidth > 0) {
                    Log.d("DEBUG", "runnable attivo");
                    int value = random.nextInt(100);
                    if (value <= 20) {
                        Log.d("DEBUG", "warning normale generato");
                        //warning normale
                        Warning warning = new Warning("Testo", false);
                        int witch = random.nextInt(warningsNotSerious.length);
                        warning.setWarning(warningsNotSerious[witch]);
                        Log.d("DEBUG", "Overlap!");
                        int size = ((random.nextInt(5) + 3) * 25);
                        Log.d("DEBUG", "framewidth " + frameWidth + " frameheight " + frameHeight);
                        int xPosition = random.nextInt(frameWidth - size - size) + size;
                        int yPosition = random.nextInt(frameHeight - size - size) + size;
                        while (isOverlap(xPosition, yPosition, size)) {
                            Log.d("DEBUG", "Overlap!");
                            size = ((random.nextInt(5) + 3) * 25);
                            xPosition = random.nextInt(frameWidth - size - size) + size;
                            yPosition = random.nextInt(frameHeight - size - size) + size;
                        }
                        warning.setxPosition(xPosition);
                        warning.setyPosition(yPosition);
                        warning.setSizeOfWarning(size);
                        warnings.add(warning);
                        saveWarnings();
                        setTextviewWarningAttivi();
                        disegnaCerchi();

                    } else if (value > 20 && value <= 30) {
                        //warning grave
                        Log.d("DEBUG", "warning grave generato");
                        Warning warning = new Warning("Testo", true);
                        int witch = random.nextInt(warningsSerious.length);
                        warning.setWarning(warningsSerious[witch]);
                        int size = ((random.nextInt(5) + 3) * 25);
                        Log.d("DEBUG", "framewidth " + frameWidth + " frameheight " + frameHeight);
                        int xPosition = random.nextInt(frameWidth - size - size) + size;
                        int yPosition = random.nextInt(frameHeight - size - size) + size;
                        while (isOverlap(xPosition, yPosition, size)) {
                            size = ((random.nextInt(5) + 3) * 25);
                            xPosition = random.nextInt(frameWidth - size - size) + size;
                            yPosition = random.nextInt(frameHeight - size - size) + size;
                        }
                        warning.setxPosition(xPosition);
                        warning.setyPosition(yPosition);
                        warning.setSizeOfWarning(size);
                        warnings.add(warning);
                        saveWarnings();
                        setTextviewWarningAttivi();
                        disegnaCerchi();


                    } else if (value > 70) {
                        Log.d("DEBUG", "warning risolto");
                        //Simulazione problema risolto
                        int size = warnings.size();
                        while(size>7){
                            size = warnings.size();
                            int randVal = random.nextInt(size);
                            warnings.remove(randVal);
                        }
                        if (size > 3) {
                            int randVal = random.nextInt(size);
                            warnings.remove(randVal);
                            saveWarnings();
                            setTextviewWarningAttivi();
                            disegnaCerchi();
                        }

                    }


                    handler.postDelayed(this, 3000);
                }
                else {
                    Log.d("DEBUG", "errore runnable");
                    handler.postDelayed(this, 3000);
                }

            }
        };
        runnable.run();
    }

    private void disegnaCerchi() {
        frameWarning.removeAllViews();
        for(Warning warning:warnings){
            CerchioView cerchioView=new CerchioView(getContext(),warning.getxPosition(),warning.getyPosition(),warning.getSizeOfWarning(),warning.isSerious());
            frameWarning.addView(cerchioView);
        }
        frameWarning.invalidate();
    }

    private void saveWarnings(){
        SavingFiles.saveFile("fileWarnings",warnings);
    }

    private void loadWarnings(){
        warnings=(ArrayList<Warning>)SavingFiles.loadFile("fileWarnings");
        if(warnings==null){
            warnings=new ArrayList<>();
        }
    }

    private void killRunnableWarning(){
        Log.d("DEBUG","runnable killato");
        handler.removeCallbacks(runnable);
    }
    private boolean isOverlap(int x,int y,int radius) {
        boolean overlap=false;
        for(Warning warning:warnings){
            if(areCircleOverlapping(warning.getxPosition(),warning.getyPosition(),x,y,warning.getSizeOfWarning(),radius)){
                overlap=true;
            }
        }
        return overlap;
    }
    private boolean areCircleOverlapping(int xA, int yA,int xB, int yB,int radiusA,int radiusB) {
        double diffX = Math.abs(xA - xB);
        double diffY = Math.abs(yA - yB);
        return Math.hypot(xA - xB, yA - yB) <= radiusA+radiusB;
    }


    private int frameHeight;
    private int frameWidth;
    private String[] warningsSerious = new String[]{"Temperatura estrema nella piantagione di mele","Terreno eccessivamente arido nel vitigno","Grave mancanza di pesticida nella piantagione di uva Big Perlon","Umidità eccessiva rilevata nella piantagione di zucche"};
    private String[] warningsNotSerious = new String[]{"Temperatura elevata nella piantagione di mele","Terreno arido nel vitigno","Mancanza di pesticida nella piantagione di uva Big Perlon","Umidità eccessiva rilevata nella piantagione di zucche"};
    private Runnable runnable;
    private Handler handler;
    private FrameLayout frameWarning;
    private ArrayList<Warning> warnings;
    private MaterialCardView m2Coltivati;
    private MaterialCardView noteSalvate;
    private MaterialCardView warningAttivi;
    private TextView textviewWarningAttivi;
    private View view;
    private Random random;
    //private ImageView mappa;
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
