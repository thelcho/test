package com.lch.test.utils;

import android.graphics.Rect;

public class CustomViewUtils {

    int left,right,top,bottom;

    //判断点（x，y）是否位于矩形内。
    public	boolean	contains(int	x,	int	y)	{
        return	left	<	right	&&	top	< bottom
                &&	x	>=	left	&&	x	<	right	&&	y	>=	top	&&	y	<	bottom;
    }

    //判断传递过来的矩形是否位于矩形内。
    public	boolean	contains(int	left,	int	top,	int	right,	int	bottom)	{
        return	this.left	<	this.right	&&	this.top	<	this.bottom
                &&	this.left	<=	left	&&	this.top	<=	top
                &&	this.right	>=	right	&&	this.bottom	>=	bottom;
    }


    //判断传递过来的矩形是否位于矩形内。
    public	boolean	contains(Rect r)	{
        return	this.left	<	this.right	&&	this.top	<	this.bottom
                &&	left	<=	r.left	&&	top	<=	r.top	&&	right	>=	r.right	&&	bottom	>=	r.bottom;
    }

    //传入 Rect 的 left、top、right、bottom，并将构建的 Rect 对象与当前 Rect 对象做交集
    //    运算，结果保存在当前 Rect 对象中。
    public	boolean	intersect(int	left,	int	top,	int	right,	int	bottom)	{
        if	(this.left	<	right	&&	left	<	this.right	&&	this.top	<	bottom	&&	top	<	this.bottom)	{
            if	(this.left	<	left)	this.left	=	left;
            if	(this.top	<	top)	this.top	=	top;
            if	(this.right	>	right)	this.right	=	right;
            if	(this.bottom	>	bottom)	this.bottom	=	bottom;
            return	true;
        }
        return	false;
    }

    //传入新的 Rect 对象，并将该对象与当前 Rect 对象做交集运算，结果保存在当前 Rect
    //    对象中。
    public	boolean	intersect(Rect	r)	{
        return	intersect(r.left,	r.top,	r.right,	r.bottom);
    }

    //union()方法是计算两个矩形的并集，传入一个新的 Rect，与当前 Rect 进行并集运算，
    //并将结果保存在当前 Rect 对象中。
    public	void	union(int	left,	int	top,	int	right,	int	bottom)	{
        if	((left	<	right)	&&	(top	<	bottom))	{
            if	((this.left	<	this.right)	&&	(this.top	<	this.bottom))	{
                if	(this.left	>	left)	this.left	=	left;
                if	(this.top	>	top)	this.top	=	top;
                if	(this.right	<	right)	this.right	=	right;
                if	(this.bottom	<	bottom)	this.bottom	=	bottom;
            }	else	{
                this.left	=	left;
                this.top	=	top;
                this.right	=	right;
                this.bottom	=	bottom;
            } } }
    public	void	union(Rect	r)	{
        union(r.left,	r.top,	r.right,	r.bottom);
    }



}
