package com.gmail.xuyimin1994.periscopelayout;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PeriscopeLayout extends RelativeLayout {


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
        final float[] x = new float[1];
        final float[] y = new float[1];
        final float[] t = new float[1];
        x[0] =x1;
        y[0] =y1;
        CircleView imageView=new CircleView(getContext());
        imageView.startAnim(new PointF(x1,y1));
        addView(imageView);
        imageView.setX(x[0]);
        imageView.setY(y[0]);
        Log.i(TAG,"add imageview");
        Observable observable= Observable.interval(0,10, TimeUnit.MILLISECONDS);
        Random random = new Random();//用于实现随机功能
        float vx= (float) (100*(random.nextFloat()-0.5));
        float vy=1600*random.nextFloat()+1200;
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .filter(n->{
//                    if(!(y1-y[0]<getHeight()+4*60)){
//                        Log.i(TAG,getChildCount()+"");
//                        removeView(imageView);
//                    }
//                    return y1-y[0]<getHeight()+4*60;
//                })
//                .subscribe(n->{
//                    t[0]=t[0]+(float) 10/1000;
//                     x[0] =vx*t[0];
//                     y[0] = (float) ((0.5*t[0])*vy-0.5*1000*t[0]*t[0]);
//                    imageView.setY(y1-y[0]);
//                    imageView.setX(x1+x[0]);
//                    //imageView.setAnimation();
//                    imageView.setAlpha(1-(float)t[0]/3);
//                    imageView.setScaleX(3*(float)t[0]/3+1);
//                    imageView.setScaleY(3*(float)t[0]/3+1);
//                })
//        ;
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
