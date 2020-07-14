package com.gruppodieci.farming4u.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.LeadingMarginSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.business.CerchioView;
import com.gruppodieci.farming4u.business.Warning;

public class ProblemInformationFragment extends Fragment {

    private View problemInformation;

    private TextView informationTitle;
    private TextView informationPriority;

    private FrameLayout framePriorityColor;
    private ImageView informationParameterImage;

    private TextView msg1;
    private TextView msg2;
    private TextView msg3;
    private TextView msg4;

    private MaterialButton buttonResolve;

    private ImageButton informationBackButton;

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        problemInformation = inflater.inflate(R.layout.fragment_problem_information, container, false);

        informationTitle = this.problemInformation.findViewById(R.id.informationTitle);
        informationPriority = this.problemInformation.findViewById(R.id.informationPriority);

        framePriorityColor = this.problemInformation.findViewById(R.id.framePriorityColor);
        informationParameterImage = this.problemInformation.findViewById(R.id.informationParameterImage);

        msg1 = this.problemInformation.findViewById(R.id.msg1);
        msg2 = this.problemInformation.findViewById(R.id.msg2);
        msg3 = this.problemInformation.findViewById(R.id.msg3);
        msg4 = this.problemInformation.findViewById(R.id.msg4);

        buttonResolve = this.problemInformation.findViewById(R.id.risolvi);

        buttonResolve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(problemInformation.getContext());
                materialAlertDialogBuilder.setMessage("Sei sicuro di voler risolvere il problema?");
                materialAlertDialogBuilder.setPositiveButton( "Sì", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

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

        informationBackButton = this.problemInformation.findViewById(R.id.informationBackButton);

        informationBackButton.setOnClickListener(new View.OnClickListener() {

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

        if(warningPressed != null) {

            if (warningPressed.getType().equals(Warning.CONCIMAZIONE)) {

                informationTitle.setText("Carenza di\nconcime");

                informationParameterImage.setBackgroundResource(R.drawable.icona_concimazione);

                String priority = "";
                if (warningPressed.isSerious()) {
                    informationPriority.setText("Priorità: alta");
                    priority = "É richiesto un intervento repentino.";
                    framePriorityColor.setBackgroundColor(Color.parseColor("#F66565"));
                } else {
                    informationPriority.setText("Priorità: normale");
                    priority = "É richiesto un intervento entro " + warningPressed.getDays() / 5 + "giorni.";
                    framePriorityColor.setBackgroundColor(Color.parseColor("#EFF377"));
                }

                msg1.setText("L'ultimo impiego di concime in questa zona risale a " + warningPressed.getDays() + "giorni fa.");
                msg2.setText(priority);
                msg3.setText("La quantità di concime richiesta è " + warningPressed.getProductQuantity() + " kg.");
                msg4.setText("Assicurati di aver eseguito gli interventi richiesti prima di segnare il problema come risolto.");

            } else if (warningPressed.getType().equals(Warning.PESTICIDI)) {

                informationTitle.setText("Carenza di\npesticidi");

                informationParameterImage.setBackgroundResource(R.drawable.icona_pesticidi);

                String priority = "";
                if (warningPressed.isSerious()) {
                    informationPriority.setText("Priorità: alta");
                    priority = "É richiesto un intervento repentino.";
                    framePriorityColor.setBackgroundColor(Color.parseColor("#F66565"));
                } else {
                    informationPriority.setText("Priorità: normale");
                    priority = "É richiesto un intervento entro " + warningPressed.getDays() / 5 + "giorni.";
                    framePriorityColor.setBackgroundColor(Color.parseColor("#EFF377"));
                }

                msg1.setText("L'ultimo impiego di pesticidi in questa zona risale a " + warningPressed.getDays() + "giorni fa.");
                msg2.setText(priority);
                msg3.setText("La quantità di concime richiesta è " + warningPressed.getProductQuantity() + " l.");
                msg4.setText("Assicurati di aver eseguito gli interventi richiesti prima di segnare il problema come risolto.");


            }

        }

        problemInformation.invalidate();

        return problemInformation;

    }
/*
    private void removeWarning(Warning warningRemove) {

        CuraPianteFragment.map.removeAllViews();
        RiepilogoFragment.warnings.remove(warningRemove);

        for(Warning warning:RiepilogoFragment.warnings){

            if( warning.getType().equals(Warning.CONCIMAZIONE) || warning.getType().equals(Warning.PESTICIDI) ) {

                CerchioView cerchioView = new CerchioView(getContext(), warning.getxPosition(), warning.getyPosition() * 2, warning.getSizeOfWarning(), warning.isSerious(), warning);
                CuraPianteFragment.map.addView(cerchioView);

            }

        }

    }*/

}
