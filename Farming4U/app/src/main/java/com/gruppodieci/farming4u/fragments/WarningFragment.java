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

import com.gruppodieci.farming4u.activity.BasicActivity;
import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.business.CustomAdapterWarning;
import com.gruppodieci.farming4u.business.SavingFiles;

import com.gruppodieci.farming4u.business.Warning;

import java.util.ArrayList;

public class WarningFragment extends ListFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        warnings =new ArrayList<>();
        loadWarnings();


    }

    private void fillCustomAdapter(){
        if(warningSaved!=null) {
            for (Warning warning : warningSaved) {
                customAdapter.add(warning);
            }
        }
    }

    private void instanziateNotes() {
        boolean serious=false;
        for(int i=0;i<8;i++) {
            Warning warning=new Warning("Warning",serious,"data");
            customAdapter.add(warning);
            serious=!serious;
        }
        Log.d("DEBUG2","grandezza array note: "+ warnings.size());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lista_warning, container, false);
        listviewWarning =view.findViewById(android.R.id.list);
        customAdapter = new CustomAdapterWarning(getContext(),R.layout.lista_warning, warnings);
        Log.d("DEBUG","listview "+ listviewWarning);
        listviewWarning.setAdapter(customAdapter);


        setToolbar();
        fillCustomAdapter();


        return view;
    }

    private void loadWarnings(){
        warningSaved=(ArrayList<Warning>) SavingFiles.loadFile("fileWarnings");
    }

    private void setToolbar(){
        ((BasicActivity)getActivity()).showToolbarMenu(false);
        MaterialToolbar toolbar= BasicActivity.getToolbar();
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
        ((BasicActivity)getActivity()).showToolbarMenu(true);
        MaterialToolbar toolbar= BasicActivity.getToolbar();
        toolbar.setNavigationIcon(null);

    }
    private ArrayList<Warning> warningSaved;
    private View view;
    private ListView listviewWarning;
    private ArrayList<Warning> warnings;
    private CustomAdapterWarning customAdapter;
}
