package com.lch.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class WatchView extends View {
    private Paint paint;
    private Calendar calendar;
    public	WatchView(Context	context,	AttributeSet	attrs)	{
        super(context,	attrs);
        paint	=	new	Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        calendar	=	Calendar.getInstance();
    }
    public	void	run(){
        new Timer().schedule(new	TimerTask()	{
            @Override
            public	void	run()	{
                postInvalidate();
            }
        },	0,	1000);
    }
    @Override
    protected	void	onDraw(Canvas canvas)	{
        super.onDraw(canvas);
        //获取组件宽度
        int	width	=	this.getMeasuredWidth();
        //获取组件高度
        int	height	=	this.getMeasuredHeight();
        //计算圆盘直径，取短的
        int	len	=	Math.min(width,	height);
        //绘制表盘
        drawPlate(canvas,	len);
        //绘制指针
        drawPoints(canvas,	len);
    }
    /**
     *	 绘制表盘
     *	@param	canvas
     *	@param	len	 组件宽度
     */
    protected	void	drawPlate(Canvas	canvas,	int	len){
        canvas.save();
        //画圆
        int	r	=	len	/	2;
        canvas.drawCircle(r,	r,	r,	paint);
        //画刻度(一共有 60 根)
        for(int	i	=	0;	i	<	60;	i	++){
            if(i	%	5	==	0){
                //长刻度,长刻度占圆半径的 1/10
                paint.setColor(Color.RED);
                paint.setStrokeWidth(4);
                canvas.drawLine(r	+	9	*	r	/	10,	r,	len,	r,	paint);
            }else{
                //短刻度,长刻度占圆半径的 1/15
                paint.setColor(Color.GRAY);
                paint.setStrokeWidth(1);
                canvas.drawLine(r	+	14	*	r	/	15,	r,	len,	r,	paint);
            }
            //以（r，r）为中心，将画布旋转 6 度
            canvas.rotate(6,	r,	r);
        }
        canvas.restore();
    }
    /**
     *	 画指针
     *	@param	canvas
     *	@param	len
     */
    protected	void	drawPoints(Canvas	canvas,	int	len){
        //先获取系统时间
        calendar.setTimeInMillis(System.currentTimeMillis());
        //获取时分秒
        int	hours	=	calendar.get(Calendar.HOUR)	%	12;//转换为 12 小时制
        int	minutes	=	calendar.get(Calendar.MINUTE);
        int	seconds	=	calendar.get(Calendar.SECOND);
        //画时针
        //角度（顺时针）
        int	degree	=	360	/	12	*	hours;
        //转换成弧度
        double	radians	=	Math.toRadians(degree);
        //根据当前时计算时针两个点的坐标
        //起点（圆中心），终点：计算得到
        int	r	=	len	/	2;
        int	startX	=	r;
        int	startY	=	r;
        int	endX	=	(int)	(startX	+	r	*	0.5	*	Math.cos(radians));
        int	endY =	(int)	(startY	+	r	*	0.5	*	Math.sin(radians));
        canvas.save();
        paint.setStrokeWidth(3);
        //0 度从 3 点处开始，时间从 12 点处开始，所以需要将画布旋转 90 度
        canvas.rotate(-90,	r,	r);
        //画时针
        canvas.drawLine(startX,	startY,	endX,	endY,	paint);
        canvas.restore();
        //画分针
        //计算角度
        degree	=	360	/	60	*	minutes;
        radians	=	Math.toRadians(degree);
        endX	=	(int)	(startX	+	r	*	0.6	*	Math.cos(radians));
        endY	=	(int)	(startY	+	r	*	0.6	*	Math.sin(radians));
        canvas.save();
        paint.setStrokeWidth(2);
        //0 度从 3 点处开始，时间从 12 点处开始，所以需要将画布旋转 90 度
        canvas.rotate(-90,	r,	r);
        //画时针
        canvas.drawLine(startX,	startY,	endX,	endY,	paint);
        canvas.restore();
        //画秒针
        degree	=	360	/	60	*	seconds;
        radians	=	Math.toRadians(degree);
        endX	=	(int)	(startX	+	r	*	0.8	*	Math.cos(radians));
        endY	=	(int)	(startY	+	r	*	0.8	*	Math.sin(radians));
        canvas.save();
        paint.setStrokeWidth(1);
        //0 度从 3 点处开始，时间从 12 点处开始，所以需要将画布旋转 90 度
        canvas.rotate(-90,	r,	r);
        //画时针
        canvas.drawLine(startX,	startY,	endX,	endY,	paint);
        //再给秒针画个“尾巴”
        radians	=	Math.toRadians(degree	- 180);
        endX	=	(int)	(startX	+	r	*	0.15	*	Math.cos(radians));
        endY	=	(int)	(startY	+	r	*	0.15	*	Math.sin(radians));
        canvas.drawLine(startX,	startY,	endX,	endY,	paint);
        canvas.restore();
    }
}
