package com.gruppodieci.farming4u.business;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.gruppodieci.farming4u.R;

import java.util.List;

public class CustomAdapterNotes extends ArrayAdapter<Note> {
    private int resource;
    private LayoutInflater inflater;
    private List<Note> notes;

    public CustomAdapterNotes(Context context, int resourceId, List<Note> notes) {
        super(context, resourceId, notes);
        resource = resourceId;
        inflater = LayoutInflater.from(context);
        this.notes=notes;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null) {
            v = inflater.inflate(R.layout.material_list_item_two_line, null);
        }

        Note note = getItem(position);

        TextView note_text= v.findViewById(R.id.warning_text);
        TextView scheduled_time = v.findViewById(R.id.warning_time);
        TextView alarm_time = v.findViewById(R.id.alarm_time);
        ImageView alarm_icon = v.findViewById(R.id.warning_icon);
        MaterialButton deleteButton=v.findViewById(R.id.deleteButton);

        note_text.setText(note.getNota());
        scheduled_time.setText(note.getData());
        boolean alarm=note.getDataSveglia()!=null?true:false;
        alarm_icon.setImageResource(R.drawable.ic_alarm_off);

        if(alarm){
            alarm_time.setText(note.getDataSveglia());
            alarm_icon.setImageResource(R.drawable.ic_alarm_on);
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //your code for the particular button
                Note nota=getItem(position);
                createDialog(getContext(),nota,position);
            }
        });



        return v;
    }


    public void createDialog(final Context context, Note nota, final int position){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Note nota=getItem(position);
                        notes.remove(nota);
                        notifyDataSetChanged();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        new MaterialAlertDialogBuilder(context)
                .setTitle("Attenzione")
                .setMessage("Stai per cancellare una nota, una volta cancellata non potrai recuperarla. Vuoi procedere?")
                .setNegativeButton("No",dialogClickListener)
                .setPositiveButton("Si",dialogClickListener)
                .show();
    }

}

