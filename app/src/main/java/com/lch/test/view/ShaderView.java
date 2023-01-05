package com.lch.test.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.lch.test.R;

public class ShaderView extends View {
    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(100);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, paint);
        paint.setShadowLayer(10, 1, 1, Color.RED);
        canvas.drawText("Android 开发", 100, 100, paint);
        paint.setShadowLayer(10, 5, 5, Color.BLUE);
        canvas.drawText("Android 绘图技术", 100, 220, paint);


        //BitmapShader
        Bitmap bmp = BitmapFactory.decodeResource(
                getResources(), R.mipmap.ic_launcher);
        BitmapShader bs = new BitmapShader(bmp,
                Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
        Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(bs);
        canvas.drawRect(new Rect(0, 0,
                getMeasuredWidth(), getMeasuredHeight()), paint2);


        //位图渐变
        Bitmap bmp3 = BitmapFactory.decodeResource(
                getResources(), R.mipmap.ic_launcher);
        BitmapShader bs3 = new BitmapShader(bmp3,
                Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
        //线性渐变
        LinearGradient lg = new LinearGradient(0, 0, getMeasuredWidth(), 0,
                Color.RED, Color.BLUE, Shader.TileMode.CLAMP);
        //混合渐变
        ComposeShader cs =
                new ComposeShader(bs3, lg, PorterDuff.Mode.SRC_ATOP);
        Paint paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(cs);
        canvas.drawRect(new Rect(0, 0, getMeasuredWidth(), 600), paint3);
    }
}
