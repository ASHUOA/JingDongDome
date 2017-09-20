package com.example.fanyishuo.jingdongdome.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fanyishuo.jingdongdome.R;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by fanyishuo on 2017/9/14.
 */

public class Yanzhengma extends AppCompatActivity implements View.OnClickListener {
    private EditText phone;
    private EditText yanzhengma;
    private TextView huoqu;
    private TextView huodeyanzheng1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yanzhengma);
        phone = (EditText) findViewById(R.id.phone);
        yanzhengma = (EditText) findViewById(R.id.yanzhengma);
        huoqu = (TextView) findViewById(R.id.huoqu);
        huodeyanzheng1 = (TextView) findViewById(R.id.huodeyanzheng1);
        huoqu.setOnClickListener(this);
        huodeyanzheng1.setOnClickListener(this);
        SMSSDK.registerEventHandler(eh);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.huoqu:
                SMSSDK.getVerificationCode("86", phone.getText().toString().trim(), new OnSendMessageHandler() {
                    @Override
                    public boolean onSendMessage(String s, String s1) {
                        return false;
                    }
                });
                break;
            case R.id.huodeyanzheng1:
                SMSSDK.submitVerificationCode("86", phone.getText().toString().trim(), yanzhengma.getText().toString().trim());
                Intent intent=new Intent(Yanzhengma.this,LoginActivity.class);
                startActivity(intent);
                break;

        }
    }
    EventHandler eh=new EventHandler(){
        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Yanzhengma.this,"验证成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                    //提交验证码成功
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Yanzhengma.this,"获取成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                    //获取验证码成功
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //返回支持发送验证码的国家列表
                }
            }else{
                ((Throwable)data).printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Yanzhengma.this,"失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }
}

