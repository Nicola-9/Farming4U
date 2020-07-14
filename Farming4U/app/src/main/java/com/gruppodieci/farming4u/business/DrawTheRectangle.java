package com.gruppodieci.farming4u.business;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.gruppodieci.farming4u.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;


public class DrawTheRectangle extends View{

    private boolean drawRectangle = false;
    private Paint paint;
    FrameLayout frame;
    private float inizio_x;
    private float inizio_y;
    private float fine_x;
    private float fine_y;
    private Random casuale;
    private ArrayList<TerreniColtivati> terra;

    public  DrawTheRectangle(Context context){
        super(context);
        paint = new Paint();
    }


    public DrawTheRectangle(Context context, FrameLayout frameLayout) {
        super(context);
        frame = frameLayout;
        casuale =  new Random();
        paint = new Paint();

        frameLayout.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, @NotNull MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        drawRectangle = true;
                        inizio_x = event.getX();
                        inizio_y = event.getY();
                        fine_x = event.getX();
                        fine_y = event.getY();
                        invalidate();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        fine_x = event.getX();
                        fine_y = event.getY();
                        invalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        drawRectangle = false;
                        break;
                }
                return true;
            }


        });
    }

    protected void onDraw(Canvas canvas) {
        if(drawRectangle) {
            paint.setColor(ContextCompat.getColor(getContext(), R.color.colorCardviewNote));
            paint.setAlpha(180);
            canvas.drawRect(inizio_x, inizio_y, fine_x, fine_y, paint);
        }
    }

    public void disegnaRettangoloSel (float inizio_x, float inizio_y, float fine_x, float fine_y){
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorCardviewNote));//R.color.menuitem_color
        paint.setAlpha(180);
        Canvas canvas = new Canvas();
        canvas.drawRect(inizio_x, inizio_y, fine_x, fine_y, paint);
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
