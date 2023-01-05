package com.lch.test.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.lch.test.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);
        main();
    }

    public static String Logout(String s){
        Log.d("lch","logout");
        return s+"Logout";
    }

    /**
     * 通过 类名.class 获取
     */
    public static void hook1() {
        Class<HookActivity> mainActivityClass = HookActivity.class;

    }

    /**
     * 通过 对象.getClass() 获取
     * @param args
     */
    public static void hook2(String[] args) {
        HookActivity mainActivity = new HookActivity();
        Class<? extends HookActivity> aClass = mainActivity.getClass();
    }

    /**
     * 使用 Class 类的静态方法 public static Class<?> forName(String className) 获取，
     * 该方法抛出了 ClassNotFoundException 异常，因为所给出的全类名参数可能不能获取到其 Class 对象
     */
    public void main() {
        try {
            Class<?> aClass = Class.forName("com.lch.test.activity.HookActivity");
            System.out.println(aClass);
            Method method = aClass.getMethod("Logout",String.class);
            Object o=method.invoke(HookActivity.this,"hhs");
            Log.d("lch","invoke:"+o);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用类加载器 ClassLoader 获取
     * @param args
     */
    public static void main2(String[] args) {
        try {
            Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass("com.lch.test.activity.HookActivity");
            System.out.println(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}