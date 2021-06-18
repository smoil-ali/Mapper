package com.techyaSoft.myapplication.MyCustomViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class DrawLineView extends androidx.appcompat.widget.AppCompatImageView {

    final String TAG = DrawLineView.class.getSimpleName();
    Canvas canvas;
    Paint paint,pBg;
    Path path;
    Bitmap bitmap;

    public DrawLineView(Context context) {
        super(context);
        init();
    }

    public DrawLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12);

        pBg = new Paint();
        pBg.setColor(Color.WHITE);



        path = new Path();
    }

    public void ActionDown(float x,float y){

    }

    public void ActionUp(float x, float y,float a,float b){
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawLine(x,y,a,b,paint);
    }

    public void  ActionMove(){
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,pBg);
    }
}
