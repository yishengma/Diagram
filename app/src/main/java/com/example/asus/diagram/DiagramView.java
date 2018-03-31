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
    private int mNextHeightY;
    private int mNextLowY;
    private int mHeightY;
    private int mLowY;
    private int mPreHeightY;
    private int mPreLowY;
    private static final int sFIRSTITEM = 0;
    private static final int sMEDIUMITEM = 1;
    private static final int sLASTITEM = 2;
    private boolean mIsDrawed = false;
    private int mItemType;
    private static final String TAG = "DiagramView";

    public DiagramView(Context context) {
        this(context, null);
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

            canvas.drawCircle(getWidth() / 2, mHeightY, 2, mPaint);
            canvas.drawCircle(getWidth() / 2, mLowY, 2, mPaint);
            switch (mItemType) {
                case sFIRSTITEM:
                    mPath.moveTo(getWidth() / 2, mHeightY);
                    mPath.lineTo(getWidth(), (mHeightY + mNextHeightY) / 2);

                    mPath.moveTo(getWidth() / 2, mLowY);
                    mPath.lineTo(getWidth(), (mNextLowY + mLowY) / 2);


                    break;

                case sMEDIUMITEM:
                    mPath.moveTo(0, (mPreHeightY+mHeightY)/2);
                    mPath.lineTo(getWidth() / 2, mHeightY);

                    mPath.moveTo(0, (mPreLowY+mLowY)/2);
                    mPath.lineTo(getWidth() / 2,mLowY);

                    mPath.moveTo(getWidth() / 2, mHeightY);
                    mPath.lineTo(getWidth(), (mHeightY + mNextHeightY) / 2);

                    mPath.moveTo(getWidth() / 2, mLowY);
                    mPath.lineTo(getWidth(), (mNextLowY + mLowY) / 2);





                    break;
                case sLASTITEM:
                    mPath.moveTo(0, (mPreHeightY+mHeightY)/2);
                    mPath.lineTo(getWidth() / 2, mHeightY);

                    mPath.moveTo(0, (mPreLowY+mLowY)/2);
                    mPath.lineTo(getWidth() / 2, mLowY);
                    break;
                default:
                    break;
            }

            canvas.drawPath(mPath, mPaint);


    }
    public void draws(int preHeightY,int preLowY,int heightY, int lowY,int itemType,boolean last) {
        this.mPreHeightY = preHeightY;
        this.mPreLowY = preLowY;
        this.mHeightY = heightY;
        this.mLowY = lowY;
        this.mItemType  =itemType;

        invalidate();
    }

    public void draws(int preHeightY,int preLowY,int heightY, int lowY, int nextHeighY, int nextLowY,int itemType) {
        this.mPreHeightY = preHeightY;
        this.mPreLowY = preLowY;
        this.mHeightY = heightY;
        this.mLowY = lowY;
        this.mNextHeightY = nextHeighY;
        this.mNextLowY = nextLowY;
        this.mItemType  =itemType;

        invalidate();
    }

    public void draws(int heightY, int lowY, int nextHeighY, int nextLowY,int itemType) {

        this.mHeightY = heightY;
        this.mLowY = lowY;
        this.mNextHeightY = nextHeighY;
        this.mNextLowY = nextLowY;
        this.mItemType  =itemType;

        invalidate();
    }

    private void init() {
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
