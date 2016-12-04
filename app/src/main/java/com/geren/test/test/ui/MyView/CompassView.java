package com.geren.test.test.ui.MyView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.geren.test.test.R;

/**
 * Created by Kevin on 2016/12/4.
 */
public class CompassView extends View {

    private int defalutSize;

    public CompassView(Context context) {
//        super(context);
        this(context, null);
    }

    public CompassView(Context context, AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs, 0);
    }

    public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //第二个参数就是我们在styles.xml文件中的<declare-styleable>标签
        //即属性集合的标签，在R文件中名称为R.styleable+name
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.compassView);
        //第一个参数为属性集合里面的属性，R文件名称：R.styleable+属性集合名称+下划线+属性名称
        //第二个参数为，如果没有设置这个属性，则设置的默认的值
        defalutSize = a.getDimensionPixelSize(R.styleable.compassView_default_size, 100);
        //最后记得将TypedArray对象回收
        a.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(defalutSize, widthMeasureSpec);
        int height = getMySize(defalutSize, heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width, height);

    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它
                mySize = size;
                break;
            }
        }
        return mySize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p1 = new Paint();
        p1.setColor(Color.GREEN);
        p1.setTextSize(80);
        p1.setTextAlign(Paint.Align.CENTER);
        Paint p2 = new Paint();
        p2.setColor(Color.RED);
        p2.setTextSize(80);
        Paint p3 = new Paint();
        p1.setTextAlign(Paint.Align.CENTER);


        p3.setColor(Color.GRAY);
        p3.setStyle(Paint.Style.STROKE);

        int x = getWidth() / 2;
        int y = getHeight() / 2;

        canvas.drawText("西", 10, y, p1);
        canvas.drawText("东", getWidth()-90, y, p1);
        canvas.drawText("北", x, 90, p2);
        canvas.drawText("南", x, getHeight()-20, p1);

        canvas.drawCircle(x, y, x, p3);

       canvas.rotate(90);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

}
