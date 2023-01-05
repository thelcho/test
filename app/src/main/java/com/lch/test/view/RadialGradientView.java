package com.lch.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

public class RadialGradientView extends View {
    public	RadialGradientView(Context context, AttributeSet attrs)	{
        super(context,	attrs);
    }
    @Override
    protected	void	onDraw(Canvas canvas)	{
        super.onDraw(canvas);
        Rect rect	=	new	Rect(100,	100,	500,	500);
        RadialGradient rg	=	new	RadialGradient(
                300,	300,	200,	Color.RED,	Color.GREEN,	Shader.TileMode.MIRROR);
        Paint p	=	new	Paint(Paint.ANTI_ALIAS_FLAG);
        p.setShader(rg);
        canvas.drawRect(rect,	p);
        canvas.translate(510,	0);
        canvas.drawOval(new RectF(rect),	p);



        //SweepGradient
        Paint	paint	=	new	Paint(Paint.ANTI_ALIAS_FLAG);
        SweepGradient sg	=
                new	SweepGradient(300,	300,	Color.GREEN,	Color.YELLOW);
        paint.setShader(sg);
        canvas.drawRect(new	Rect(0,	500,	600,	1100),	paint);

        Paint	paint2	=	new	Paint(Paint.ANTI_ALIAS_FLAG);
        SweepGradient	sg2	=	new	SweepGradient(300,	300,
                new	int[]{Color.GREEN,	Color.YELLOW,	Color.RED,	Color.GREEN},	null);
        paint.setShader(sg2);
        canvas.drawOval(new	RectF(0,	1200,	600,	1800),	paint2);
    }
}
