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

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gruppodieci.farming4u.MainActivity;
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

        setToolbar();

        return view;
    }


    private void setToolbar(){
        ((MainActivity)getActivity()).showToolbarMenu(false);
        MaterialToolbar toolbar= MainActivity.getToolbar();
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle("Problemi attivi");
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
        ((MainActivity)getActivity()).showToolbarMenu(true);
        MaterialToolbar toolbar= MainActivity.getToolbar();
        toolbar.setNavigationIcon(null);

    }

    private View view;
    private ListView listviewNote;
    private ArrayList<Warning> warning;
    private CustomAdapterWarning customAdapter;
}
