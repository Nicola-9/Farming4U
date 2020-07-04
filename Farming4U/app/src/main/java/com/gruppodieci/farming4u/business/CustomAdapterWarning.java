package com.gruppodieci.farming4u.business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.gruppodieci.farming4u.R;

import java.util.List;

public class CustomAdapterWarning extends ArrayAdapter<Warning> {
    private int resource;
    private LayoutInflater inflater;
    private List<Warning> warning;

    public CustomAdapterWarning(Context context, int resourceId, List<Warning> warning) {
            super(context, resourceId, warning);
            resource = resourceId;
            inflater = LayoutInflater.from(context);
            this.warning =warning;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
    		if (v == null) {
    			v = inflater.inflate(R.layout.item_warning, null);
    		}

            Warning warning = getItem(position);

        TextView warning_text= v.findViewById(R.id.warning_text);
        TextView warning_time = v.findViewById(R.id.warning_time);
        ImageView warning_icon = v.findViewById(R.id.warning_icon);

        warning_text.setText(warning.getWarning());
        warning_time.setText(warning.getData());
        warning_icon.setImageResource(R.drawable.ic_warning_giallo);

        if(warning.isSerious()){
            warning_icon.setImageResource(R.drawable.ic_warning_rosso);
        }

            return v;
    }
}

