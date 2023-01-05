package com.lch.test.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by obo on 15/8/26.
 */
public class MatrixView extends View {

    public final static String TAG = MatrixView.class.getCanonicalName();
    //bitmap运行矩阵
    Matrix matrix = new Matrix();
    //记录点
    PointF startPoint = new PointF();
    //自定义bitmap
    Bitmap bitmap = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);

    private Paint paint;
    private int width, height;
    private Path path;
    private Region circleRegion;

    public MatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Canvas canvas = new Canvas(bitmap);
        //直接在bitmap上面绘制一个小球
        canvas.drawCircle(50,50,50,new Paint());
    }
    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);

        circleRegion = new Region();
        path = new Path();
        //Path.Direction.CW---顺时针  Path.Direction.CCW逆时针
        path.addCircle(50, 50, 50, Path.Direction.CW);

        Region region = new Region(0, 0, width, height);
        circleRegion.setPath(path, region);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        init();

    }
    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, matrix, new Paint());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        if (event.getActionMasked() == MotionEvent.ACTION_MOVE)
        {
            matrix.postTranslate(event.getX() - startPoint.x, event.getY() - startPoint.y );
            //刷新
            this.postInvalidate();
        }

        startPoint.x = event.getX();
        startPoint.y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (circleRegion.contains((int) event.getX(), (int) event.getY())) {
                    Toast.makeText(getContext(), "触摸区域内", Toast.LENGTH_LONG).show();
                }
                break;
        }
        return true;
    }
}
