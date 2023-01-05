package com.lch.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CornerLayout extends ViewGroup {
    public CornerLayout(Context context) {
        super(context);
    }

    public CornerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 定位子组件
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (i == 0) {
                //定位到左上角
                child.layout(0, 0, child.getMeasuredWidth(),
                        child.getMeasuredHeight());
            } else if (i == 1) {
                //定位到右上角
                child.layout(getMeasuredWidth() - child.getMeasuredWidth(),
                        0, getMeasuredWidth(), child.getMeasuredHeight());
            } else if (i == 2) {
                //定位到左下角
                child.layout(0, getMeasuredHeight() - child.getMeasuredHeight(),
                        child.getMeasuredWidth(), getMeasuredHeight());
            } else if (i == 3) {
                //定位到右下角
                child.layout(getMeasuredWidth() - child.getMeasuredWidth(),
                        getMeasuredHeight() - child.getMeasuredHeight(),
                        getMeasuredWidth(), getMeasuredHeight());
            }
        }
    }

    /**
     * 测量尺寸
     *
     * @param    widthMeasureSpec
     * @param    heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //先测量所有子组件的大小
        this.measureChildren(widthMeasureSpec, heightMeasureSpec);
        //再测量自己的大小
        int width = this.measureWidth(widthMeasureSpec);
        int height = this.measureHeight(heightMeasureSpec);
        //应用尺寸
        this.setMeasuredDimension(width, height);
    }

    /**
     * 测量容器的宽度
     *
     * @return
     * @param    widthMeasureSpec
     */
    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;
        if (mode == MeasureSpec.EXACTLY) {
            //match_parent 或具体值
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            //wrap_content
            int aWidth = 0;
            int bWidth = 0;
            int cWidth = 0;
            int dWidth = 0;
            for (int i = 0; i < this.getChildCount(); i++) {
                if (i == 0)
                    aWidth = getChildAt(i).getMeasuredWidth();
                else if (i == 1)
                    bWidth = getChildAt(i).getMeasuredWidth();
                else if (i == 2)
                    cWidth = getChildAt(i).getMeasuredWidth();
                else if (i == 3)
                    dWidth = getChildAt(i).getMeasuredWidth();
            }
            width = Math.max(aWidth, cWidth) + Math.max(bWidth, dWidth);
        }
        return width;
    }

    /**
     * 测量容器的高度
     *
     * @return
     * @param    heightMeasureSpec
     */
    private int measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;
        if (mode == MeasureSpec.EXACTLY) {
            //match_parent 或具体值
            height = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            //wrap_content
            int aHeight = 0;
            int bHeight = 0;
            int cHeight = 0;
            int dHeight = 0;
            for (int i = 0; i < this.getChildCount(); i++) {
                if (i == 0)
                    aHeight = getChildAt(i).getMeasuredHeight();
                else if (i == 1)
                    bHeight = getChildAt(i).getMeasuredHeight();
                else if (i == 2)
                    cHeight = getChildAt(i).getMeasuredHeight();
                else if (i == 3)
                    dHeight = getChildAt(i).getMeasuredHeight();
            }
            height = Math.max(aHeight, bHeight) + Math.max(cHeight, dHeight);
        }
        return height;
    }
}
