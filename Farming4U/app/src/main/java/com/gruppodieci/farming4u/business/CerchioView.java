package com.gruppodieci.farming4u.business;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.Nullable;

import com.gruppodieci.farming4u.R;

public class CerchioView extends View {
    private float x,y;
    private int size;
    private boolean isSerious;
    Warning warning;

    private Paint fill;
    public CerchioView(Context context, float x, float y,int size,boolean isSerious) {
        super(context);
        this.x = x;
        this.y = y;
        this.size=size;
        this.isSerious=isSerious;
        this.warning = null;

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

    public void setWarning(Warning warning) {
        this.warning = warning;
    }

    public Warning getWarning() {
        return this.warning;
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
    }

}
