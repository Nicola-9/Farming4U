package com.gruppodieci.farming4u.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gruppodieci.farming4u.R;

public class CuraPianteFragment extends Fragment {

    //temp
    TextView textView;

    View curaPiante;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        this.curaPiante = inflater.inflate(R.layout.fragment_cura_piante, container, false);

        this.textView = this.curaPiante.findViewById(R.id.testoprova);

        return curaPiante;

    }
}
