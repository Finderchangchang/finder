package com.cjt2325.cameralibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * =====================================
 * 作    者: 陈嘉桐 445263848@qq.com
 * 版    本：1.0.4
 * 创建日期：2017/4/26
 * 描    述：拍照或录制完成后弹出的确认和返回按钮
 * =====================================
 */
public class TypeButton extends View {
    public static final int TYPE_CANCEL = 0x001;
    public static final int TYPE_CONFIRM = 0x002;
    public static final int TYPE_NULL = 0x003;
    private int button_type;
    private int button_size;

    private float center_X;
    private float center_Y;
    private float button_radius;

    private Paint mPaint;
    private Path path;
    private float strokeWidth;

    private float index;
    private RectF rectF;


    public TypeButton(Context context) {
        super(context);
    }

    public TypeButton(Context context, int type, int size) {
        super(context);
        this.button_type = type;
        button_size = size;
        button_radius = size / 2.25f;
        center_X = size / 2.0f;
        center_Y = size / 2.0f;

        mPaint = new Paint();
        path = new Path();
        strokeWidth = size / 25f;
        index = button_size / 18f;
        rectF = new RectF(center_X, center_Y - index, center_X + index * 2, center_Y + index);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(button_size, button_size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //如果类型为取消，则绘制内部为返回箭头
        if (button_type == TYPE_CANCEL) {
            mPaint.setAntiAlias(true);
            mPaint.setColor(0xFFFFFFFF);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(center_X, center_Y, button_radius, mPaint);

            mPaint.setColor(0xffbd10e0);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(strokeWidth);

//            path.moveTo(center_X - index / 7, center_Y + index);
//            path.lineTo(center_X + index, center_Y + index);
//
//            path.arcTo(rectF, 90, -180);
//            path.lineTo(center_X - index, center_Y - index);
//            canvas.drawPath(path, mPaint);
//            mPaint.setStyle(Paint.Style.FILL);
//            path.reset();
//            path.moveTo(center_X - index, (float) (center_Y - index * 1.5));
//            path.lineTo(center_X - index, (float) (center_Y - index / 2.3));
//            path.lineTo((float) (center_X - index * 1.6), center_Y - index);
//            path.close();

//            path.moveTo(center_X + index * 2, center_Y);
//            path.lineTo((float) (center_X - index * 1.5), center_Y);
//            canvas.drawPath(path, mPaint);
//            mPaint.setStyle(Paint.Style.FILL);
//            path.reset();
//            path.moveTo((float) (center_X - index * 1.5), (float) (center_Y - index * 0.5));
//            path.lineTo((float) (center_X - index * 1.5), (float) (center_Y + index * 0.5));
//            path.lineTo((float) (center_X - index * 2.3), center_Y);
//            path.close();

            path.moveTo(center_X - index * 1.5f, center_Y - index * 1.5f);
            path.lineTo(center_X + index * 1.5f, center_Y + index * 1.5f);
            canvas.drawPath(path, mPaint);

            path.reset();
            path.moveTo(center_X - index * 1.5f, center_Y + index * 1.5f);
            path.lineTo(center_X + index * 1.5f, center_Y - index * 1.5f);

            canvas.drawPath(path, mPaint);

        }
        //如果类型为确认，则绘制绿色勾
        if (button_type == TYPE_CONFIRM) {
            mPaint.setAntiAlias(true);
            mPaint.setColor(0xFFFFFFFF);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(center_X, center_Y, button_radius, mPaint);
            mPaint.setAntiAlias(true);
            mPaint.setColor(0xffbd10e0);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(strokeWidth);

//            path.moveTo(center_X - button_size / 6f, center_Y);
//            path.lineTo(center_X - button_size / 21.2f, center_Y + button_size / 7.7f);
//            path.lineTo(center_X + button_size / 4.0f, center_Y - button_size / 8.5f);
//            path.lineTo(center_X - button_size / 21.2f, center_Y + button_size / 9.4f);
//            path.close();

//            path.moveTo((float) (center_X + index * 1.5), center_Y);
//            path.lineTo(center_X - index * 2, center_Y);
//            canvas.drawPath(path, mPaint);
//            mPaint.setStyle(Paint.Style.FILL);
//            path.reset();
//            path.moveTo((float) (center_X + index * 1.5), (float) (center_Y - index * 0.5));
//            path.lineTo((float) (center_X + index * 1.5), (float) (center_Y + index * 0.5));
//            path.lineTo((float) (center_X + index * 2.3), center_Y);
//            path.close();
            path.moveTo(center_X - index * 1.8f, center_Y);
            path.lineTo(center_X - index * 0.3f, center_Y + index * 1.5f);
            path.lineTo(center_X + index * 2.3f, center_Y - index * 1.5f);

            canvas.drawPath(path, mPaint);
        }
    }
}
