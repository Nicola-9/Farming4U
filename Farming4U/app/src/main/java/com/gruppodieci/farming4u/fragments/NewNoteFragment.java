package com.gruppodieci.farming4u.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.business.AlertDialog;
import com.gruppodieci.farming4u.business.MonthConverter;

import java.time.LocalDateTime;

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
        setDataNota();

        textInputEditTextNota.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                lunghezza=textInputEditTextNota.getText().toString().length();
                setDataNota();
                return false;
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.createDialog(getContext(),"Sei sicuro di voler salvare?","Ma proprio sicuro sicuro?","Dai salva","Annulla stronzo");
            }
        });


        return view;
    }


    private void setDataNota(){
        textviewDataNota.setText(""+data.getDayOfMonth()+" "+MonthConverter.getMonth(data.getMonthValue())+" "+ data.getYear()+" "+data.getHour() +":"+data.getMinute()+" | "+lunghezza+" caratteri");
    }


    private LocalDateTime data;
    private int lunghezza;
    private MaterialButton saveButton;
    private MaterialButton returnButton;
    private TextInputEditText textInputEditTextNota;
    private TextView textviewDataNota;
    private View view;
}
