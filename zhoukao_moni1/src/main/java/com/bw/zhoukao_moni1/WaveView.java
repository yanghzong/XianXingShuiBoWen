package com.bw.zhoukao_moni1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 择木 on 2018/10/8.
 */

public class WaveView  extends View{

    private Paint paint;
    private Path path;
    private  float fai=0;
    private  int A=0;

    public WaveView(Context context) {
        this(context,null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaveViewStyle);
        int color = typedArray.getColor(R.styleable.WaveViewStyle_color, Color.WHITE);
        typedArray.recycle();


        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        path = new Path();


    }


    //回调接口
    public  interface  OnWaveViewClickListener{

        void  onChange(float y);
    }

    private OnWaveViewClickListener listener;

   public  void  setOnWaveViewClickListener(OnWaveViewClickListener listener){
       this.listener=listener;
   }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //y=Asin(Ωx+φ)+k

        fai-=0.1f;

        //重置路径
        path.reset();

        //移动到左下角的位置
        path.moveTo(getLeft(),getBottom());


        for (int x=0;x<getMeasuredWidth();x+=20){
            float y=A*(float)(Math.sin(2*Math.PI/getMeasuredWidth()*x+fai))+A;
                if (x>getMeasuredWidth()/2-10&& x<getMeasuredWidth()/2+10){
                    if (listener!=null){
                        listener.onChange(y);
                    }
                }
                path.lineTo(x,y);

            }




        path.lineTo(getRight(),getBottom());
        canvas.drawPath(path,paint);

        postInvalidateDelayed(50);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       A=MeasureSpec.getSize(heightMeasureSpec)/2;

   }
}
