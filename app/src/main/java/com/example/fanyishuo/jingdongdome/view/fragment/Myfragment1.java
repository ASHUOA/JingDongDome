package com.example.fanyishuo.jingdongdome.view.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.bumptech.glide.Glide;
import com.example.fanyishuo.jingdongdome.R;
import com.example.fanyishuo.jingdongdome.model.bean.shouyeBean;
import com.example.fanyishuo.jingdongdome.utils.OkHttpUtils;
import com.example.fanyishuo.jingdongdome.view.IView.MyGrideView;
import com.example.fanyishuo.jingdongdome.view.adapter.shouyeMygridView_Adapter;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by fanyishuo on 2017/9/6.
 */

public class Myfragment1 extends Fragment {
    private Banner banner;
    private List<Integer> list;
    private ArrayList<View> listview;
    private Integer arr[] ={R.drawable.ban1,R.drawable.ban2,R.drawable.ban3,R.drawable.ban4};
    private ViewPager viewpager;
    private RadioGroup group;
    private MyGrideView mygrid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment1, container, false);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        banner= (Banner) view.findViewById(R.id.banner);
        list=new ArrayList<>();
        for (Integer i:arr
                ) {
            list.add(i);
        }
        banner.setImageLoader(new image());
        banner.setImages(list);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setDelayTime(3000);
        banner.start();
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        group = (RadioGroup) view.findViewById(R.id.group);
        RadioButton radioButton= (RadioButton) group.getChildAt(0);
        radioButton.setChecked(true);
        radioButton.setTextColor(Color.BLACK);
        listview = new ArrayList<View>();
        View view1 = View.inflate(getContext(), R.layout.fragment1_01, null);
        View view2 = View.inflate(getContext(), R.layout.fragment1_02, null);
        listview.add(view1);
        listview.add(view2);

        viewpager.addOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                for (int i = 0; i < listview.size(); i++) {
                    RadioButton radioButton = (RadioButton) group.getChildAt(i);
                    if (arg0==i) {
                        radioButton.setChecked(true);
                    }
                }
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });
        group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < listview.size(); i++) {
                    RadioButton radioButton = (RadioButton) group.getChildAt(i);

                    if (radioButton.isChecked()) {
                        viewpager.setCurrentItem(i);
                    }
                }
            }
        });
        //viewpager适配器
        viewpager.setAdapter(new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0==arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return listview.size();
            }
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView((View) object);
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View views = listview.get(position);
                container.addView(views);
                return views;
            }
        });

        //自定义gridView
        mygrid = (MyGrideView) view.findViewById(R.id.mygrid);
        init();


    }

    private void init() {
        OkHttpUtils.sendOkHttpRequest("http://apiv3.yangkeduo.com/v5/newlist?page=1&size=20", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson=new Gson();
                shouyeBean bean = gson.fromJson(json, shouyeBean.class);
                final List<shouyeBean.GoodsListBean> suibian = bean.getGoods_list();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mygrid.setAdapter(new shouyeMygridView_Adapter(suibian,getActivity()));
                    }
                });

            }
        });
    }

    public class image extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
