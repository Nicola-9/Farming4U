package com.gruppodieci.farming4u.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.business.CerchioView;
import com.gruppodieci.farming4u.business.Warning;

public class CuraPianteFragment extends Fragment {

    //temp
    FrameLayout map;

    View curaPiante;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        this.curaPiante = inflater.inflate(R.layout.fragment_cura_piante, container, false);

        this.map = this.curaPiante.findViewById(R.id.map);

        RiepilogoFragment.loadWarnings();

        disegnaCerchi(map);

        return curaPiante;

    }

    void disegnaCerchi(FrameLayout frameWarning) {

        frameWarning.removeAllViews();

        for(Warning warning:RiepilogoFragment.warnings){

            if( warning.getType().equals(Warning.CONCIMAZIONE) || warning.getType().equals(Warning.PESTICIDI) ) {

                CerchioView cerchioView=new CerchioView(getContext(),warning.getxPosition(),warning.getyPosition(),warning.getSizeOfWarning(),warning.isSerious());
                frameWarning.addView(cerchioView);

            }

        }

        frameWarning.invalidate();

    }

}
