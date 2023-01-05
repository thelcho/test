package com.lch.test.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lch.test.R;
import com.lch.test.view.BallMoveView;
import com.lch.test.view.FlowLayout;
import com.lch.test.view.WatchView;

import java.util.Timer;
import java.util.TimerTask;

public class CustomViewActivity extends AppCompatActivity {

    private ImageView iv;
    private BallMoveView ballview;
    private WatchView watchView;
    private FlowLayout flowLayout;
    /**
     * 记录触摸点位置的变量
     */
    private int x;
    private int y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
//        iv = findViewById(R.id.iv);
        iv.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_UP:
                    x = (int) event.getRawX();
                    y = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int dx = (int) (event.getRawX() - x);
                    int dy = (int) (event.getRawY() - y);
                    // 更改imageView位置，原来View的四边距离
                    v.layout(v.getLeft() + dx, v.getTop() + dy, v.getRight() + dx, v.getBottom() + dy);
                    //获取移动后的位置
                    x = (int) event.getRawX();
                    y = (int) event.getRawY();
                    break;
                default:
                    break;
            }
            return true;
        });



//        ballview	=	(BallMoveView)	findViewById(R.id.ballview);

//        watchView	=	(WatchView)	findViewById(R.id.watch);
//        watchView.run();

        //drawText();
        //drawBitmap();
//        drawPoint();
//        drawLine();
//        drawRoundRect();
//        drawArc();
//        drawPath();
//        drawPathAdd();
//        drawQuadTo();
//        drawArcTo();
//        drawPathOp();
//        drawPathOpChange();
//        drawTextOnPath();


