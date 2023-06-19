package com.lch.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {
    private static final String TAG = "FlowLayout";

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int n = getChildCount();
        int maxViewHeight = 0;    //当前行的子组件的最大高度
        int maxLineWidth = 0;    //当前行的子组件的总宽度
        int totalHeight = 0;    //累计高度
        int width = getMeasuredWidth();    //容器宽度
        for (int i = 0; i < n; i++) {
            View child = getChildAt(i);
            //判断是否要换行显示（已占宽度+当前子组件的宽度是否大于容器的宽度）
            if (maxLineWidth + getChildAt(i).getMeasuredWidth()
                    > width - getPaddingLeft() - getPaddingRight()) {
                //换行后累计已显示的行的总高度
                totalHeight += maxViewHeight;
                Log.i(TAG, "totalHeight:" + totalHeight + "	maxLineWidth:" +
                        maxLineWidth + "	width:" + width);
                //新起一行，新行的已占宽度和高度重置为 0
                maxLineWidth = 0;
                maxViewHeight = 0;
            }
            layoutChild(child, maxLineWidth, totalHeight,
                    maxLineWidth + child.getMeasuredWidth(),
                    totalHeight + child.getMeasuredHeight());
            //获取当前行的最高高度
            maxViewHeight = Math.max(maxViewHeight,
                    child.getMeasuredHeight());
            //累加当前行的宽度
            maxLineWidth += child.getMeasuredWidth();
        }
    }

    /**
     * 定位子组件，方法内考虑 padding
     *
     * @param    child
     * @param    l
     * @param    t
     * @param    r
     * @param    b
     */
    private void layoutChild(View child, int l, int t, int r, int b) {
        Log.i(TAG, child.getTag() + ":" + "	Left:" + l + "	Top:"
                + t + "	Right:" + r + "	Bottom:" + b);
        //所有子组件要统一向右和向下平移指定的 padding
        child.layout(l + getPaddingLeft(), t + getPaddingTop(),
                r + getPaddingLeft(), b + getPaddingTop());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    /**
     * 测量容器宽度
     *
     * @return
     * @param    widthMeasureSpec
     */
    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            //计算出所有子组件占的总宽度
            int n = getChildCount();
            int childrenWidth = 0;
            for (int i = 0; i < n; i++) {
                View child = getChildAt(i);
                int childWidth = child.getMeasuredWidth();
                //单个子组件的宽度不能超过容器宽度
                if (childWidth > size) {
                    throw new IllegalStateException("Sub	view	is	too	large.");
                }
                childrenWidth += childWidth;
            }
            Log.i(TAG, "size:" + size + "	viewsWidth:" + childrenWidth);
            //在 wrap_content 的情况下，如果子组件占的总宽度>容器的
            //最大宽度，则应该取容器最大宽度
            if (childrenWidth > size) {
                width = size;
            } else {
                width = childrenWidth;
            }
            //padding
            width += this.getPaddingLeft() + getPaddingRight();
        }
        return width;
    }

    /**
     * 测量容器高度
     *
     * @return
     * @param    heightMeasureSpec
     */
    private int measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else if (mode == MeasureSpec.AT_MOST) {//wrap_content 容器高度跟随内容
            int width = getMeasuredWidth();
            int n = getChildCount();
            int maxViewHeight = 0;//当前行的子组件的最大高度
            int maxLineWidth = 0;//当前行的子组件的总宽度
            for (int i = 0; i < n; i++) {
                View child = getChildAt(i);
                maxLineWidth += child.getMeasuredWidth();
                maxViewHeight =
                        Math.max(child.getMeasuredHeight(), maxViewHeight);
                //预测是否需要换行
                if (i < n - 1 && maxLineWidth
                        + getChildAt(i + 1).getMeasuredWidth()
                        > width - getPaddingLeft() - getPaddingRight()) {
                    //当前行的子组件宽度如果超出容器的宽度，则要换行
                    height += maxViewHeight;
                    maxLineWidth = 0;
                    maxViewHeight = 0;
                } else if (i == n - 1) {
                    //已经遍历到最后一个
                    height += maxViewHeight;
                }
            }
        }
        //padding
        height += getPaddingTop() + getPaddingBottom();
        return height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
