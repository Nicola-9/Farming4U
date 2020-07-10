package com.gruppodieci.farming4u.business;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import androidx.annotation.Nullable;

import com.gruppodieci.farming4u.R;

public class CerchioView extends View {
    private float x,y;
    private int size;
    private boolean isSerious;

    private int actualSize;
    private boolean animation;
  
    private Paint fill;
    private Paint strokePaint;
    private Warning warning;


    public CerchioView(Context context, float x, float y, int size, boolean isSerious, Warning warning) {
        super(context);
        instanziaTutto(x,y,size,isSerious,warning);
    }

    public CerchioView(Context context, float x, float y, int size, boolean isSerious, boolean animation, Warning warning) {
        super(context);
        this.animation=animation;
        instanziaTutto(x,y,size,isSerious,warning);
    }

    private void instanziaTutto(float x, float y, int size, boolean isSerious, Warning warning){
        this.x = x;
        this.y = y;
        this.size=size;
        this.isSerious=isSerious;
        actualSize=1;
        this.warning=warning;

        fill = new Paint();
        fill.setStyle(Paint.Style.FILL);
        strokePaint=new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(12);
        strokePaint.setStrokeCap(Paint.Cap.ROUND);
        strokePaint.setColor(getResources().getColor(R.color.colorWarningNotSeriousStroke));
        fill.setColor(getResources().getColor(R.color.colorWarningNotSerious));
        if(isSerious) {
            fill.setColor(getResources().getColor(R.color.colorWarningSerious));
            strokePaint.setColor(getResources().getColor(R.color.colorWarningSeriousStroke));

        }
    }

    public void setNuoveCoordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(animation) {
            Path path = new Path();
            path.addCircle(x, y, actualSize, Path.Direction.CW);
            canvas.clipPath(path);
            int icona=getIcona();
            Bitmap b=BitmapFactory.decodeResource(getResources(),icona);
            Bitmap bCopy=Bitmap.createScaledBitmap(b, (int)(actualSize*1.3), (int)(actualSize*1.3), false);
            canvas.drawCircle(x, y, actualSize, fill);
            canvas.drawCircle(x,y,actualSize,strokePaint);
            canvas.drawBitmap(bCopy, x - bCopy.getWidth()/2 , y - bCopy.getHeight()/2 , null);
            if (actualSize < size) {
                actualSize += 2;
                if (actualSize > size)
                    actualSize = size;
                postInvalidate();
            }
        }
        else {
            Path path = new Path();
            path.addCircle(x, y, size, Path.Direction.CW);
            canvas.clipPath(path);
            int icona=getIcona();
            Bitmap b=BitmapFactory.decodeResource(getResources(),icona);
            Bitmap bCopy=Bitmap.createScaledBitmap(b, (int)(size*1.3), (int)(size*1.3), false);
            canvas.drawCircle(x, y, size,fill);
            canvas.drawCircle(x,y,size,strokePaint);
            canvas.drawBitmap(bCopy, x - bCopy.getWidth()/2 , y - bCopy.getHeight()/2 , null);

        }

    }




    private int getIcona(){
        int icona=0;
        switch (warning.getType()){
            case Warning.CONCIMAZIONE:
                icona=R.drawable.icona_concimazione;
                break;
            case Warning.PESTICIDI:
                icona=R.drawable.icona_pesticidi;
                break;
            case Warning.ERBA:
                icona=R.drawable.icona_erba;
                break;
            case Warning.IRRIGAZIONE:
                icona=R.drawable.icona_irrigazione;
                break;
        }
        return icona;
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

    public void setWarning(Warning warning) {
        this.warning = warning;
    }

    public Warning getWarning() {
        return this.warning;
    }


}
