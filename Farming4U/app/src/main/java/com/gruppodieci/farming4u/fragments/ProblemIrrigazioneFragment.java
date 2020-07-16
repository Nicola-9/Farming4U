package com.gruppodieci.farming4u.fragments;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.gruppodieci.farming4u.BottomNavigationMenu;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.business.SavingFiles;
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

        risolvi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(problemIrrigazione.getContext());
                    materialAlertDialogBuilder.setMessage("Sei sicuro di voler risolvere il problema?");
                    materialAlertDialogBuilder.setPositiveButton( "Sì", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

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

                            BasicActivity.getIstance().onBackPressed();

                        }

                    } );
                    materialAlertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            risolvi.setChecked(false);

                        }

                    });
                    materialAlertDialogBuilder.setCancelable(false);

                    materialAlertDialogBuilder.show();

                }


            }

        });




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