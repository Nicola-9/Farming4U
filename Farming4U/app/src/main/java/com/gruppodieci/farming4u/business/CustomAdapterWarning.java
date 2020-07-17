package com.gruppodieci.farming4u.business;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.gruppodieci.farming4u.BottomNavigationMenu;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.fragments.GroundsFragment;
import com.gruppodieci.farming4u.fragments.ProblemGrassFragment;
import com.gruppodieci.farming4u.fragments.ProblemInformationFragment;
import com.gruppodieci.farming4u.fragments.ProblemIrrigazioneFragment;
import com.gruppodieci.farming4u.fragments.RiepilogoFragment;

import java.util.List;

public class CustomAdapterWarning extends ArrayAdapter<Warning> {
    private int resource;
    private LayoutInflater inflater;
    private List<Warning> warning;

    public CustomAdapterWarning(Context context, int resourceId, List<Warning> warning) {
            super(context, resourceId, warning);
            resource = resourceId;
            inflater = LayoutInflater.from(context);
            this.warning =warning;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
    		if (v == null) {
    			v = inflater.inflate(R.layout.item_warning, null);
    		}

            Warning warning = getItem(position);

        TextView warning_text= v.findViewById(R.id.warning_text);
        TextView warning_time = v.findViewById(R.id.warning_time);
        ImageView warning_icon = v.findViewById(R.id.warning_icon);
        MaterialCardView materialCardView=v.findViewById(R.id.cardviewWarning);
        RelativeLayout relativeLayoutWarning=v.findViewById(R.id.relativeLayoutWarningSingolo);

        warning_text.setText(warning.getWarning());
        warning_time.setText(warning.getData());
        warning_icon.setImageResource(R.drawable.ic_warning_giallo);
        materialCardView.setCardBackgroundColor(v.getResources().getColor(R.color.cardviewWarningGiallo));
        relativeLayoutWarning.setBackground(v.getResources().getDrawable(R.drawable.gradient_warning_giallo));
        if(warning.isSerious()){
            warning_icon.setImageResource(R.drawable.ic_warning_rosso);
            materialCardView.setCardBackgroundColor(v.getResources().getColor(R.color.cardviewWarningRosso));
            relativeLayoutWarning.setBackground(v.getResources().getDrawable(R.drawable.gradient_warning_rosso));


        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DEBUG","cliccata view in posizione"+position);
                Warning warningCliccato= RiepilogoFragment.warnings.get(position);
                if(warningCliccato.getType().equals(Warning.CONCIMAZIONE) || warningCliccato.getType().equals(Warning.PESTICIDI)){
                    BottomNavigationMenu.setPreviousFragment("home");
                    warningCliccato.setTagClicked(true);
                    Fragment problem = new ProblemInformationFragment();
                    BottomNavigationMenu.replaceFragment(problem,false);
                    BottomNavigationMenu.setActiveFragment(problem);
                    BasicActivity.getIstance().getSupportActionBar().hide();
                }
                else {
                    Fragment problem=null;

                    if(warningCliccato.getType().equals(Warning.IRRIGAZIONE)) {

                        problem = new ProblemIrrigazioneFragment();

                    }
                    else if(warningCliccato.getType().equals(Warning.ERBA)) {

                        problem = new ProblemGrassFragment();

                    }
                    BottomNavigationMenu.setPreviousFragment("home");
                    warningCliccato.setTagClicked(true);
                    BottomNavigationMenu.replaceFragment(problem,false);
                    BottomNavigationMenu.setActiveFragment(problem);
                    BasicActivity.getIstance().getSupportActionBar().hide();

                }
            }
        });

            return v;
    }



}

