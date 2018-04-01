package com.example.asus.diagram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;

import android.view.View;


public class DiagramView extends View {
    private Paint mPaint;//画笔工具
    private TextPaint mTextPaint;//画文字的工具
    private Path mPath;//画线
    private int mNextHeightY;//下一个高点的坐标
    private int mNextLowY;//下一个低点的坐标
    private int mHeightY;//高点坐标
    private int mLowY;//低点坐标
    private int mPreHeightY;//前一个高点坐标
    private int mPreLowY;//前一个低点坐标
    //计算过后的高点的坐标
    private int mCaculatedNextHeightY;
    private int mCaculatedNextLowY;
    private int mCaculatedHeightY;
    private int mCaculatedLowY;
    private int mCaculatedPreHeightY;
    private int mCaculatedPreLowY;

    //每一个item 的高度和宽度
    private int mWidth ;
    private int mHeight;

    //数字
    private String mHeightText;
    private String mLowText;

    //分别为第一个item 中间的item 和最后的item ,因为这三种item 需要画线的情况不同，所以设置三个状态进行判断
    private static final int sFIRSTITEM = 0;
    private static final int sMEDIUMITEM = 1;
    private static final int sLASTITEM = 2;
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
        //自定View 重写onDraw 方法时需要设置这个标志，表示需要重绘，否则会使用默认的onDraw 方法
        setWillNotDraw(false);
        init();
    }

    /**
     * 在onDraw 方法里进行曲线的绘制和温度数据的显示
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

              mWidth = getWidth();
              mHeight = getHeight();
            caculateY(mHeight);//根据屏幕的高度计算出坐标

            //描点
             canvas.drawCircle(mWidth/ 2, mCaculatedHeightY, 4, mPaint);
            canvas.drawCircle(mWidth/ 2, mCaculatedLowY, 4, mPaint);

            //画数值
            canvas.drawText(mHeightText,mWidth/2-mTextPaint.getTextSize()/2,mCaculatedHeightY-5,mTextPaint);
            canvas.drawText(mLowText,mWidth/2-mTextPaint.getTextSize()/2,mCaculatedLowY+mTextPaint.getTextSize(),mTextPaint);


            //根据不同的item 画出不同的状态的曲线
            switch (mItemType) {

                //第一个item 只需要从中间到右边
                case sFIRSTITEM:
                    mPath.moveTo(mWidth / 2, mCaculatedHeightY);
                    mPath.lineTo(mWidth, (mCaculatedHeightY + mCaculatedNextHeightY) / 2);

                    mPath.moveTo(mWidth / 2, mCaculatedLowY);
                    mPath.lineTo(mWidth, (mCaculatedNextLowY + mCaculatedLowY) / 2);


                    break;

                    //中间的item 需要从左边画到中间，再从中间画到右边
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

                    //最后一个item 只需要从坐标画到中间即可
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

    /**
     * 最后一个item 的数据赋值，并重绘
     * @param preHeightY
     * @param preLowY
     * @param heightY
     * @param lowY
     * @param itemType
     * @param last
     */
    public void draws(int preHeightY,int preLowY,int heightY, int lowY,int itemType,boolean last) {
        this.mPreHeightY = preHeightY;
        this.mPreLowY = preLowY;
        this.mHeightY = heightY;
        this.mLowY =lowY;
        this.mItemType  =itemType;

        invalidate();

    }

    /**
     * 中间的item 的数据赋值并重绘
     * @param preHeightY
     * @param preLowY
     * @param heightY
     * @param lowY
     * @param nextHeighY
     * @param nextLowY
     * @param itemType
     */
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


    /**
     * 第一个item 的数据赋值并重绘
     * @param heightY
     * @param lowY
     * @param nextHeighY
     * @param nextLowY
     * @param itemType
     */
    public void draws(int heightY, int lowY, int nextHeighY, int nextLowY,int itemType) {
        this.mHeightY = heightY;
        this.mLowY = lowY;
        this.mNextHeightY = nextHeighY;
        this.mNextLowY = nextLowY;
        this.mItemType  =itemType;

        invalidate();


    }


    /**
     * 根据屏幕高度和温度数据计算出坐标，为了显示在中间高点多加了/8项，低点多加了/4/8项
     * @param height
     */
    private void caculateY(int height){
         mCaculatedNextHeightY = height/2+height/8-mNextHeightY;
         mCaculatedNextLowY = height/2+height/4+height/8-mNextLowY;
         mCaculatedHeightY = height/2+height/8-mHeightY;
         mCaculatedLowY=height/2+height/4+height/8-mLowY;
         mCaculatedPreHeightY=height/2+height/8-mPreHeightY;
         mCaculatedPreLowY=height/2+height/4+height/8-mPreLowY;

    }


    /**
     * 初始化画笔工具
     */
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

    /**
     * 设置温度数值
     * @param heightText
     * @param lowText
     */
    public void setText(int heightText,int lowText){
        this.mHeightText = heightText+"";
        this.mLowText = lowText+"";
    }


}
