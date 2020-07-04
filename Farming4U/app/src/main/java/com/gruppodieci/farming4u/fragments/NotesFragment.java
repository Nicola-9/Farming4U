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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.business.CustomAdapterNotes;
import com.gruppodieci.farming4u.business.Note;
import com.gruppodieci.farming4u.business.SavingFiles;

import java.util.ArrayList;

public class NotesFragment extends ListFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        note=new ArrayList<>();


    }

    private void instanziateNotes() {
        ArrayList<Note> noteSaved=(ArrayList<Note>)SavingFiles.loadFile("fileNotes");
        int i=1;
        for(Note nota:noteSaved){
            customAdapter.add(nota);
            Log.d("DEBUG","Nota  "+nota.getNota()+" , nr "+i++);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lista_note, container, false);
        listviewNote=view.findViewById(android.R.id.list);
        customAdapter = new CustomAdapterNotes(getContext(),R.layout.lista_note,note);
        Log.d("DEBUG","listview "+listviewNote);
        listviewNote.setAdapter(customAdapter);
        instanziateNotes();
        addButton=view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //your code for the particular button
                Log.d("DEBUG","add cliccato");
            }
        });

        return view;
    }









    private FloatingActionButton addButton;
    private View view;
    private ListView listviewNote;
    private ArrayList<Note> note;
    private CustomAdapterNotes customAdapter;
}
