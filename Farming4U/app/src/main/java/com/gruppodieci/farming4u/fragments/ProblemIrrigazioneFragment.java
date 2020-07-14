package com.gruppodieci.farming4u.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.business.Warning;

public class ProblemIrrigazioneFragment extends Fragment {

    View problemIrrigazione;
    TextView informationPriority;
    FrameLayout framePriorityColor;
    ImageButton backButton;
    Switch risolvi;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.problemIrrigazione = inflater.inflate(R.layout.fragment_problem_irrigazione, container, false);

        backButton=this.problemIrrigazione.findViewById(R.id.infBackButton);

        informationPriority = this.problemIrrigazione.findViewById(R.id.irrigationPriority);

        framePriorityColor = this.problemIrrigazione.findViewById(R.id.irrigationPriorityColor);

        risolvi=this.problemIrrigazione.findViewById(R.id.switch1);




        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                for(Warning warning: RiepilogoFragment.warnings) {

                    warning.setTagClicked(false);

                }

                BasicActivity.getIstance().onBackPressed();

            }

        });

        Warning warningPressed = null;

        for(Warning warning: RiepilogoFragment.warnings) {

            if( warning.getTagClicked() ) {
                warningPressed = warning;
                break;
            }

        }

        if(warningPressed!=null) {

            if (warningPressed.isSerious()) {
                informationPriority.setText("Priorità: alta");
                framePriorityColor.setBackgroundColor(Color.parseColor("#F66565"));
            } else {
                informationPriority.setText("Priorità: normale");
                framePriorityColor.setBackgroundColor(Color.parseColor("#EFF377"));
            }
        }


        problemIrrigazione.invalidate();                      /* serve o no */

        return problemIrrigazione;
    }
}