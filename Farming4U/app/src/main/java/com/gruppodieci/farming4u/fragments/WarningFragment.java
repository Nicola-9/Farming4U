package com.gruppodieci.farming4u.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.business.CustomAdapterNotes;
import com.gruppodieci.farming4u.business.CustomAdapterWarning;
import com.gruppodieci.farming4u.business.Note;
import com.gruppodieci.farming4u.business.Warning;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class WarningFragment extends ListFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        warning =new ArrayList<>();


    }

    private void instanziateNotes() {
        boolean serious=false;
        for(int i=0;i<8;i++) {
            Warning warning=new Warning("Warning",serious,"data");
            customAdapter.add(warning);
            serious=!serious;
        }
        Log.d("DEBUG2","grandezza array note: "+ warning.size());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lista_note, container, false);
        listviewNote=view.findViewById(android.R.id.list);
        customAdapter = new CustomAdapterWarning(getContext(),R.layout.lista_warning, warning);
        Log.d("DEBUG","listview "+listviewNote);
        listviewNote.setAdapter(customAdapter);
        instanziateNotes();


        return view;
    }









    private View view;
    private ListView listviewNote;
    private ArrayList<Warning> warning;
    private CustomAdapterWarning customAdapter;
}
