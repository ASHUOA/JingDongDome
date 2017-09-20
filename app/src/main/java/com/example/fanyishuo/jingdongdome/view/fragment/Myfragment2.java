package com.example.fanyishuo.jingdongdome.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fanyishuo.jingdongdome.R;
import com.example.fanyishuo.jingdongdome.model.bean.fenleiOkHttpBean;
import com.example.fanyishuo.jingdongdome.utils.OkHttpUtils;
import com.example.fanyishuo.jingdongdome.view.adapter.MyfenleiAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * Created by fanyishuo on 2017/9/6.
 */

public class Myfragment2 extends Fragment {

    private VerticalTabLayout vertab;
    private ViewPager viewpager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment2, container, false);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        vertab = (VerticalTabLayout) view.findViewById(R.id.vertab);
        viewpager = (ViewPager) view.findViewById(R.id.viewfenlei);
        init();

    }
    private void init(){
        OkHttpUtils.sendOkHttpRequest("http://169.254.129.115/mobile/index.php?act=goods_class", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson=new Gson();
                fenleiOkHttpBean bean = gson.fromJson(json, fenleiOkHttpBean.class);
                final List<fenleiOkHttpBean.DatasBean.ClassListBean> list = bean.getDatas().getClass_list();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewpager.setAdapter(new MyfenleiAdapter(getChildFragmentManager(),list));
                        vertab.setupWithViewPager(viewpager);
                    }
                });
            }
        });
    }
}
