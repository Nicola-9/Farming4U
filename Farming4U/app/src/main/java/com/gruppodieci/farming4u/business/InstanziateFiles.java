package com.gruppodieci.farming4u.business;

import android.util.Log;

import java.util.ArrayList;

public class InstanziateFiles {

    public static void instanziateFiles(){
        saveNotes();
    }

    private static void saveNotes(){
        Object obj=SavingFiles.loadFile("fileNotes");
        if (obj!=null)
                return;
        ArrayList<Note> note= new ArrayList<>();
        boolean sveglia=false;
        for(int i=0;i<8;i++) {
            Note nota = new Note("Pianta il BIG PERLON!", "19:45");
            if(sveglia)
                nota.setDataSveglia("15:00");
            note.add(nota);
            sveglia=!sveglia;
        }
        Log.d("DEBUG2","grandezza array note: "+note.size());
        SavingFiles.saveFile("fileNotes",note);
    }
}
