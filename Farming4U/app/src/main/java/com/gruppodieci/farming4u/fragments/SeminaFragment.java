package com.gruppodieci.farming4u.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.gruppodieci.farming4u.R;

public class SeminaFragment extends Fragment {

    private View view;
    private TextInputEditText input_text;
    private ImageButton img1;
    private ImageButton img2;
    private ImageButton img3;
    private ImageButton img4;
    private ImageButton img5;
    private ImageButton img6;
    private ImageButton img7;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.riepilogo_fragment, container, false);

        // Inflate Fragment layout
        this.view = inflater.inflate(R.layout.semina_fragment, container, false);

        this.input_text = this.view.findViewById(R.id.input_text);
        img1 = this.view.findViewById(R.id.icona1);
        img2 = this.view.findViewById(R.id.icona2);
        img3 = this.view.findViewById(R.id.icona3);
        img4 = this.view.findViewById(R.id.icona4);
        img5 = this.view.findViewById(R.id.icona5);
        img6 = this.view.findViewById(R.id.icona6);
        img7 = this.view.findViewById(R.id.icona7);


        input_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString().trim().toLowerCase();
                if (s.equals("k") || s.equals("ki") || s.equals("kiw") || s.equals("kiwi")) {
                    img1.setVisibility(View.GONE);
                    img2.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    img4.setVisibility(View.GONE);
                    img5.setVisibility(View.VISIBLE);
                    img6.setVisibility(View.GONE);
                    img7.setVisibility(View.GONE);
                }
                else if (s.equals("p") || s.equals("pi") || s.equals("pis") || s.equals("pise") || s.equals("pisel") || s.equals("pisell") || s.equals("piselli")) {
                    img1.setVisibility(View.GONE);
                    img2.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    img4.setVisibility(View.GONE);
                    img5.setVisibility(View.GONE);
                    img6.setVisibility(View.VISIBLE);
                    img7.setVisibility(View.GONE);
                }
                else if (s.equals("u") || s.equals("uv") || s.equals("uva")) {
                    img1.setVisibility(View.GONE);
                    img2.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    img4.setVisibility(View.GONE);
                    img5.setVisibility(View.GONE);
                    img6.setVisibility(View.GONE);
                    img7.setVisibility(View.VISIBLE);
                }
                else {
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.VISIBLE);
                    img3.setVisibility(View.VISIBLE);
                    img4.setVisibility(View.VISIBLE);
                    img5.setVisibility(View.GONE);
                    img6.setVisibility(View.GONE);
                    img7.setVisibility(View.GONE);
                }
            }
        });


        return this.view;
    }

}
