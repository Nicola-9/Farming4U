package com.gruppodieci.farming4u.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.gruppodieci.farming4u.R;

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

        return this.grounds;

    }

}
