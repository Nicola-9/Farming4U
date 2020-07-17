package com.gruppodieci.farming4u.fragments;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.gruppodieci.farming4u.BottomNavigationMenu;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.business.SavingFiles;
import com.gruppodieci.farming4u.business.Warning;

public class ProblemGrassFragment extends Fragment {

    View problemGrass;
    TextView grassPriority;
    FrameLayout grassPriorityColor;
    ImageButton back;
    Button resolve;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.problemGrass = inflater.inflate(R.layout.fragment_problem_grass, container, false);

        back=this.problemGrass.findViewById(R.id.informBackButton);

        resolve=this.problemGrass.findViewById(R.id.button_grass);

        resolve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(problemGrass.getContext());
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

                    }

                });

                materialAlertDialogBuilder.show();

            }

        });

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                for(Warning warning: RiepilogoFragment.warnings) {

                    warning.setTagClicked(false);

                }

                BasicActivity.getIstance().onBackPressed();

            }

        });

        grassPriority= this.problemGrass.findViewById(R.id.GrassPriority);

        grassPriorityColor=this.problemGrass.findViewById(R.id.GrassPriorityColor);

        Warning warningPressed = null;

        for(Warning warning: RiepilogoFragment.warnings) {

            if( warning.getTagClicked() ) {
                warningPressed = warning;
                break;
            }

        }

        if(warningPressed!=null) {
            if (warningPressed.isSerious()) {
                grassPriority.setText("Priorità: alta");
                grassPriorityColor.setBackgroundColor(Color.parseColor("#F66565"));
            } else {
                grassPriority.setText("Priorità: normale");
                grassPriorityColor.setBackgroundColor(Color.parseColor("#EFF377"));
            }
        }

        problemGrass.invalidate();           /*serve o no */

        return problemGrass;
    }
}