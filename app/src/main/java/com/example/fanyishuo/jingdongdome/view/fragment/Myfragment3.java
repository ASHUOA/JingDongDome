package com.example.fanyishuo.jingdongdome.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.fanyishuo.jingdongdome.R;
import com.example.fanyishuo.jingdongdome.view.adapter.Myadapter;
import com.library.zxing.activity.QRCodeScanFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by fanyishuo on 2017/9/6.
 */

public class Myfragment3 extends QRCodeScanFragment {

    private View inflate;
    private ProgressBar pro1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment3, null, false);
        //为了注册，在开始时注册
        EventBus.getDefault().register(this);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TabLayout tablayout = (TabLayout) inflate.findViewById(R.id.tab);
        ViewPager viewpager = (ViewPager) inflate.findViewById(R.id.viewpager);
        pro1 = (ProgressBar) inflate.findViewById(R.id.pro);

        Myadapter myadapter = new Myadapter(getChildFragmentManager());

            viewpager.setAdapter(myadapter);

//     viewpager.setOffscreenPageLimit(18);
        tablayout.setupWithViewPager(viewpager);

        ImageView saosao = (ImageView) inflate.findViewById(R.id.saosao);
        saosao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanQRCode();
            }
        });

//eventbus

    }
    //为了收消息，那边一发这边立马接收，可以传bean类
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String s) {
        pro1.setVisibility(View.GONE);
    };
    //注销
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
