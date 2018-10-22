package com.gmail.xuyimin1994.periscopelayout;

import android.content.Context;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import java.util.Calendar;
import java.util.Date;


public class PeriscopeLayout extends RelativeLayout {

    private long time;
    private final String TAG="periscopelayout";
    public PeriscopeLayout(Context context) {
        super(context);

    }

    public PeriscopeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public PeriscopeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PeriscopeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }


    private  void  addView(float x1,float y1){
        long timeNow=System.currentTimeMillis();
        if(timeNow-time<100)return;
        time=timeNow;
        final float[] x = new float[1];
        final float[] y = new float[1];
        x[0] =x1;
        y[0] =y1;
        CircleView imageView=new CircleView(getContext());
        addView(imageView);
        imageView.startAnim(new PointF(x1,y1),this::removeView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                addView(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                addView(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;

        }
        return true;
    }
}
