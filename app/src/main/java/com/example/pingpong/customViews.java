package com.example.pingpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Random;

class customViews extends View {

    public int score = 0;
    private Rect rect2;
    private Rect wall;
    private Float mCircleX;
    private Float mCircleY;
    private Float mRadius = 25f;
    private Paint mPaint;
    private int num=0;
    private int speedX;
    private int speedY;
    private Random r;
    private int a[] = {-1,1};

    private static SoundPool soundPool;
    private static int hit;
    private static int over;

    public customViews(Context context) {
        super(context);
        init(null);
    }

    public customViews(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        init(attrs);
    }

    public customViews(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public customViews(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {

        rect2 = new Rect();
        wall = new Rect();
        r = new Random();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);

        postInvalidate();
    }

    public void SoundPlayer(Context context){
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        hit = soundPool.load(String.valueOf(context), R.raw.hit1);
        over = soundPool.load(String.valueOf(context), R.raw.over);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);

        wall.left = 0;
        wall.right = getWidth();
        wall.top = 0;
        wall.bottom = getHeight()/30;

        canvas.drawRect(wall, mPaint);

        if(num==0){
            mCircleX = getWidth() / 2 - 0f;
            mCircleY = getHeight() / 2 - 0f;
            canvas.drawCircle(mCircleX, mCircleY, mRadius, mPaint);
            speedX = (r.nextInt(5)+5)*a[r.nextInt(2)];
            speedY = (r.nextInt(5)+4)*a[r.nextInt(2)];

            rect2.left = getWidth() / 2 - 100;
        }

        rect2.bottom = (getHeight()*95)/100;
        rect2.top = (getHeight()*93)/100;
        rect2.right = rect2.left + 200;

        canvas.drawRect(rect2, mPaint);

        if (mCircleX >= getWidth() || mCircleX <= 0) {
            speedX *=-1.4;
            soundPool.play(hit, 1.0f, 1.0f, 1, 0, 1.0f);
        }
        if (mCircleY >= rect2.top-10 && mCircleX <= rect2.right && mCircleX >= rect2.left) {
            speedY *= -1.3;
            soundPool.play(hit, 1.0f, 1.0f, 1, 0, 1.0f);
        }
        if(mCircleY <= wall.bottom+15){
            speedY *=-1;
            score+=1;
            soundPool.play(hit, 1.0f, 1.0f, 1, 0, 1.0f);
        }
        if(mCircleY >= getHeight()){
            speedY = 0;
            speedX = 0;
            soundPool.play(over, 1.0f, 1.0f, 1, 0, 1.0f);
        }
        mCircleX += speedX;
        mCircleY += speedY;
        canvas.drawCircle(mCircleX, mCircleY, mRadius, mPaint);

        num=1;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                return true;
            }
            case MotionEvent.ACTION_MOVE:{
                float x = event.getX();

                if(rect2.contains((int) event.getX(), (int) event.getY())){
                    rect2.left= (int) (x-100);

                    postInvalidate();
                    return true;
                }
                return value;
            }
        }
        return value;
    }
}

