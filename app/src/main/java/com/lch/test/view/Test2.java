package com.lch.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class Test2 extends View {

    public Test2(Context context) {
        super(context);
    }

    public Test2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int	color=Color.parseColor("#66FF0000");
    }
}
