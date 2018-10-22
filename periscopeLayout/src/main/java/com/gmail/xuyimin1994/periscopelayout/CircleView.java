package com.gmail.xuyimin1994.periscopelayout;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class CircleView extends View {
    ArrayList<Integer> list=new ArrayList<>();
    private void init(){
        list.add(getContext().getResources().getColor(R.color.f));
        list.add(getContext().getResources().getColor(R.color.colorPrimary));
        list.add(getContext().getResources().getColor(R.color.colorAccent));
        list.add(getContext().getResources().getColor(R.color.d));
        list.add(getContext().getResources().getColor(R.color.e));
        list.add(getContext().getResources().getColor(R.color.c));
        list.add(getContext().getResources().getColor(R.color.a));
        list.add(getContext().getResources().getColor(R.color.colorYellow));
        list.add(getContext().getResources().getColor(R.color.f));
    }

    public CircleView(Context context) {
        super(context);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(60, 60);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint=new Paint();
        Random random = new Random();
        paint.setColor(list.get(random.nextInt(8)));
        paint.setAntiAlias(true);
       //paint.setColor(getContext().getResources().getColor(R.color.colorYellow));
        canvas.drawCircle(30,30,30,paint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private  class ParabolaListerer implements ValueAnimator.AnimatorUpdateListener{
        private View target;
        private OnAnimFinishListener onAnimFinishListener;

        public ParabolaListerer(View target, OnAnimFinishListener onAnimFinishListener) {
            this.target = target;
            this.onAnimFinishListener = onAnimFinishListener;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            PointF pointF = (PointF) animation.getAnimatedValue();
            target.setX(pointF.x);
            target.setY(pointF.y);
            target.setScaleX(1+animation.getAnimatedFraction());
            target.setScaleY(1+animation.getAnimatedFraction());
            target.setAlpha(2- animation.getAnimatedFraction()*2);
            if(1-animation.getAnimatedFraction()==0){
                onAnimFinishListener.OnAnimFinish(target);
            }
        }


    }

    public void startAnim(PointF pointF,OnAnimFinishListener onAnimFinishListener){
        setX(pointF.x);
        setY(pointF.y);
        ParabolaEvaluator parabolaEvaluator=new ParabolaEvaluator();
        Random random = new Random();//用于实现随机功能
        PointF ponitF=new PointF((float) (random.nextFloat()-0.5),random.nextFloat());
        ValueAnimator animator = ValueAnimator.ofObject(parabolaEvaluator,pointF,ponitF);
        ParabolaListerer listerer=new ParabolaListerer(this,onAnimFinishListener);
        animator.addUpdateListener(listerer);
        animator.setTarget(this);
        animator.setDuration(5000);
        animator.start();
    }

    public interface  OnAnimFinishListener{
        void  OnAnimFinish(View view);
    }
}
