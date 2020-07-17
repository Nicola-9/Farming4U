package com.gruppodieci.farming4u.business;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.core.content.ContextCompat;

import com.gruppodieci.farming4u.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class DrawExistingRectangle extends View {

    private boolean drawRectangle = false;
    private Paint paint;
    FrameLayout frame;
    private float inizio_x;
    private float inizio_y;
    private float fine_x;
    private float fine_y;
    private Random casuale;
    private ArrayList<TerreniColtivati> terra;

    public DrawExistingRectangle(Context context, FrameLayout frameLayout) {
        super(context);
        frame = frameLayout;
        paint = new Paint();
    }

    public DrawExistingRectangle(Context context, FrameLayout frameLayout, ArrayList<TerreniColtivati> t) {
        super(context);
        frame = frameLayout;
        paint = new Paint();
        terra = t;
    }

    protected void onDraw(Canvas canvas) {
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorCardviewNote));
        paint.setStyle(Paint.Style.FILL);
        if(terra != null){
            for(TerreniColtivati t : terra) {
                paint.setColor(ContextCompat.getColor(getContext(), R.color.colorCardviewNote));
                paint.setAlpha(180);
              canvas.drawRect(t.getxPositionInizio(), t.getyPositionInizio(), t.getxPositionFine(), t.getyPositionFine(), paint);


                if(t.getType_of_cultivation() == 1) {
                    Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.icona_albero);
                    float y = t.getyPositionFine() + (t.getyPositionInizio() - t.getyPositionFine())/2;
                    float x = t.getxPositionFine() + (t.getxPositionInizio() - t.getxPositionFine())/2;
                    y = y -(b.getWidth()/2);
                    x = x -(b.getHeight()/2);
                    canvas.drawBitmap(b, x, y, paint);
                }
                else if(t.getType_of_cultivation() == 2) {
                    Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.icona_ciliegia);
                    float y = t.getyPositionFine() + (t.getyPositionInizio() - t.getyPositionFine())/2;
                    float x = t.getxPositionFine() + (t.getxPositionInizio() - t.getxPositionFine())/2;
                    y = y -(b.getWidth()/2);
                    x = x -(b.getHeight()/2);
                    canvas.drawBitmap(b, x, y, paint);
                }
                else if(t.getType_of_cultivation() == 3) {
                    Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.icona_mela);
                    float y = t.getyPositionFine() + (t.getyPositionInizio() - t.getyPositionFine())/2;
                    float x = t.getxPositionFine() + (t.getxPositionInizio() - t.getxPositionFine())/2;
                    y = y -(b.getWidth()/2);
                    x = x -(b.getHeight()/2);
                    canvas.drawBitmap(b, x, y, paint);
                }
                else if(t.getType_of_cultivation() == 4) {
                    Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.icona_patata);
                    float y = t.getyPositionFine() + (t.getyPositionInizio() - t.getyPositionFine())/2;
                    float x = t.getxPositionFine() + (t.getxPositionInizio() - t.getxPositionFine())/2;
                    y = y -(b.getWidth()/2);
                    x = x -(b.getHeight()/2);
                    canvas.drawBitmap(b, x, y, paint);
                }
                else if(t.getType_of_cultivation() == 5) {
                    Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.icona_kiwi);
                    float y = t.getyPositionFine() + (t.getyPositionInizio() - t.getyPositionFine())/2;
                    float x = t.getxPositionFine() + (t.getxPositionInizio() - t.getxPositionFine())/2;
                    y = y -(b.getWidth()/2);
                    x = x -(b.getHeight()/2);
                    canvas.drawBitmap(b, x, y, paint);
                }
                else if(t.getType_of_cultivation() == 6) {
                    Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.icona_piselli);
                    float y = t.getyPositionFine() + (t.getyPositionInizio() - t.getyPositionFine())/2;
                    float x = t.getxPositionFine() + (t.getxPositionInizio() - t.getxPositionFine())/2;
                    y = y -(b.getWidth()/2);
                    x = x -(b.getHeight()/2);
                    canvas.drawBitmap(b, x, y, paint);
                }
                else if(t.getType_of_cultivation() == 7) {
                    Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.icona_uva);
                    float y = t.getyPositionFine() + (t.getyPositionInizio() - t.getyPositionFine())/2;
                    float x = t.getxPositionFine() + (t.getxPositionInizio() - t.getxPositionFine())/2;
                    y = y -(b.getWidth()/2);
                    x = x -(b.getHeight()/2);
                    canvas.drawBitmap(b, x, y, paint);
                }



               /* System.out.println("vai vai vai vai"+canvas.getWidth());
                //super.onDraw(canvas);
                Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.icona_albero);

                canvas.drawBitmap(b, t.getxPositionInizio(), t.getyPositionInizio(), paint);*/
            }
        }
        else{

        }
    }


    public float getInizio_x() {
        return inizio_x;
    }

    public void setInizio_x(float inizio_x) {
        this.inizio_x = inizio_x;
    }

    public float getInizio_y() {
        return inizio_y;
    }

    public void setInizio_y(float inizio_y) {
        this.inizio_y = inizio_y;
    }

    public float getFine_x() {
        return fine_x;
    }

    public void setFine_x(float fine_x) {
        this.fine_x = fine_x;
    }

    public float getFine_y() {
        return fine_y;
    }

    public void setFine_y(float fine_y) {
        this.fine_y = fine_y;
    }

}