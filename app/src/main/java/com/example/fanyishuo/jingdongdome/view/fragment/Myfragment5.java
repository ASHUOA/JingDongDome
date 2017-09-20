package com.example.fanyishuo.jingdongdome.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fanyishuo.jingdongdome.R;
import com.example.fanyishuo.jingdongdome.view.activity.LoginActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Created by fanyishuo on 2017/9/6.
 */

public class Myfragment5 extends Fragment implements View.OnClickListener {

    private View inflate;
    private ImageView touxiang;
    private TextView login;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment5, container, false);
        return inflate;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        touxiang = (ImageView) inflate.findViewById(R.id.touxiang);
        login = (TextView) inflate.findViewById(R.id.login);
        touxiang.setOnClickListener(this);
        login.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.touxiang:
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                Intent intent2=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .displayer(new CircleBitmapDisplayer())
                .build();
        SharedPreferences key = getActivity().getSharedPreferences("key", Context.MODE_PRIVATE);
        String name = key.getString("name", "登录/注册");
        String tu = key.getString("tu","https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=694224151,974401683&fm=27&gp=0.jpg");
        login.setText(name);
       // Glide.with(getActivity()).load(tu).into(touxiang);
        ImageLoader.getInstance().displayImage(tu,touxiang,options);

    }
}
