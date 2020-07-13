package com.gruppodieci.farming4u.fragments;

import android.annotation.SuppressLint;
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

    private TextView msg;

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

        msg = this.problemInformation.findViewById(R.id.msg);

        buttonResolve = this.problemInformation.findViewById(R.id.risolvi);

        buttonResolve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Warning warningRemove = null;
                for(Warning warning: RiepilogoFragment.warnings) {

                    if(warning.getTagClicked())
                        warningRemove = warning;

                }

                if(warningRemove != null)
                   ;

                BasicActivity.getIstance().onBackPressed();

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


                msg.setText("L'ultimo impiego di concime in questa zona risale a " + warningPressed.getDays() + "giorni fa.\n" +
                        priority +
                        "La quantità di concime richiesta è " + warningPressed.getProductQuantity() + " kg." +
                        "Assicurati di aver eseguito gli interventi richiesti prima di segnare il problema come risolto.");

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


                msg.setText("L'ultimo impiego di pesticidi in questa zona risale a " + warningPressed.getDays() + "giorni fa.\n" +
                        priority +
                        "La quantità di concime richiesta è " + warningPressed.getProductQuantity() + " l." +
                        "Assicurati di aver eseguito gli interventi richiesti prima di segnare il problema come risolto.");

            }

        }

        problemInformation.invalidate();

        return problemInformation;

    }

}
