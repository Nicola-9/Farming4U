package com.gruppodieci.farming4u.fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
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

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.gruppodieci.farming4u.BottomNavigationMenu;
import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.business.CerchioView;
import com.gruppodieci.farming4u.business.Note;
import com.gruppodieci.farming4u.business.SavingFiles;
import com.gruppodieci.farming4u.business.Warning;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.futured.donut.DonutProgressView;
import app.futured.donut.DonutSection;

import static android.content.Context.VIBRATOR_SERVICE;


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

        imageColtivations=view.findViewById(R.id.imageColtivations);
        Glide.with(this).load(R.drawable.gif_irrigazione).into(imageColtivations);



        donut=view.findViewById(R.id.donut_view);
        DonutSection section1=new DonutSection("section 1", Color.parseColor(getResources().getString(0+R.color.colorDonutValue)),73f);
        donut.setCap(100f);
        donut.setAnimationDurationMs(2000);
        List<DonutSection> donuts= new ArrayList<>();
        donuts.add(section1);
        donut.submitData(donuts);

        MaterialToolbar toolbar= BasicActivity.getToolbar();
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
        int meteoGif[]={R.drawable.lightning,R.drawable.windy,R.drawable.partly_cloudy,R.drawable.storm,R.drawable.snow_with_rain,R.drawable.partly_cloudy_with_rain};
        int meteoGifSera[]={R.drawable.lightning,R.drawable.windy,R.drawable.storm,R.drawable.snow_with_rain};

        boolean sunny=random.nextBoolean();
        if(sunny){
            Glide.with(this).load(R.drawable.sunny).into(meteo1);
        }
        else
            Glide.with(this).load(meteoGif[random.nextInt(meteoGif.length)]).into(meteo1);
        sunny=random.nextBoolean();
        if(sunny){
            Glide.with(this).load(R.drawable.sunny).into(meteo2);
        }
        else
            Glide.with(this).load(meteoGif[random.nextInt(meteoGif.length)]).into(meteo2);
        sunny=random.nextBoolean();
        if(sunny){
            Glide.with(this).load(R.drawable.sunny).into(meteo3);
        }
        else
            Glide.with(this).load(meteoGif[random.nextInt(meteoGif.length)]).into(meteo3);
        sunny=random.nextBoolean();
        if(sunny){
            Glide.with(this).load(R.drawable.sunny).into(meteo4);
        }
        else
            Glide.with(this).load(meteoGif[random.nextInt(meteoGif.length)]).into(meteo4);
        Glide.with(this).load(meteoGifSera[random.nextInt(meteoGifSera.length)]).into(meteo5);


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
                    if (value <= 15) {
                        Log.d("DEBUG", "warning normale generato");

                        //generazione tipologia
                        int choice = random.nextInt(4);
                        String type = "";
                        switch (choice) {
                            case 0:
                                type = Warning.CONCIMAZIONE;
                                break;
                            case 1:
                                type = Warning.ERBA;
                                break;
                            case 2:
                                type = Warning.IRRIGAZIONE;
                                break;
                            case 3:
                                type = Warning.PESTICIDI;
                                break;
                        }

                        //warning normale
                        Warning warning = new Warning("Testo", false);
                        warning.setType(type);
                        int witch = random.nextInt(location.length);
                        warning.setWarning(type+location[witch]);
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
                        CerchioView cerchioView=new CerchioView(getContext(),warning.getxPosition(),warning.getyPosition(),warning.getSizeOfWarning(),warning.isSerious(),true,warning.getType());
                        frameWarning.addView(cerchioView);
                        cerchioView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                cerchioCliccato(view);
                            }
                        });
                        startVibration();

                    } else if (value > 20 && value <= 35) {

                        //generazione tipologia
                        int choice = random.nextInt(4);
                        String type = "";
                        switch (choice) {
                            case 0:
                                type = Warning.CONCIMAZIONE;
                                break;
                            case 1:
                                type = Warning.ERBA;
                                break;
                            case 2:
                                type = Warning.IRRIGAZIONE;
                                break;
                            case 3:
                                type = Warning.PESTICIDI;
                                break;
                        }

                        //warning grave
                        Log.d("DEBUG", "warning grave generato");
                        Warning warning = new Warning("Testo", true);
                        warning.setType(type);
                        int witch = random.nextInt(location.length);
                        warning.setWarning(type+location[witch]+" Richiesta massima urgenza.");
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
                        CerchioView cerchioView=new CerchioView(getContext(),warning.getxPosition(),warning.getyPosition(),warning.getSizeOfWarning(),warning.isSerious(),true,warning.getType());
                        frameWarning.addView(cerchioView);
                        cerchioView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                cerchioCliccato(view);
                            }
                        });
                        startVibration();


                    } else if (value > 65) {
                        Log.d("DEBUG", "warning risolto");
                        //Simulazione problema risolto
                        int size = warnings.size();
                        while(size>8){
                            deleteRandomWarning();
                            size = warnings.size();
                        }
                        if (size > 5) {
                            deleteRandomWarning();

                            saveWarnings();
                            setTextviewWarningAttivi();
                            disegnaCerchi();
                        }

                    }


                    handler.postDelayed(this, 5000);
                }
                else {
                    Log.d("DEBUG", "errore runnable");
                    handler.postDelayed(this, 5000);
                }

            }
        };
        runnable.run();
    }

    private void deleteRandomWarning(){
        ArrayList<Integer> posColtivazione=new ArrayList<>();
        ArrayList<Integer> posPesticidi=new ArrayList<>();
        ArrayList<Integer> posErba=new ArrayList<>();
        ArrayList<Integer> posIrrigazione=new ArrayList<>();
        for(int i=0;i<warnings.size();i++){
            switch (warnings.get(i).getType()){
                case Warning.CONCIMAZIONE:
                    posColtivazione.add(i);
                    break;
                case Warning.ERBA:
                    posErba.add(i);
                    break;
                case Warning.IRRIGAZIONE:
                    posIrrigazione.add(i);
                    break;
                case Warning.PESTICIDI:
                    posPesticidi.add(i);
                    break;
            }
        }
        int maxSize=Math.max(posColtivazione.size(),Math.max(posPesticidi.size(),Math.max(posErba.size(),posIrrigazione.size())));
        ArrayList<Integer>toDelete;
        if (posColtivazione.size()==maxSize)
            toDelete=posColtivazione;
        else if(posErba.size()==maxSize)
            toDelete=posErba;
        else if(posIrrigazione.size()==maxSize)
            toDelete=posIrrigazione;
        else  toDelete=posPesticidi;
        Log.d("DEBUGTYPE","toDelete size "+toDelete.size()+" maxSize "+maxSize);
        int randVal = random.nextInt(toDelete.size());
        Log.d("DEBUGTYPE","Deleted "+warnings.get(toDelete.get(randVal)).getType());
        Log.d("DEBUGTYPE","grandezza warnings prima di cancellare "+warnings.size());
        Log.d("DEBUGTYPE","Cancellare warning in posizione "+toDelete.get(randVal));
        warnings.remove((toDelete.get(randVal)).intValue());
        Log.d("DEBUGTYPE","grandezza warnings dopo aver cancellato "+warnings.size());

        for(Warning warn:warnings){
            Log.d("DEBUGTYPE","Warning type "+warn.getType());
        }
        Log.d("DEBUGTYPE","\n\n");
    }

    private void startVibration(){
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(250, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE)).vibrate(250);
        }
    }

    private void disegnaCerchi() {
        frameWarning.removeAllViews();
        for(Warning warning:warnings){
            Log.d("DEBUGCERCHI","Warnings type: "+warning.getType());
            CerchioView cerchioView=new CerchioView(getContext(),warning.getxPosition(),warning.getyPosition(),warning.getSizeOfWarning(),warning.isSerious(),warning.getType());
            frameWarning.addView(cerchioView);
            cerchioView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cerchioCliccato(view);
                }
            });
        }
        frameWarning.invalidate();
    }

    private void cerchioCliccato(View view) {
        CerchioView cerchiooView=(CerchioView)view;
        Log.d("DEBUG","Cerchio cliccato, tipo "+cerchiooView.getType());
    }

    private void saveWarnings(){
        SavingFiles.saveFile("fileWarnings",warnings);
    }

    public static void loadWarnings(){
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

    private void showGif(View view) {
        ImageView imageView = view.findViewById(R.id.immagineMeteo1);
        Glide.with(this).load(R.drawable.meteo_11).into(imageView);
    }


    private int frameHeight;
    private int frameWidth;
    private String[] warningsSerious = new String[]{"Temperatura estrema nella piantagione di mele","Terreno eccessivamente arido nel vitigno","Grave mancanza di pesticida nella piantagione di uva Big Perlon","Umidità eccessiva rilevata nella piantagione di zucche"};
    private String[] warningsNotSerious = new String[]{"Temperatura elevata nella piantagione di mele","Terreno arido nel vitigno","Mancanza di pesticida nella piantagione di uva Big Perlon","Umidità eccessiva rilevata nella piantagione di zucche"};
    private String[] location = new String[]{" nella piantagione di mele."," nel vitigno."," nella piantagione di uva Big Perlon."," nella piantagione di zucche."," nel campo di pomodori."};

    private Runnable runnable;
    private Handler handler;
    private FrameLayout frameWarning;
    public static ArrayList<Warning> warnings;
    private MaterialCardView m2Coltivati;
    private MaterialCardView noteSalvate;
    private MaterialCardView warningAttivi;
    private TextView textviewWarningAttivi;
    private View view;
    private Random random;
    //private ImageView mappa;
    private ImageView imageColtivations;
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
