package com.gruppodieci.farming4u.business;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SavingFiles {

    public SavingFiles(Context context){
        this.context=context;
    }

    public static void saveFile(String fileName, Object object) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(object);
            os.close();
            fos.close();
        } catch (Exception e) {
            Log.d("DEBUG", "Problema salvataggio ");
            e.printStackTrace();
        }
    }


    public static Object loadFile(String fileName) {
        Object object=null;
        try {
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            object = is.readObject();
            is.close();
            fis.close();
        }
        catch (Exception e) {
            Log.d("DEBUG", "Problema caricamento ");
            e.printStackTrace();
        }
        return object;
    }

    private static Context context;
}
