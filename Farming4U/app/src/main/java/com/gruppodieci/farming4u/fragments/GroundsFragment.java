package com.gruppodieci.farming4u.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textview.MaterialTextView;
import com.gruppodieci.farming4u.BottomNavigationMenu;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.activity.BasicActivity;

import static com.gruppodieci.farming4u.BottomNavigationMenu.replaceFragment;

public class GroundsFragment extends Fragment {

    private View grounds;
    private static TabLayout tabLayout;
    public TabLayout.Tab semina;
    public TabLayout.Tab cura;
    public TabLayout.Tab trattamento;


    static Fragment activeFragment;
    static String activeTab = null;

    public static GroundsFragment instance;

    public static boolean flagCura = false;
    public static boolean flagTrattamento = false;
    public static boolean flagSemina = false;
    public static boolean isSet = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.grounds = inflater.inflate(R.layout.fragment_grounds, container, false);

        isSet = true;

        this.tabLayout = this.grounds.findViewById(R.id.tabGrounds);

        this.tabLayout.setElevation(10);

        this.instance = this;

        semina = tabLayout.newTab().setText("Semina e coltivazione");
        cura=tabLayout.newTab().setText("Cura delle piante");
        trattamento=tabLayout.newTab().setText("Trattamento del terreno");
        tabLayout.addTab(cura);
        tabLayout.addTab(trattamento);
        tabLayout.addTab(semina);

        tabLayout.setBackgroundColor(Color.parseColor("#71A3BB"));
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#FFFFFF"));

        if(flagCura) {

            activeTab = "cura";

        }
        else if (flagTrattamento) {

            activeTab = "trattamento";

        }
        else if (flagSemina) {

            activeTab = "semina";

        }

        flagCura = false;
        flagTrattamento = false;
        flagSemina = false;

        BasicActivity.getToolbar().setTitle("Terreni");

        if (activeTab != null && activeTab.equals("semina")){
            /*MaterialToolbar toolbar = BasicActivity.getToolbar();
            toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BasicActivity.getBasicActivity().onBackPressed();
                }
            });*/

            semina.select();

            Fragment semina = new SeminaFragment();
            BottomNavigationMenu.setActiveFragment(semina);
            activeFragment = semina;

            replaceFragment(R.id.mapContent, semina);
        }
        else if (activeTab != null && activeTab.equals("cura")) {

            cura.select();

            Fragment cura = new CuraPianteFragment();
            BottomNavigationMenu.setActiveFragment(cura);
            activeFragment = cura;

            replaceFragment(R.id.mapContent, cura);

        }
        else if(activeTab != null && activeTab.equals("trattamento")){

            trattamento.select();

            Fragment trattamento = new TrattamentoTerrenoFragment();
            BottomNavigationMenu.setActiveFragment(trattamento);
            activeFragment = trattamento;

            replaceFragment(R.id.mapContent, trattamento);

        }
        else {
            replaceFragment(R.id.mapContent, new CuraPianteFragment());
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Fragment fragment;

                switch (tab.getText().toString()) {
                    case "Cura delle piante":

                        fragment = new CuraPianteFragment();

                        replaceFragment(R.id.mapContent,fragment);

                        activeFragment = fragment;

                        break;

                    case "Trattamento del terreno":

                        fragment = new TrattamentoTerrenoFragment();

                        replaceFragment(R.id.mapContent,fragment);

                        activeFragment = fragment;

                        break;

                        case "Semina e coltivazione":

                        fragment = new SeminaFragment();
                        replaceFragment(R.id.mapContent,fragment);

                        activeFragment = fragment;

                        break;

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return this.grounds;

    }

    public static TabLayout getTab(){
        return tabLayout;
    }

    public static void setTab(String tab){
        activeTab = tab;
    }

    public static GroundsFragment getInstance() {

        return instance;

    }


}
