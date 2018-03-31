package com.example.asus.diagram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by asus on 18-3-30.
 */

public class DiagramView extends View {
    private Paint mPaint;
    private TextPaint mTextPaint;
    private Path mPath;
    private int mNextHeightY;
    private int mNextLowY;
    private int mHeightY;
    private int mLowY;
    private int mPreHeightY;
    private int mPreLowY;

    private int mCaculatedNextHeightY;
    private int mCaculatedNextLowY;
    private int mCaculatedHeightY;
    private int mCaculatedLowY;
    private int mCaculatedPreHeightY;
    private int mCaculatedPreLowY;


    private int mWidth ;
    private int mHeight;
    private String mHeightText;
    private String mLowText;
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
              mWidth = getWidth();
              mHeight = getHeight();
            caculateY(mHeight);
            canvas.drawCircle(mWidth/ 2, mCaculatedHeightY, 4, mPaint);
            canvas.drawCircle(mWidth/ 2, mCaculatedLowY, 4, mPaint);
            canvas.drawText(mHeightText,mWidth/2-mTextPaint.getTextSize()/2,mCaculatedHeightY-5,mTextPaint);
            canvas.drawText(mLowText,mWidth/2-mTextPaint.getTextSize()/2,mCaculatedLowY+mTextPaint.getTextSize(),mTextPaint);
        switch (mItemType) {
                case sFIRSTITEM:
                    mPath.moveTo(mWidth / 2, mCaculatedHeightY);
                    mPath.lineTo(mWidth, (mCaculatedHeightY + mCaculatedNextHeightY) / 2);

                    mPath.moveTo(mWidth / 2, mCaculatedLowY);
                    mPath.lineTo(mWidth, (mCaculatedNextLowY + mCaculatedLowY) / 2);


                    break;

                case sMEDIUMITEM:
                    mPath.moveTo(0, (mCaculatedPreHeightY+mCaculatedHeightY)/2);
                    mPath.lineTo(mWidth / 2, mCaculatedHeightY);

                    mPath.moveTo(0, (mCaculatedPreLowY+mCaculatedLowY)/2);
                    mPath.lineTo(mWidth / 2,mCaculatedLowY);

                    mPath.moveTo(mWidth / 2, mCaculatedHeightY);
                    mPath.lineTo(mWidth, (mCaculatedHeightY + mCaculatedNextHeightY) / 2);

                    mPath.moveTo(mWidth / 2, mCaculatedLowY);
                    mPath.lineTo(mWidth, (mCaculatedNextLowY + mCaculatedLowY) / 2);





                    break;
                case sLASTITEM:
                    mPath.moveTo(0, (mCaculatedPreHeightY+mCaculatedHeightY)/2);
                    mPath.lineTo(mWidth / 2, mCaculatedHeightY);

                    mPath.moveTo(0, (mCaculatedLowY+mCaculatedPreLowY)/2);
                    mPath.lineTo(mWidth / 2, mCaculatedLowY);
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
        this.mLowY =lowY;
        this.mItemType  =itemType;

        invalidate();

    }

    public void draws(int preHeightY,int preLowY,int heightY, int lowY, int nextHeighY, int nextLowY,int itemType) {

        this.mPreHeightY =preHeightY;
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

    private void caculateY(int height){
         mCaculatedNextHeightY = height/2+height/8-mNextHeightY;
         mCaculatedNextLowY = height/2+height/4+height/8-mNextLowY;
         mCaculatedHeightY = height/2+height/8-mHeightY;
         mCaculatedLowY=height/2+height/4+height/8-mLowY;
         mCaculatedPreHeightY=height/2+height/8-mPreHeightY;
         mCaculatedPreLowY=height/2+height/4+height/8-mPreLowY;

    }



    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#b6b3b3"));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mPath = new Path();
        mItemType = 0;
        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.parseColor("#424242"));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStrokeWidth(1);
        mTextPaint.setTextSize(30);
        mTextPaint.setStrokeCap(Paint.Cap.ROUND);




    }

    public void setText(int heightText,int lowText){
        this.mHeightText = heightText+"";
        this.mLowText = lowText+"";
    }


}
