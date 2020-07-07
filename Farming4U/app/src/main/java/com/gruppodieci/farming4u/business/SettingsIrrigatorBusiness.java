package com.gruppodieci.farming4u.business;

import android.app.TimePickerDialog;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.gruppodieci.farming4u.BottomNavigationMenu;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.fragments.RiepilogoFragment;
import com.gruppodieci.farming4u.fragments.SettingsAddIrrigator;

import java.util.Calendar;

public class SettingsIrrigatorBusiness {

    public SettingsIrrigatorBusiness(ExtendedFloatingActionButton hourButton, TextInputEditText portataIrrigator,
                                     TextInputEditText maxRadius, MaterialCheckBox ferialDays, MaterialCheckBox evenDays,
                                     MaterialCheckBox oddDays, MaterialButton dismissButton, MaterialButton saveButton,
                                     FloatingActionButton addIrrigatorButton) {

        this.hourButton = hourButton;
        this.portataIrrigator = portataIrrigator;
        this.maxRadius = maxRadius;
        this.ferialDays = ferialDays;
        this.evenDays = evenDays;
        this.oddDays = oddDays;
        this.dismissButton = dismissButton;
        this.saveButton = saveButton;
        this.addIrrigatorButton = addIrrigatorButton;
    }

    public void setonHourButtonClick(){
        this.hourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });
    }

    public void setOnSaveButtonClick(){
        this.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomNavigationMenu.replaceFragment(new RiepilogoFragment());
            }
        });
    }

    public void setOnDismissButtonClick(){
        this.dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hourButtonT = "Imposta ora";
                Drawable icon = BottomNavigationMenu.getInstance().getResources().getDrawable(R.drawable.watch_icon, null);

                hourButton.setIcon(icon);
                hourButton.setText(hourButtonT);

                portataIrrigator.setText(null);
                maxRadius.setText(null);

                ferialDays.setChecked(false);
                evenDays.setChecked(false);
                oddDays.setChecked(false);

                portataIrrigator.setEnabled(false);
                maxRadius.setEnabled(false);

                portataIrrigator.setEnabled(true);
                maxRadius.setEnabled(true);
            }
        });
    }

    public void onAddButtonClick(){
        this.addIrrigatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment addIrrigator = new SettingsAddIrrigator();

                BottomNavigationMenu.setActiveFragment(addIrrigator);
                BottomNavigationMenu.replaceFragment(addIrrigator, true);
            }
        });
    }

    private void showTimePicker(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minutes = calendar.get(Calendar.MINUTE);

        this.timePicker = new TimePickerDialog(BasicActivity.getBasicActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        Drawable icon = BottomNavigationMenu.getInstance().getResources()
                                                            .getDrawable(R.drawable.ic_baseline_alarm_on_24, null);
                        hourButton.setIcon(icon);

                        String hourTime = "";
                        String minutesTime = "";

                        if(i < 10){
                            hourTime = "0" + String.valueOf(i);
                        } else {
                            hourTime = String.valueOf(i);
                        }

                        if(i1 < 10){
                            minutesTime = "0" + String.valueOf(i1);
                        } else {
                            minutesTime = String.valueOf(i1);
                        }
                        String newTextHourButton = hourTime + ":" + minutesTime;

                        hourButton.setText(newTextHourButton);
                    }
                }, hour, minutes, true);

        timePicker.show();
    }

    private ExtendedFloatingActionButton hourButton;
    private TextInputEditText portataIrrigator;
    private TextInputEditText maxRadius;
    private MaterialCheckBox ferialDays;
    private MaterialCheckBox evenDays;
    private MaterialCheckBox oddDays;
    private MaterialButton dismissButton;
    private MaterialButton saveButton;
    private FloatingActionButton addIrrigatorButton;
    private TimePickerDialog timePicker;
}
