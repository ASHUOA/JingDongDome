package com.example.fanyishuo.jingdongdome.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fanyishuo.jingdongdome.R;
import com.example.fanyishuo.jingdongdome.model.bean.LoginDataBean;
import com.example.fanyishuo.jingdongdome.model.bean.SQLiteBean;
import com.example.fanyishuo.jingdongdome.model.db.Dao;
import com.example.fanyishuo.jingdongdome.presenter.RegisterAndLoginPresenter;
import com.example.fanyishuo.jingdongdome.view.IView.IRegisterAndLoginView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.Map;

import static com.example.fanyishuo.jingdongdome.R.id.password;
import static com.example.fanyishuo.jingdongdome.R.id.username;

/**
 * Created by fanyishuo on 2017/9/6.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, IRegisterAndLoginView {

    private Button denglu;
    private RegisterAndLoginPresenter presenter;
    private ImageView qqlogin;
    private SharedPreferences.Editor edit;
    private TextView zhanghao;
    private TextView pwd;
    private Dao dao;
    private String username;
    private String password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        denglu = (Button) findViewById(R.id.denglu);
        denglu.setOnClickListener(this);
        SharedPreferences sp=getSharedPreferences("key",MODE_PRIVATE);
        edit = sp.edit();

        presenter = new RegisterAndLoginPresenter(this);

        //QQ登陆
        qqlogin = (ImageView) findViewById(R.id.qq);
        qqlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
            }
        });
        //短信验证
        TextView wangjimima = (TextView) findViewById(R.id.wangjimima);
        wangjimima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,Yanzhengma.class);
                startActivity(intent);
            }
        });
        TextView zhuce = (TextView) findViewById(R.id.zhuce);
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,ZhuCe.class);
                startActivity(intent);
            }
        });
        zhanghao = (TextView) findViewById(R.id.zhanghao);
        pwd = (TextView) findViewById(R.id.pwd);
        dao = new Dao(this);


    }

    @Override
    public void onClick(View v) {
        //presenter.log();
        ArrayList<SQLiteBean> list = dao.chaxun();
        for (SQLiteBean bean :list){
            username = bean.getUsername();
            password = bean.getPassword();
        }
        if(TextUtils.isEmpty(zhanghao.getText().toString()) || TextUtils.isEmpty(pwd.getText().toString())){
            Toast.makeText(this,"用户名和密码为空,请重新输入", Toast.LENGTH_SHORT).show();
        }else if (! zhanghao.getText().toString().equals(username) || !pwd.getText().toString().equals(password)){
            Toast.makeText(this,"用户名或密码输入错误,请重新输入", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"登录成功", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void onLoginSucced(LoginDataBean loginDataBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onLoginFail(String exception) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            ImageView pic = (ImageView) findViewById(R.id.qq);
            TextView name = (TextView) findViewById(R.id.name);
            String name1 = data.get("name");
            String iconurl = data.get("iconurl");
            edit.putString("name",name1);
            edit.putString("tu",iconurl);
            edit.commit();
            name.setText(name1);
            ImageLoader.getInstance().displayImage(iconurl,pic);

            Toast.makeText(LoginActivity.this, "Authorize succeed", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( LoginActivity.this, "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "Authorize cancel", Toast.LENGTH_SHORT).show();
        }

    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(LoginActivity.this).onActivityResult(requestCode, resultCode, data);
    }
}
