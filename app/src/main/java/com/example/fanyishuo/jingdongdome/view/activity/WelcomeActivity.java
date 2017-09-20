package com.example.fanyishuo.jingdongdome.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.fanyishuo.jingdongdome.R;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by fanyishuo on 2017/9/7.
 */

public class WelcomeActivity extends AppCompatActivity {
    private final static int COUNT = 1;
    private TextView countDown;
    private TextView tiaoguo;
    private Timer timer;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.welcome);
        initView();
        animation = AnimationUtils.loadAnimation(this, R.anim.item_anim);
    }
    //sehedule的第而个参数是第一次启动延时的时间，第三个是每隔多长时间执行一次。单位都是ms。
    //因此这里是每一秒发送一次消息给handler更新UI。
    //然后三秒后时间到了，在timer的第二个sehedule中进行跳转到另外一个界面

    private void initView() {
        countDown = (TextView) findViewById(R.id.miao);
        tiaoguo = (TextView) findViewById(R.id.tiaoguo);
        tiaoguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                handler.removeCallbacksAndMessages(null);
                timer.cancel();
                finish();
            }
        });
        timer = new Timer();
        final long end = System.currentTimeMillis() + 1000 * 5;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(COUNT);

            }
        }, 0, 1000);
        //这里的schedule的第二个参数意义是到了这个时间尽快运行run里面的方法
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
                timer.cancel();
            }
        }, new Date(end));

    }

    private Handler handler = new Handler() {
        int num = 5;

        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case COUNT:
                    countDown.setText(String.valueOf(num));
                    num--;
                    animation.reset();
                    countDown.startAnimation(animation);
                    break;

                default:
                    break;
            }
        }

        ;
    };
}

