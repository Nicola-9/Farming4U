package com.gruppodieci.farming4u.business;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.gruppodieci.farming4u.R;

public class CerchioView extends View {
    private float x,y;
    private int size;
    private boolean isSerious;
    private int actualSize;
    private boolean animation;
    private Paint fill;
    private String type;


    public CerchioView(Context context, float x, float y,int size,boolean isSerious) {
        super(context);
        this.x = x;
        this.y = y;
        this.size=size;
        this.isSerious=isSerious;
        actualSize=0;

        fill = new Paint();
        fill.setStyle(Paint.Style.FILL);
        fill.setColor(getResources().getColor(R.color.colorWarningNotSerious));
        if(isSerious)
            fill.setColor(getResources().getColor(R.color.colorWarningSerious));

    }

    public CerchioView(Context context, float x, float y,int size,boolean isSerious,boolean animation) {
        super(context);
        this.x = x;
        this.y = y;
        this.size=size;
        this.isSerious=isSerious;
        this.animation=animation;
        actualSize=0;

        fill = new Paint();
        fill.setStyle(Paint.Style.FILL);
        fill.setColor(getResources().getColor(R.color.colorWarningNotSerious));
        if(isSerious)
            fill.setColor(getResources().getColor(R.color.colorWarningSerious));


    }
    public CerchioView(Context context, float x, float y,int size,boolean isSerious,String type) {
        super(context);
        this.x = x;
        this.y = y;
        this.size=size;
        this.isSerious=isSerious;
        this.type=type;
        actualSize=0;

        fill = new Paint();
        fill.setStyle(Paint.Style.FILL);
        fill.setColor(getResources().getColor(R.color.colorWarningNotSerious));
        if(isSerious)
            fill.setColor(getResources().getColor(R.color.colorWarningSerious));

    }

    public CerchioView(Context context, float x, float y,int size,boolean isSerious,boolean animation,String type) {
        super(context);
        this.x = x;
        this.y = y;
        this.size=size;
        this.isSerious=isSerious;
        this.animation=animation;
        this.type=type;
        actualSize=0;

        fill = new Paint();
        fill.setStyle(Paint.Style.FILL);
        fill.setColor(getResources().getColor(R.color.colorWarningNotSerious));
        if(isSerious)
            fill.setColor(getResources().getColor(R.color.colorWarningSerious));


    }
    public void setNuoveCoordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(animation) {
            canvas.drawCircle(x, y, actualSize, fill);
            if (actualSize < size) {
                actualSize += 2;
                if (actualSize > size)
                    actualSize = size;
                postInvalidate();
            }
        }
        else
            canvas.drawCircle(x, y, size, fill);


    }


    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
