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

        Note nota = new Note("Controlla la coltivazione di uva Big Perlon", "2 Luglio 2020\n19:45");
        nota.setDataSveglia("15 Luglio 2020\n23:35");
        note.add(nota);

        nota = new Note("Aggiusta la recinzione danneggiata dal vento", "3 Luglio 2020\n15:37");
        note.add(nota);

        nota = new Note("Porta a riparare il tagliaerba", "2 Luglio 2020\n15:37");
        nota.setDataSveglia("3 Agosto 2020\n14:30");
        note.add(nota);

        Log.d("DEBUG2","grandezza array note: "+note.size());
        SavingFiles.saveFile("fileNotes",note);
    }
}
