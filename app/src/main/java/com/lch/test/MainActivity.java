package com.lch.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.lch.test.activity.BroadcastActivity;
import com.lch.test.activity.ContentResolverActivity;
import com.lch.test.activity.CustomViewActivity;
import com.lch.test.activity.DatabaseActivity;
import com.lch.test.activity.HookActivity;
import com.lch.test.activity.LightSensorActivity;
import com.lch.test.activity.MyServiceActivity;

import org.w3c.dom.NodeList;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity {

    TextView textView,tv_hook,tv_custom_view,tv_service,tv_broadcast,
            tv_content_resolver,tv_database,tv_light_sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        tv_hook=findViewById(R.id.textView2);
        tv_custom_view=findViewById(R.id.tv_custom_view);
        tv_service=findViewById(R.id.tv_service);
        tv_broadcast=findViewById(R.id.tv_broadcast);
        tv_content_resolver=findViewById(R.id.tv_content_resolver);
        tv_database=findViewById(R.id.tv_database);
        tv_light_sensor=findViewById(R.id.tv_light_sensor);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("lch","onClick");
                Integer a =2;
                String b = "jd";
                b.equals(a);
                NodeList c;
            }
        });
        tv_hook.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HookActivity.class);
            startActivity(intent);
        });
        tv_custom_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomViewActivity.class);
                startActivity(intent);
            }
        });

        tv_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyServiceActivity.class);
                startActivity(intent);
            }
        });
        tv_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BroadcastActivity.class);
                startActivity(intent);
            }
        });
        tv_content_resolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContentResolverActivity.class);
                startActivity(intent);
            }
        });
        tv_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });
        tv_light_sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LightSensorActivity.class);
                startActivity(intent);
            }
        });

        hook(MainActivity.this,textView);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("lch","onTouch："+event.getAction());
                return false;
            }
        });
    }

//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            int width = view.getMeasureWidth(); int height = view.getMeasuredHeight();
//        }
//    }

    /*** hook 的核心代码* 这个方法的唯一目的：用自己的点击事件，替换掉 View 原来的点击事件** @param view hook 的范围仅限于这个 view*/
    @SuppressLint({"DiscouragedPrivateApi", "PrivateApi"})
    public static void hook(Context context, final View view) {
        try {
            // 反射执行 View 类的 getListenerInfo()方法，拿到 v 的 mListenerInfo 对象，这个对象就是点 击事件的持有者
            Method method = View.class.getDeclaredMethod("getListenerInfo");
            method.setAccessible(true);
            //由于 getListenerInfo()方法并不是 public 的，所以要加这个 代码来保证访问权限
            Object mListenerInfo = method.invoke(view);
            //这里拿到的就是 mListenerInfo 对象，也就 是点击事件的持有者 // 要从这里面拿到当前的点击事件对象
            Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
            // 这是 内部类的表示方法
            Field field = listenerInfoClz.getDeclaredField("mOnClickListener");
            final View.OnClickListener onClickListenerInstance = (View.OnClickListener) field.get(mListenerInfo);
            //取得真实的 mOnClickListener 对象
            // 2. 创建我们自己的点击事件代理类
            // 方式 1：自己创建代理类
            //ProxyOnClickListener proxyOnClickListener = new ProxyOnClickListener(onClickListenerInstance);
            // 方式 2：由于 View.OnClickListener 是一个接口，所以可以直接用动态代理模式 //
            // Proxy.newProxyInstance 的 3 个参数依次分别是：
            // 本地的类加载器;
            // 代理类的对象所继承的接口（用 Class 数组表示，支持多个接口）
            // 代理类的实际逻辑，封装在 new 出来的 InvocationHandler 内
            Object proxyOnClickListener = Proxy.newProxyInstance(context.getClass().getClassLoader(), new Class[]{View.OnClickListener.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Log.d("lch", "HookSetOnClickListener+点击事件被 hook 到了");
                    //加入自己的逻辑
                    return method.invoke(onClickListenerInstance, args);
                    //执行被代理的对象的逻辑

                    }
            });
            // 3. 用我们自己的点击事件代理类，设置到"持有者"中
            field.set(mListenerInfo, proxyOnClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 自定义代理类
    static class ProxyOnClickListener implements View.OnClickListener {
        View.OnClickListener oriLis;
        public ProxyOnClickListener(View.OnClickListener oriLis) {
            this.oriLis = oriLis;
        }
        @Override
        public void onClick(View v) {
            Log.d("HookSetOnClickListener", "点击事件被 hook 到了");
            if (oriLis != null) {
                oriLis.onClick(v);
            }
        }
    }
}