package com.gruppodieci.farming4u.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.gruppodieci.farming4u.BottomNavigationMenu;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.business.DrawExistingRectangle;
import com.gruppodieci.farming4u.business.DrawTheRectangle;
import com.gruppodieci.farming4u.business.DrawTheSelectRectangle;
import com.gruppodieci.farming4u.business.SavingFiles;
import com.gruppodieci.farming4u.business.SavingFilesSeminaTerreni;
import com.gruppodieci.farming4u.business.TerreniColtivati;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.zip.Inflater;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.gruppodieci.farming4u.BottomNavigationMenu.replaceFragment;
import static com.gruppodieci.farming4u.fragments.GroundsFragment.activeFragment;


public class SeminaFragment extends Fragment {

    private View view;
    private TextInputEditText input_text;
    private ImageButton img1;
    private ImageButton img2;
    private ImageButton img3;
    private ImageButton img4;
    private ImageButton img5;
    private ImageButton img6;
    private ImageButton img7;
    private ImageButton imgMunu;
    private Button draw;
    private Button cancella;
    private Button conferma;
    private DrawTheRectangle drawRectangle;
    private DrawTheSelectRectangle existingRectangle;
    private FrameLayout frame;
    float x;
    float y;
    private ArrayList<TerreniColtivati> terreniFile;
    private ArrayList<TerreniColtivati> terreniSele = new ArrayList<TerreniColtivati>();
    private Button save;
    private int selezionaIMGB;
    float x_sel_inizio;
    float y_sel_inizio;
    float x_sel_fine;
    float y_sel_fine;
    Boolean selezionato1 = false;
    Boolean selezionato2 = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        setHasOptionsMenu(true);

    }

    @Override
    public void onStop() {
        super.onStop();
        frame.removeAllViews();
    }

    @Override
    public void onPause() {
        super.onPause();
        frame.removeAllViews();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BasicActivity.getToolbar().setVisibility(View.VISIBLE);
        GroundsFragment.getTab().setVisibility(View.VISIBLE);
        view = inflater.inflate(R.layout.riepilogo_fragment, container, false);

        GroundsFragment.setTab(null);

        // Inflate Fragment layout
        this.view = inflater.inflate(R.layout.semina_fragment, container, false);

        frame = this.view.findViewById(R.id.mappa);

        save = this.view.findViewById(R.id.salva);
        save.setVisibility(View.GONE);

        cancella = this.view.findViewById(R.id.cancel);

        //bottone conferma selezione
        conferma = this.view.findViewById(R.id.confirm);

        drawRectangle = new DrawTheRectangle(getContext(), frame);


        final SavingFilesSeminaTerreni salva = new SavingFilesSeminaTerreni();

        terreniFile = salva.readFromFileTerreni(getContext());

        if(terreniFile == null){
            //File vuoto
            terreniFile = new ArrayList<TerreniColtivati>();
        }

       frame.addView(new DrawExistingRectangle(getContext(), frame, terreniFile));

        cancella.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
               /* activeFragment = new GroundsFragment();
                replaceFragment(activeFragment);

                BasicActivity.getIstance().getSupportActionBar().show();

                BasicActivity.getToolbar().setElevation(0);

                BasicActivity.getToolbar().setNavigationIcon(null);*/

                Fragment fragment = new SeminaFragment();
                replaceFragment(R.id.mapContent, fragment);
                BasicActivity.getIstance().getSupportActionBar().show();

                Toast.makeText(getContext(), "Operazione annullata" , Toast.LENGTH_SHORT).show();
            }
        });


        //salva area coltivabile disegnata
        save.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                //terreniFile = salva.readFromFileTerreni(getContext());

                TerreniColtivati terreni = new TerreniColtivati();
                terreni.setxPositionInizio(drawRectangle.getInizio_x());
                terreni.setyPositionInizio(drawRectangle.getInizio_y());
                terreni.setxPositionFine(drawRectangle.getFine_x());
                terreni.setyPositionFine(drawRectangle.getFine_y());

                System.out.println(terreni.toString());

                ArrayList<TerreniColtivati> terra = terreniFile;

                terra.add(terreni);

                salva.saveTerreni(terra, getContext());

                System.out.println(terra.toString());

                save.setVisibility(View.GONE);

                //restart fragment
                Fragment fragment = new SeminaFragment();
                replaceFragment(R.id.semina,fragment);
                //BottomNavigationMenu.setActiveFragment(fragment);
               // BasicActivity.getIstance().getSupportActionBar().hide();

            }
        });


        conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selezionato1 == true && selezionato2 == true){
                    selezionato1 = false;
                    selezionato2 = false;

                    Fragment fragment = new InformazioniSpecificheColtureFragment(selezionaIMGB, x_sel_inizio, x_sel_fine, y_sel_inizio, y_sel_fine);
                    replaceFragment(R.id.mapContent,fragment);
                    BottomNavigationMenu.setActiveFragment(fragment);
                    BasicActivity.getIstance().getSupportActionBar().hide();

                }
                else if(selezionato1 == false || selezionato2 == false){
                    Toast.makeText(getContext(), "Selezionare sia il terreno che la coltura" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.input_text = this.view.findViewById(R.id.input_text);
        img1 = this.view.findViewById(R.id.icona1);
        img2 = this.view.findViewById(R.id.icona2);
        img3 = this.view.findViewById(R.id.icona3);
        img4 = this.view.findViewById(R.id.icona4);
        img5 = this.view.findViewById(R.id.icona5);
        img6 = this.view.findViewById(R.id.icona6);
        img7 = this.view.findViewById(R.id.icona7);
        imgMunu = this.view.findViewById(R.id.iconaMunu);

        imgMunu.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {

        PopupMenu popup = new PopupMenu(getContext(), imgMunu);

        popup.getMenuInflater().inflate(R.menu.menu_semina, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
        public boolean onMenuItemClick(MenuItem item) {
        //Toast.makeText(getContext(), "Hai cliccato : " + item.getTitle(), Toast.LENGTH_SHORT).show();

            switch (item.getItemId())
            {
                case R.id.disegnaSettingsButton:
                    frame.removeView(drawRectangle);
                    frame.refreshDrawableState();
                    frame.addView(drawRectangle);
                    frame.invalidate();
                    save.setVisibility(View.VISIBLE);

                    return true;


                case R.id.eliminaSettingsButton:
                    frame.removeView(drawRectangle);
                    save.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "seleziona l'area da cancellare" , Toast.LENGTH_LONG).show();
                    frame.setOnTouchListener(null);
                    //verifica se viene selezionata una zona
                   frame.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, @NotNull MotionEvent event) {
                            switch(event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    x = event.getX();
                                    y = event.getY();
                                    break;
                                case MotionEvent.ACTION_UP:
                                    for(TerreniColtivati t : terreniFile){
                                        if(x > t.getxPositionInizio() && x < t.getxPositionFine()){
                                            if(y > t.getyPositionInizio() && y < t.getyPositionFine()){
                                                DrawTheRectangle x = new DrawTheRectangle(getContext());
                                                x.disegnaRettangoloSel(t.getxPositionInizio(), t.getyPositionInizio(), t.getxPositionFine(), t.getyPositionFine());

                                                x_sel_inizio = t.getxPositionInizio();
                                                y_sel_inizio = t.getyPositionInizio();
                                                x_sel_fine = t.getxPositionFine();
                                                y_sel_fine = t.getyPositionFine();

                                                createDialogElininaOne(getContext());

                                            }
                                        }
                                    }
                                    break;
                            }
                            return true;
                        }


                    });

                    return true;

                case R.id.eliminaAllSettingsButton:
                    frame.removeView(drawRectangle);
                    save.setVisibility(View.GONE);
                    createDialogEliminatutto(getContext());
                    //onButtonShowPopupWindowClick(view);

                    return true;

                case R.id.selezionaSettingsButton:
                    frame.removeView(drawRectangle);
                    save.setVisibility(View.GONE);
                    //verifica se viene selezionata una zona
                    frame.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, @NotNull MotionEvent event) {
                            switch(event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    x = event.getX();
                                    y = event.getY();
                                    break;
                                case MotionEvent.ACTION_UP:
                                    for(TerreniColtivati t : terreniFile){
                                        if(x > t.getxPositionInizio() && x < t.getxPositionFine()){
                                            if(y > t.getyPositionInizio() && y < t.getyPositionFine()){
                                                DrawTheRectangle x = new DrawTheRectangle(getContext());
                                                x.disegnaRettangoloSel(t.getxPositionInizio(), t.getyPositionInizio(), t.getxPositionFine(), t.getyPositionFine());

                                                x_sel_inizio = t.getxPositionInizio();
                                                y_sel_inizio = t.getyPositionInizio();
                                                x_sel_fine = t.getxPositionFine();
                                                y_sel_fine = t.getyPositionFine();

                                                TerreniColtivati nuovoTerreno = new TerreniColtivati(x_sel_inizio, y_sel_inizio, x_sel_fine, y_sel_fine);

                                                terreniSele.add(nuovoTerreno);

                                                existingRectangle = new DrawTheSelectRectangle(getContext(), frame, terreniFile, terreniSele);

                                                frame.addView(existingRectangle);
                                                frame.invalidate();

                                                selezionato1 = true;
                                            }
                                        }
                                    }
                                    break;
                            }
                            return true;
                        }


                    });

                    return true;

                default:
                    Toast.makeText(getContext(), item.getTitle()+"non funziona: " , Toast.LENGTH_SHORT).show();
                    return false;
            }
         }
         });
        popup.show();}});

        img1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                img1.setBackgroundResource(R.drawable.rounded_fg);
                GradientDrawable drawable = (GradientDrawable) img1.getBackground();
                drawable.setAlpha(190);
                drawable.setColor(Color.GREEN);
                selezionato2 = true;
                selezionaIMGB = 1;

                img2.setBackgroundColor(Color.WHITE);
                img3.setBackgroundColor(Color.WHITE);
                img4.setBackgroundColor(Color.WHITE);
                img5.setBackgroundColor(Color.WHITE);
                img6.setBackgroundColor(Color.WHITE);
                img7.setBackgroundColor(Color.WHITE);

            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img2.setBackgroundResource(R.drawable.rounded_fg);
                GradientDrawable drawable = (GradientDrawable) img2.getBackground();
                drawable.setAlpha(190);
                drawable.setColor(Color.GREEN);
                selezionato2 = true;
                selezionaIMGB = 2;

                img1.setBackgroundColor(Color.WHITE);
                img3.setBackgroundColor(Color.WHITE);
                img4.setBackgroundColor(Color.WHITE);
                img5.setBackgroundColor(Color.WHITE);
                img6.setBackgroundColor(Color.WHITE);
                img7.setBackgroundColor(Color.WHITE);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img3.setBackgroundResource(R.drawable.rounded_fg);
                GradientDrawable drawable = (GradientDrawable) img3.getBackground();
                drawable.setAlpha(190);
                drawable.setColor(Color.GREEN);
                selezionato2 = true;
                selezionaIMGB = 3;

                img1.setBackgroundColor(Color.WHITE);
                img2.setBackgroundColor(Color.WHITE);
                img4.setBackgroundColor(Color.WHITE);
                img5.setBackgroundColor(Color.WHITE);
                img6.setBackgroundColor(Color.WHITE);
                img7.setBackgroundColor(Color.WHITE);

            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img4.setBackgroundResource(R.drawable.rounded_fg);
                GradientDrawable drawable = (GradientDrawable) img4.getBackground();
                drawable.setAlpha(190);
                drawable.setColor(Color.GREEN);
                selezionato2 = true;
                selezionaIMGB = 4;

                img1.setBackgroundColor(Color.WHITE);
                img2.setBackgroundColor(Color.WHITE);
                img3.setBackgroundColor(Color.WHITE);
                img5.setBackgroundColor(Color.WHITE);
                img6.setBackgroundColor(Color.WHITE);
                img7.setBackgroundColor(Color.WHITE);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img5.setBackgroundResource(R.drawable.rounded_fg);
                GradientDrawable drawable = (GradientDrawable) img5.getBackground();
                drawable.setAlpha(190);
                drawable.setColor(Color.GREEN);
                selezionato2 = true;
                selezionaIMGB = 5;

                img1.setBackgroundColor(Color.WHITE);
                img2.setBackgroundColor(Color.WHITE);
                img3.setBackgroundColor(Color.WHITE);
                img4.setBackgroundColor(Color.WHITE);
                img6.setBackgroundColor(Color.WHITE);
                img7.setBackgroundColor(Color.WHITE);
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img6.setBackgroundResource(R.drawable.rounded_fg);
                GradientDrawable drawable = (GradientDrawable) img6.getBackground();
                drawable.setAlpha(190);
                drawable.setColor(Color.GREEN);
                selezionato2 = true;
                selezionaIMGB = 6;

                img1.setBackgroundColor(Color.WHITE);
                img2.setBackgroundColor(Color.WHITE);
                img3.setBackgroundColor(Color.WHITE);
                img4.setBackgroundColor(Color.WHITE);
                img5.setBackgroundColor(Color.WHITE);
                img7.setBackgroundColor(Color.WHITE);
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img7.setBackgroundResource(R.drawable.rounded_fg);
                GradientDrawable drawable = (GradientDrawable) img7.getBackground();
                drawable.setAlpha(190);
                drawable.setColor(Color.GREEN);
                selezionato2 = true;
                selezionaIMGB = 7;

                img1.setBackgroundColor(Color.WHITE);
                img2.setBackgroundColor(Color.WHITE);
                img3.setBackgroundColor(Color.WHITE);
                img4.setBackgroundColor(Color.WHITE);
                img5.setBackgroundColor(Color.WHITE);
                img6.setBackgroundColor(Color.WHITE);
            }
        });


        input_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString().trim().toLowerCase();
                if (s.equals("k") || s.equals("ki") || s.equals("kiw") || s.equals("kiwi")) {
                    img1.setVisibility(View.GONE);
                    img2.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    img4.setVisibility(View.GONE);
                    img5.setVisibility(View.VISIBLE);
                    img6.setVisibility(View.GONE);
                    img7.setVisibility(View.GONE);
                }
                else if (s.equals("p") || s.equals("pi") || s.equals("pis") || s.equals("pise") || s.equals("pisel") || s.equals("pisell") || s.equals("piselli")) {
                    img1.setVisibility(View.GONE);
                    img2.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    img4.setVisibility(View.GONE);
                    img5.setVisibility(View.GONE);
                    img6.setVisibility(View.VISIBLE);
                    img7.setVisibility(View.GONE);
                }
                else if (s.equals("u") || s.equals("uv") || s.equals("uva")) {
                    img1.setVisibility(View.GONE);
                    img2.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    img4.setVisibility(View.GONE);
                    img5.setVisibility(View.GONE);
                    img6.setVisibility(View.GONE);
                    img7.setVisibility(View.VISIBLE);
                }
                else {
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.VISIBLE);
                    img3.setVisibility(View.VISIBLE);
                    img4.setVisibility(View.VISIBLE);
                    img5.setVisibility(View.GONE);
                    img6.setVisibility(View.GONE);
                    img7.setVisibility(View.GONE);
                }
            }
        });

        return this.view;
    }

            public void createDialogEliminatutto(final Context context){
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                SavingFilesSeminaTerreni s = new SavingFilesSeminaTerreni();

                                s.clearAllTerreni(getContext());
                                //restart fragment
                                Fragment fragment = new SeminaFragment();
                                replaceFragment(R.id.semina,fragment);
                                BottomNavigationMenu.setActiveFragment(fragment);
                                BasicActivity.getIstance().getSupportActionBar().hide();

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                new MaterialAlertDialogBuilder(context)
                        .setTitle("Attenzione")
                        .setMessage("Stai per eliminare ogni zona selezionata e coltivata. Vuoi procedere?")
                        .setNegativeButton("No",dialogClickListener)
                        .setPositiveButton("Si",dialogClickListener)
                        .show();
            }


    public void createDialogElininaOne(final Context context){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        TerreniColtivati nuovoTerreno = new TerreniColtivati(x_sel_inizio, y_sel_inizio, x_sel_fine, y_sel_fine);
                        SavingFilesSeminaTerreni elimina = new SavingFilesSeminaTerreni();

                        elimina.clearOneTerreni(nuovoTerreno, getContext());

                        //restart fragment
                        /*Fragment fragment = new SeminaFragment();
                        replaceFragment(R.id.mappa,fragment);*/
                        Fragment fragment = new SeminaFragment();
                        replaceFragment(R.id.mapContent, fragment);
                        BasicActivity.getIstance().getSupportActionBar().show();
                        BottomNavigationMenu.setActiveFragment(fragment);


                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        new MaterialAlertDialogBuilder(context)
                .setTitle("Attenzione")
                .setMessage("Stai per eliminare la zona selezionata. Vuoi procedere?")
                .setNegativeButton("No",dialogClickListener)
                .setPositiveButton("Si",dialogClickListener)
                .show();
    }
}