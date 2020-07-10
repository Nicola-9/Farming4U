package com.gruppodieci.farming4u.business;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SavingFilesSeminaTerreni {

    private ArrayList<TerreniColtivati> terreniFile;

    public SavingFilesSeminaTerreni() {
    }


    public void saveTerreni(ArrayList<TerreniColtivati> data, Context context) {
        try {
            String fileName = "terreniDaColtivare.txt";
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(data);
            os.close();
            fos.close();
        } catch (IOException e) {
            Log.e("Exception", "Scrittura sul file fallita: " + e.toString());
        }
    }

    public ArrayList<TerreniColtivati> readFromFileTerreni(Context context) {

        try {
            String fileName = "terreniDaColtivare.txt";
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            terreniFile = (ArrayList<TerreniColtivati>) is.readObject();
            is.close();
            fis.close();

            if (terreniFile == null) {
                try {
                    FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                    ObjectOutputStream os = new ObjectOutputStream(fos);
                    os.close();
                    fos.close();
                } catch (IOException e) {
                    Log.e("Exception", "Scrittura sul file fallita: " + e.toString());
                }
                return  terreniFile;
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return terreniFile;
    }


    public void clearOneTerreni(TerreniColtivati t, Context context) {
        try {
            String fileName = "terreniDaColtivare.txt";
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            terreniFile = (ArrayList<TerreniColtivati>) is.readObject();
            TerreniColtivati terra = t;

            for(int i = 0; i < terreniFile.size(); i++){
                if(terra.equals(terreniFile.get(i))) {
                    terreniFile.remove(i);
                }
                else if(terra.getxPositionInizio() == terreniFile.get(i).getxPositionInizio()){
                    if(terra.getyPositionInizio() == terreniFile.get(i).getyPositionInizio()){
                        if(terra.getxPositionFine() == terreniFile.get(i).getxPositionFine()){
                            if(terra.getyPositionFine() == terreniFile.get(i).getyPositionFine()){
                                terreniFile.remove(i);
                            }
                        }
                    }
                }
            }

            is.close();
            fis.close();

            if (terreniFile == null) {

            }
            else{
                try {
                    String file = "terreniDaColtivare.txt";
                    FileOutputStream fos = context.openFileOutput(file, Context.MODE_PRIVATE);
                    ObjectOutputStream os = new ObjectOutputStream(fos);
                    os.writeObject(terreniFile);
                    os.close();
                    fos.close();
                } catch (IOException e) {
                    Log.e("Exception", "Scrittura sul file fallita: " + e.toString());
                }
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void clearAllTerreni(Context context) {
        //String s = context.getFilesDir().getAbsolutePath();
        try {
            String fileName = "terreniDaColtivare.txt";
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            String s = context.getFilesDir().getAbsolutePath();
            s = s + "/terreniDaColtivare.txt";

            File fdelete = new File(s);
            if (fdelete.exists()) {
                if (fdelete.delete()) {
                    System.out.println("file Deleted :" + s);
                } else {
                    System.out.println("file not Deleted :" + s);
                }
            }

            os.close();
            fos.close();
        } catch (IOException e) {
            Log.e("Exception", "pulizia files fallita: " + e.toString());
        }
    }
}