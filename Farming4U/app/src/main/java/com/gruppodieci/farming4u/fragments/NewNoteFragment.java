package com.gruppodieci.farming4u.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.business.MonthConverter;
import com.gruppodieci.farming4u.business.Note;
import com.gruppodieci.farming4u.business.SavingFiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class NewNoteFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lunghezza=0;
        data=LocalDateTime.now();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.view = inflater.inflate(R.layout.new_note, container, false);

        textviewDataNota=view.findViewById(R.id.textviewDataNota);
        Log.d("DEBUG","textview null? "+textviewDataNota);
        textInputEditTextNota=view.findViewById(R.id.textInputEditTextNota);
        returnButton=view.findViewById(R.id.returnButton);
        saveButton=view.findViewById(R.id.saveButton);
        buttonAggiungiPromemoria=view.findViewById(R.id.buttonAggiungiPromemoria);
        setDataNota();
        setCounterCaratteri();
        setAggiungiPromemoriaListener();

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Note> noteSaved=(ArrayList<Note>)SavingFiles.loadFile("fileNotes");
                Note newNote=new Note();
                newNote.setNota(textInputEditTextNota.getText().toString());
                String data=textviewDataNota.getText().toString();
                data=data.substring(0,data.indexOf("|")-1);
                newNote.setData(data);
                String sveglia=buttonAggiungiPromemoria.getText().toString();
                if (!sveglia.equalsIgnoreCase("Aggiungi\npromemoria")) {
                    sveglia.replace("\n", " ");
                    newNote.setDataSveglia(sveglia);
                }
                noteSaved.add(newNote);
                Log.d("DEBUG","Notesaved "+newNote.getDataSveglia());
                SavingFiles.saveFile("fileNotes",noteSaved);

                getActivity().onBackPressed();
            }
        });

        setToolbar();
        return view;
    }

    private void setAggiungiPromemoriaListener() {
        buttonAggiungiPromemoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String testo=buttonAggiungiPromemoria.getText().toString();
                Log.d("DEBUG","testo buttonAggiungiPromemoria: "+testo);
                if(testo.equalsIgnoreCase("Aggiungi\npromemoria")){
                    //allarme spento, si deve inserire data
                    Log.d("DEBUG","Allarme disattivato");

                    dialogDateTime();


                }
                else{
                    Log.d("DEBUG","Allarme attivato");
                    Drawable icona = getResources().getDrawable(R.drawable.ic_baseline_alarm_off_24);
                    buttonAggiungiPromemoria.setIcon(icona);
                    buttonAggiungiPromemoria.setText("Aggiungi\npromemoria");
                }


            }
        });
    }

    private void dialogDateTime() {
        final Calendar cldr = Calendar.getInstance();
        final int hour = cldr.get(Calendar.HOUR_OF_DAY);
        final int minutes = cldr.get(Calendar.MINUTE);
        final int day = cldr.get(Calendar.DAY_OF_MONTH);
        final int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // time picker dialog

        DatePickerDialog datePicker = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
                        Log.d("DEBUG","Date : "+day+" "+month+" "+year);

                        TimePickerDialog picker = new TimePickerDialog(getContext(),
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                        Log.d("DEBUG","Date and time: "+dayOfMonth+" "+ (monthOfYear+1)+" "+year+ " "+sHour+" "+sMinute);
                                        Drawable icona = getResources().getDrawable(R.drawable.ic_baseline_alarm_on_24);
                                        buttonAggiungiPromemoria.setIcon(icona);
                                        buttonAggiungiPromemoria.setText(dayOfMonth+" "+MonthConverter.getMonth(monthOfYear+1)+" "+year+"\n"+sHour+":"+sMinute);
                                    }
                                }, hour, minutes, true);
                        picker.show();
                    }
                }, year, month, day);
        datePicker.show();
    }


    private void setCounterCaratteri() {
        textInputEditTextNota.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                lunghezza=textInputEditTextNota.getText().toString().length();
                Log.d("DEBUG","Lunghezza edittext "+lunghezza);

                setDataNota();
                return false;
            }
        });
        textInputEditTextNota.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lunghezza=textInputEditTextNota.getText().toString().length();
                Log.d("DEBUG","Lunghezza edittext "+lunghezza);

                setDataNota();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    private void setDataNota(){
        textviewDataNota.setText(""+data.getDayOfMonth()+" "+MonthConverter.getMonth(data.getMonthValue())+" "+ data.getYear()+" "+data.getHour() +":"+data.getMinute()+" | "+lunghezza+" caratteri");
    }

    private void setToolbar() {
        ((BasicActivity) getActivity()).showToolbarMenu(false);
        MaterialToolbar toolbar = BasicActivity.getToolbar();
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle("Nuova nota");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((BasicActivity)getActivity()).showToolbarMenu(true);
        MaterialToolbar toolbar= BasicActivity.getToolbar();
        toolbar.setNavigationIcon(null);

    }


    private ExtendedFloatingActionButton buttonAggiungiPromemoria;
    private LocalDateTime data;
    private int lunghezza;
    private MaterialButton saveButton;
    private MaterialButton returnButton;
    private TextInputEditText textInputEditTextNota;
    private TextView textviewDataNota;
    private View view;
}
