package com.gruppodieci.farming4u.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.business.Warning;

public class ProblemInformationFragment extends Fragment {

    private View problemInformation;

    private TextView informationTitle;
    private TextView informationPriority;

    private FrameLayout framePriorityColor;
    private ImageView informationParameterImage;

    private TextView msg;

    private MaterialButton buttonResolve;

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

                informationTitle.setText("Carenza di\nconcime");

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

        warningPressed.setTagClicked(false);

        return problemInformation;

    }
}
