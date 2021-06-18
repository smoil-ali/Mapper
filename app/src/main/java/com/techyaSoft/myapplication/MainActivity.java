package com.techyaSoft.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.techyaSoft.myapplication.MyCustomViews.DrawLineView;


public class MainActivity extends AppCompatActivity {
    final String TAG = MainActivity.class.getSimpleName();
    float dx,dy,mx,my,cX,cY,InitX,InitY,Fx,Fy=0;
    View control,xLine,yLine;
    DrawLineView drawLineView;
    ConstraintLayout constraintLayout;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        control = findViewById(R.id.control);
        xLine = findViewById(R.id.xLine);
        yLine = findViewById(R.id.yLine);
        drawLineView = findViewById(R.id.custom_view);
        constraintLayout = findViewById(R.id.container);


        Display mdisp = getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);
        int maxX = mdispSize.x;
        int maxY = mdispSize.y;

        Log.i(TAG,maxX+" "+maxY);



        control.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        dx=yLine.getX()-event.getRawX();
                        dy=xLine.getY()-event.getRawY();
                        cX=v.getX()-event.getRawX();
                        cY=v.getY()-event.getRawY();
                        InitX = event.getRawX()+cX;
                        InitY = event.getRawY()+cY;
                        Fx = event.getRawX()+cX;
                        Fy = event.getRawY()+cY;
                        drawLineView.ActionDown(event.getRawX()+cX,event.getRawY()+cY);
                        Log.i(TAG,"Action Down "+dx+" "+dy+" "+event.getRawY());
                        break;
                    case MotionEvent.ACTION_UP:
                        float fx = v.getX()-event.getRawX();
                        float fy = v.getY()-event.getRawY();
                        drawLineView.setImageBitmap(getView());
                        Log.i(TAG,"Action Up");
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        Log.i(TAG,"Action pointer up");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.i(TAG,"Action Move "+event.getRawY());
                        my=event.getRawY()+dy;
                        mx=event.getRawX()+dx;
                        xLine.animate()
                                    .y(my)
                                    .setDuration(0)
                                    .start();
                        yLine.animate()
                                    .x(mx)
                                    .setDuration(0)
                                    .start();
                        v.animate().x(event.getRawX()+cX)
                                    .y(event.getRawY()+cY)
                                    .setDuration(0)
                                    .start();
                        Fx = event.getRawX()+cX;
                        Fy = event.getRawY()+cY;

                        drawLineView.ActionUp(InitX,
                                InitY,
                                Fx,
                                Fy);
                        break;
                }
                return true;
            }
        });
    }

    private Bitmap getView(){
        Bitmap returnedBitmap =
                Bitmap.createBitmap(drawLineView.getWidth(),
                        drawLineView.getHeight(),
                        Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =drawLineView.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);

        drawLineView.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }
}