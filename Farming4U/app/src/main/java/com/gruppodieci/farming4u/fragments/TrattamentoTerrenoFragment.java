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
import com.gruppodieci.farming4u.business.Warning;


public class TrattamentoTerrenoFragment extends Fragment {

    View trattamentoTerreno;
    FrameLayout map;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.trattamentoTerreno = inflater.inflate(R.layout.fragment_trattamento_terreno, container, false);

        this.map = this.trattamentoTerreno.findViewById(R.id.map);

        RiepilogoFragment.loadWarnings();

        disegnaCerchi(map);

        return trattamentoTerreno;

    }


    void disegnaCerchi(FrameLayout frameWarning) {

        frameWarning.removeAllViews();

        for(Warning warning:RiepilogoFragment.warnings){

            if( warning.getType().equals(Warning.IRRIGAZIONE) || warning.getType().equals(Warning.ERBA) ) {


                CerchioView cerchioView=new CerchioView(getContext(),warning.getxPosition(),warning.getyPosition() * 2,warning.getSizeOfWarning(),warning.isSerious(),warning);


                map.setOnTouchListener( new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        Warning warningCliccato = frameCliccato(motionEvent);



                        if(warningCliccato != null) {

                            Fragment problem=null;

                            if(warningCliccato.getType().equals(Warning.IRRIGAZIONE)) {

                                problem = new ProblemIrrigazioneFragment();

                            }
                            else if(warningCliccato.getType().equals(Warning.ERBA)) {

                                problem = new ProblemGrassFragment();

                            }
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
                    warningCliccato.setTagClicked(true);
                    Log.d("DEBUG","Cerchio cliccato appartente al tipo "+warningCliccato.getType());
                }
            }
        }

        return warningCliccato;

    }
}