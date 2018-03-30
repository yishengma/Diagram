package com.example.asus.diagram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by asus on 18-3-30.
 */

public class DiagramView extends LinearLayout {
    private Paint mPaint;
    private Path mPath;
    private int x = 0;
    private int y = 0;
    private int mHeightY;
    private int mLowY;
    private static final int sFIRSTITEM = 0;
    private static final int sMEDIUMITEM = 1;
    private static final int sLASTITEM = 2;
    private int mItemType ;
    private static final String TAG = "DiagramView";
    public DiagramView(Context context) {
        this(context,null);
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    public DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2, mHeightY,2,mPaint);
        canvas.drawCircle(getWidth()/2,mLowY,2,mPaint);
        switch (mItemType){
            case sFIRSTITEM :
                mPath.moveTo(getWidth()/2,mHeightY);
                mPath.lineTo(getWidth(),y);

                mPath.moveTo(getWidth()/2,mLowY);
                mPath.lineTo(getWidth(),y);
                break;
            case sMEDIUMITEM:
                mPath.moveTo(0,mHeightY);
                mPath.lineTo(getWidth()/2,y);

                mPath.moveTo(0,mLowY);
                mPath.lineTo(getWidth()/2,y);


                mPath.moveTo(getWidth()/2,mHeightY);
                mPath.lineTo(getWidth(),y);

                mPath.moveTo(getWidth()/2,mLowY);
                mPath.lineTo(getWidth(),y);

                break;
            case sLASTITEM:
                mPath.moveTo(0,mHeightY);
                mPath.lineTo(getWidth()/2,y);

                mPath.moveTo(0,mLowY);
                mPath.lineTo(getWidth()/2,y);
                break;
                default:
                    break;
        }

        canvas.drawPath(mPath, mPaint);

    }

    public void draws(int x,int y){
        this.x = x;
        this.y = y;
        invalidate();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        mPath = new Path();
        mItemType = 0;
    }
}
