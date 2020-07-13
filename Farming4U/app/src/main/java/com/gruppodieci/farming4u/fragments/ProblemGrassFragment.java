package com.gruppodieci.farming4u.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.business.Warning;

public class ProblemGrassFragment extends Fragment {

    View problemGrass;
    TextView grassPriority;
    FrameLayout grassPriorityColor;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        this.problemGrass = inflater.inflate(R.layout.fragment_problem_grass, container, false);





        /*
        grassPriority= this.problemGrass.findViewById(R.id.GrassPriority);

        grassPriorityColor=this.problemGrass.findViewById(R.id.GrassPriorityColor);

        Warning warningPressed = null;




        if (warningPressed.isSerious()) {
            grassPriority.setText("Priorità: alta");
            grassPriorityColor.setBackgroundColor(Color.parseColor("#F66565"));
        } else {
            grassPriority.setText("Priorità: normale");
            grassPriorityColor.setBackgroundColor(Color.parseColor("#EFF377"));
        }


         */



        return problemGrass;
    }
}