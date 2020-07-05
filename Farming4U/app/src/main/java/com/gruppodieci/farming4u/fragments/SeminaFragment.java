package com.gruppodieci.farming4u.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gruppodieci.farming4u.R;
import com.gruppodieci.farming4u.business.GroundStatusBusiness;

public class SeminaFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.riepilogo_fragment, container, false);

        // Inflate Fragment layout
        this.view = inflater.inflate(R.layout.semina_fragment, container, false);

        return this.view;
    }


}
