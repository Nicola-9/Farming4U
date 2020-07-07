package com.gruppodieci.farming4u.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gruppodieci.farming4u.BottomNavigationMenu;
import com.gruppodieci.farming4u.activity.BasicActivity;
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
        customAdapter.clear();
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
                Fragment fragment = new NewNoteFragment();

                BottomNavigationMenu.replaceFragment(fragment,true);
                BottomNavigationMenu.setActiveFragment(fragment);
            }
        });

        setToolbar();
        return view;
    }

    private void setToolbar() {

            ((BasicActivity)getActivity()).showToolbarMenu(false);
            MaterialToolbar toolbar= BasicActivity.getToolbar();
            toolbar.setVisibility(View.VISIBLE);
            toolbar.setTitle("Note");
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









    private FloatingActionButton addButton;
    private View view;
    private ListView listviewNote;
    private ArrayList<Note> note;
    private CustomAdapterNotes customAdapter;
}