//        drawBallView();

        ViewGroup content = findViewById(android.R.id.content);//父布局
        View view = content.getChildAt(0);//内容布局

    }

    private void drawBallView() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ballview.postInvalidate();
            }
        }, 200, 50);
    }

    private void drawTextOnPath() {
        //下面的案例中绘制了 4 个字符串，一个绘制所有的字符串，中间两个截取子串进行绘制，最
        //后一个沿着 Path 绘制出所有的文字，为了更好的理解文字与路径的关系，所以把对应的路径也
        //绘制出来了。
        Bitmap bmpBuffer = Bitmap.createBitmap(500, 800,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(14);
        String text = "fff科技，移动互联网卓越品牌！ 我爱 Android！";
        canvas.drawText(text, 10, 50, paint);
        canvas.drawText(text, 0, 4, 10, 100, paint);
        canvas.drawText(text.toCharArray(), 5, 10, 10, 150, paint);
        Path path = new Path();
        path.moveTo(10, 200);
        path.quadTo(100, 100, 300, 300);
        canvas.drawTextOnPath(text, path, 15, 15, paint);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        iv.setImageBitmap(bmpBuffer);
    }

    private void drawPathOpChange() {
        Bitmap bmpBuffer = Bitmap.createBitmap(500, 800,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL); //请修改此语句
        Path path1 = new Path();
        path1.addRect(new RectF(10, 10, 110, 110), Path.Direction.CCW);
        Path path2 = new Path();
        path2.addCircle(100, 100, 50, Path.Direction.CCW);
        path1.op(path2, Path.Op.DIFFERENCE);//请修改此语句
        canvas.drawPath(path1, paint);
        iv.setImageBitmap(bmpBuffer);
    }

    private void drawPathOp() {
        Bitmap bmpBuffer = Bitmap.createBitmap(500, 800,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        Path path1 = new Path();
        path1.addRect(new RectF(10, 10, 110, 110), Path.Direction.CCW);
        Path path2 = new Path();
        path2.addCircle(100, 100, 50, Path.Direction.CCW);
        canvas.drawPath(path1, paint);
        paint.setColor(Color.RED);
        canvas.drawPath(path2, paint);
        iv.setImageBitmap(bmpBuffer);
    }

    private void drawArcTo() {
        Bitmap bmpBuffer = Bitmap.createBitmap(500, 800,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(100, 100);
        path.arcTo(new RectF(100, 150, 300, 300), -30, 60, false);
        canvas.drawPath(path, paint);
        iv.setImageBitmap(bmpBuffer);
    }

    private void drawQuadTo() {
        Bitmap bmpBuffer = Bitmap.createBitmap(500, 800,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(100, 100);
        path.quadTo(200, 10, 300, 300);
        canvas.drawPath(path, paint);
        //画点（100，100），（200，10），（300，300）
        paint.setStrokeWidth(4);
        paint.setColor(Color.RED);
        canvas.drawPoints(new float[]{100, 100, 200, 10, 300, 300}, paint);
        iv.setImageBitmap(bmpBuffer);
    }

    private void drawPathAdd() {
        Bitmap bmpBuffer = Bitmap.createBitmap(500, 800,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        //矩形
        path.addRect(new RectF(10, 10, 300, 100), Path.Direction.CCW);
        //圆角矩形，4 个角的弧度都不一样，2 个数确定一个弧度
        path.addRoundRect(new RectF(10, 120, 300, 220),
                new float[]{10, 20, 20, 10, 30, 40, 40, 30}, Path.Direction.CCW);
        //椭圆
        path.addOval(new RectF(10, 240, 300, 340), Path.Direction.CCW);
        //圆
        path.addCircle(60, 390, 50, Path.Direction.CCW);
        //弧线
        path.addArc(new RectF(10, 500, 300, 600), -30, -60);
        canvas.drawPath(path, paint);
        iv.setImageBitmap(bmpBuffer);
    }

    private void drawPath() {
        Bitmap bmpBuffer = Bitmap.createBitmap(500, 800,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(0, 150);
        path.rLineTo(300, 0);
        path.rLineTo(-300, 150);
        path.rLineTo(150, -300);
        path.rLineTo(150, 300);
        path.close();
        canvas.drawPath(path, paint);
        iv.setImageBitmap(bmpBuffer);
    }

    private void drawArc() {
        Bitmap bmpBuffer = Bitmap.createBitmap(500, 800,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        RectF rect = new RectF(10, 10, 400, 300);
        paint.setColor(Color.GRAY);
        canvas.drawOval(rect, paint);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        canvas.drawArc(rect, -30, -30, false, paint);
        iv.setImageBitmap(bmpBuffer);
    }

    private void drawRoundRect() {
        Bitmap bmpBuffer = Bitmap.createBitmap(500, 800,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRoundRect(10, 10, 400, 300, 50, 30, paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(new RectF(10, 320, 400, 620), 30, 50, paint);
        iv.setImageBitmap(bmpBuffer);
    }

    private void drawLine() {
        Bitmap bmpBuffer = Bitmap.createBitmap(500, 800,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(1);
        final int offsetDy = 50;
        for (int i = 0; i < 5; i++) {
            canvas.drawLine(10, offsetDy * i, 300, offsetDy * i, paint);
        }
        iv.setImageBitmap(bmpBuffer);
    }


    private void drawPoint() {
        Bitmap bmpBuffer = Bitmap.createBitmap(500, 800,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);
        //画一个红色的点
        canvas.drawPoint(120, 20, paint);
        paint.setColor(Color.BLUE);
        //两个数一组画 4 个蓝色的点
        float[] points1 = new float[]{10, 10, 50, 50, 50, 100, 50, 150};
        canvas.drawPoints(points1, paint);
        paint.setColor(Color.GREEN);
        //canvas.drawPoints(points1,	1,	4,	paint);
        iv.setImageBitmap(bmpBuffer);
    }

    private void drawBitmap() {
        Bitmap bmpBuffer = Bitmap.createBitmap(500, 800,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //原大小绘制
        canvas.drawBitmap(bmp, 0, 0, null);
        //对图片进行缩放
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        Rect src = new Rect(0, 0, bmpWidth, bmpHeight);
        Rect dst = new Rect(0, bmpHeight, bmpWidth * 3, bmpHeight * 3 + bmpHeight);
        canvas.drawBitmap(bmp, src, dst, null);
        iv.setImageBitmap(bmpBuffer);
    }

    private void drawText() {
        Bitmap bmp = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        //绘制文字
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.LEFT);
        int sp = 14;
        paint.setTextSize(sp);
        paint.setTextSkewX(0.5f);
        paint.setUnderlineText(true);
        paint.setFakeBoldText(true);
        canvas.drawText("移动互联网卓越领导者!", 10, 100, paint);
        //绘制图形
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        paint.setStrokeJoin(Paint.Join.BEVEL);//请修改枚举值查看效果
        //绘制一个矩形
        canvas.drawRect(new Rect(10, 200, 350, 350), paint);
        iv.setImageBitmap(bmp);
    }
}