package com.gruppodieci.farming4u.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.gruppodieci.farming4u.R;

import static com.gruppodieci.farming4u.BottomNavigationMenu.replaceFragment;

public class GroundsFragment extends Fragment {

    private View grounds;
    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.grounds = inflater.inflate(R.layout.fragment_grounds, container, false);

        this.tabLayout = this.grounds.findViewById(R.id.tabGrounds);

        tabLayout.addTab(tabLayout.newTab().setText("Cura delle piante"));
        tabLayout.addTab(tabLayout.newTab().setText("Trattamento del terreno"));
        tabLayout.addTab(tabLayout.newTab().setText("Semina e coltivazione"));

        replaceFragment(R.id.mapContent, new CuraPianteFragment());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Fragment fragment;

                switch (tab.getText().toString()) {
                    case "Cura delle piante":

                        fragment = new CuraPianteFragment();

                        replaceFragment(R.id.mapContent,fragment);

                        break;

                    case "Trattamento del terreno":

                        break;

                    case "Semina e coltivazione":

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

}
