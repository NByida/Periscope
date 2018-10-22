package com.gmail.xuyimin1994.periscopelayout;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

import java.util.Random;

public class ParabolaEvaluator implements TypeEvaluator<PointF> {
    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        float timeLeft = fraction;
        PointF point = new PointF();//结果
        Random random = new Random();//用于实现随机功能
        float vx= 1000*endValue.x;
                //(float) (100*(random.nextFloat()-0.5));
        float vy=2500*endValue.y+3500;
                //1600*random.nextFloat()+1200;
        point.x=vx*timeLeft+startValue.x;
        point.y= (float) ((float) startValue.y-((0.5*timeLeft)*vy-0.5*5000*timeLeft*timeLeft));
        return point;
    }
}
