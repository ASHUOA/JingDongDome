package com.example.fanyishuo.jingdongdome.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fanyishuo.jingdongdome.R;
import com.example.fanyishuo.jingdongdome.model.db.Dao;

/**
 * Created by 北城 on 2017/9/13.
 */

public class ZhuCe extends AppCompatActivity implements View.OnClickListener{

    private EditText username;
    private EditText password;
    private Button bt_zhuce;
    private Dao dao;
    private ImageView fan;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce_item);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        bt_zhuce = (Button) findViewById(R.id.bt_zhuce);
        bt_zhuce.setOnClickListener(this);
         dao = new Dao(this);
        fan = (ImageView) findViewById(R.id.fan);
        fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        String name = username.getText().toString().trim();
        String mima = password.getText().toString().trim();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(mima)){
            Toast.makeText(this,"用户名和密码为空不能完成注册", Toast.LENGTH_SHORT).show();
        }else {
            dao.add(name,mima);
            Toast.makeText(this,"注册完成，请前往登录界面！",Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
