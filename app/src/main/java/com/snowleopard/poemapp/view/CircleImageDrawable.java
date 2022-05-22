package com.snowleopard.poemapp.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CircleImageDrawable extends Drawable {

    private Bitmap mBitmap;
    private Paint mPaint;
    private BitmapShader mBitmapShader;
    private int mSize;
    private int mRadius;

    private static final String TAG = "CircleImageDrawable";

    public CircleImageDrawable(Bitmap bitmap) {
        this.mBitmap = bitmap;
        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setShader(mBitmapShader);
        mSize = Math.min(bitmap.getWidth(),bitmap.getHeight());
        mRadius = mSize / 2;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawCircle(mRadius,mRadius,mRadius,mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return mSize;
    }

    @Override
    public int getIntrinsicHeight() {
        return mSize;
    }
}
