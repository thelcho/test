package com.lch.test.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.lch.test.R;

public class AnomalousPhotoView extends View {
    private Bitmap bmpCat;
    private Bitmap bmpMask;
    private Paint paint;
    private static final int OFFSET = 100;

    public AnomalousPhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bmpCat = BitmapFactory.decodeResource(getResources(), R.mipmap.pic_icq);
        bmpMask = BitmapFactory.decodeResource(
                getResources(), R.mipmap.ic_launcher);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int id = canvas.saveLayer(OFFSET, OFFSET, bmpMask.getWidth() + OFFSET,
                bmpMask.getHeight() + OFFSET,
                null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bmpCat, 0, 0, null);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bmpMask, OFFSET, OFFSET, paint);
        canvas.restoreToCount(id);
    }
}
