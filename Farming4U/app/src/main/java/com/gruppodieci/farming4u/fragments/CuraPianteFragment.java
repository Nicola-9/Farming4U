package com.gruppodieci.farming4u.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gruppodieci.farming4u.BottomNavigationMenu;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.business.CerchioView;
import com.gruppodieci.farming4u.business.SavingFiles;
import com.gruppodieci.farming4u.business.Warning;

import java.util.Random;

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

        Warning warningRemove = null;
        for(Warning warning: RiepilogoFragment.warnings) {

            if(warning.getTagClicked()) {

                warningRemove = warning;

            }

        }

        if(warningRemove != null) {
            RiepilogoFragment.warnings.remove(warningRemove);
            SavingFiles.saveFile("fileWarnings", RiepilogoFragment.warnings);
        }
        else
            RiepilogoFragment.loadWarnings();

        disegnaCerchi(map);

        BottomNavigationMenu.setActiveFragment(this);

        return curaPiante;

    }

    private void disegnaCerchi(FrameLayout frameWarning) {

        frameWarning.removeAllViews();

        for(Warning warning:RiepilogoFragment.warnings){

            if( warning.getType().equals(Warning.CONCIMAZIONE) || warning.getType().equals(Warning.PESTICIDI) ) {


                CerchioView cerchioView=new CerchioView(getContext(),warning.getxPosition(),warning.getyPosition() * 2,warning.getSizeOfWarning(),warning.isSerious(),warning);


                map.setOnTouchListener( new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        Warning warningCliccato = frameCliccato(motionEvent);

                        if(warningCliccato != null) {

                            warningCliccato.setTagClicked(true);

                            Fragment problem = new ProblemInformationFragment();
                            BottomNavigationMenu.replaceFragment(R.id.fragmentContainer, problem);
                            BottomNavigationMenu.setActiveFragment(problem);

                            BasicActivity.getIstance().getSupportActionBar().hide();

                        }

                        return false;

                    }

                });

                frameWarning.addView(cerchioView);

            }

        }

        frameWarning.invalidate();

    }

    private Warning frameCliccato(MotionEvent event) {
        Warning warningCliccato=null;
        int action = event.getAction();
        if(action==MotionEvent.ACTION_DOWN){
            for(Warning warn:RiepilogoFragment.warnings){
                double xDiff=Math.abs(event.getX()-warn.getxPosition());
                double yDiff=Math.abs(event.getY()-warn.getyPosition() * 2);
                double hypot=Math.hypot(xDiff,yDiff);
                if (hypot<warn.getSizeOfWarning()){
                    warningCliccato=warn;
                    Log.d("DEBUG","Cerchio cliccato appartente al tipo "+warningCliccato.getType());
                }
            }
        }

        return warningCliccato;

    }

}
