package com.gruppodieci.farming4u.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.gruppodieci.farming4u.BottomNavigationMenu;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.business.GroundStatusBusiness;
import com.gruppodieci.farming4u.business.SettingsIrrigatorBusiness;

public class SettingsIrrigator extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate Fragment layout
        this.settingsIrrigator = inflater.inflate(R.layout.irrigator_settings, container, false);

        // Find all components
        this.hourButton = this.settingsIrrigator.findViewById(R.id.setHourButton);
        this.portataIrrigator = this.settingsIrrigator.findViewById(R.id.portataIrrigator);
        this.maxRadius = this.settingsIrrigator.findViewById(R.id.raggioIrrigator);
        this.ferialDays = this.settingsIrrigator.findViewById(R.id.checkboxFerialDays);
        this.evenDays = this.settingsIrrigator.findViewById(R.id.checkboxEvenDays);
        this.oddDays = this.settingsIrrigator.findViewById(R.id.checkboxOddDays);
        this.dismissButton = this.settingsIrrigator.findViewById(R.id.irrigatorSettingButtonDismiss);
        this.saveButton = this.settingsIrrigator.findViewById(R.id.irrigatorSettingButtonSave);
        this.addIrrigatorButton = this.settingsIrrigator.findViewById(R.id.newIrrigatorButton);

        // Initialize business variable
        this.business = new SettingsIrrigatorBusiness(this.hourButton, this.portataIrrigator, this.maxRadius, this.ferialDays,
                                                        this.evenDays, this.oddDays, this.dismissButton, this.saveButton,
                                                        this.addIrrigatorButton);

        MaterialToolbar toolbar = BasicActivity.getToolbar();

        toolbar.setVisibility(View.VISIBLE);

        ((BasicActivity) getActivity()).showToolbarMenu(false);

        toolbar.setTitle("Irrigatori");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        this.business.setonHourButtonClick();
        this.business.setOnDismissButtonClick();
        this.business.setOnSaveButtonClick();
        this.business.onAddButtonClick();

        return this.settingsIrrigator;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((BasicActivity) getActivity()).showToolbarMenu(true);
        MaterialToolbar toolbar = BasicActivity.getToolbar();
        toolbar.setNavigationIcon(null);
        toolbar.setNavigationOnClickListener(null);
    }

    private View settingsIrrigator;
    private ExtendedFloatingActionButton hourButton;
    private TextInputEditText portataIrrigator;
    private TextInputEditText maxRadius;
    private MaterialCheckBox ferialDays;
    private MaterialCheckBox evenDays;
    private MaterialCheckBox oddDays;
    private MaterialButton dismissButton;
    private MaterialButton saveButton;
    private FloatingActionButton addIrrigatorButton;
    private SettingsIrrigatorBusiness business;
}
