package com.example.fanyishuo.jingdongdome.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.fanyishuo.jingdongdome.R;
import com.example.fanyishuo.jingdongdome.model.bean.fenleierjieBean;
import com.example.fanyishuo.jingdongdome.utils.OkHttpUtils;
import com.example.fanyishuo.jingdongdome.view.adapter.MyfenleiRecyAdapter;
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
 * Created by fanyishuo on 2017/9/11.
 */

public class MyfenleiFragment extends Fragment {

    private Banner banner;
    private List<Integer> list;
    private Integer arr[] ={R.drawable.u1,R.drawable.u2,R.drawable.u3};
    private RecyclerView recyfenlei;
    private GridLayoutManager gridLayoutManager;
    private int i;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        i = Integer.valueOf(id).intValue();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fenlei_item,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        banner= (Banner) view.findViewById(R.id.feileiban);
        list=new ArrayList<>();
        for (Integer i:arr
                ) {
            list.add(i);
        }
        banner.setImageLoader(new image());
        banner.setImages(list);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setDelayTime(2000);
        banner.start();
        recyfenlei = (RecyclerView) view.findViewById(R.id.recyfenlei);
        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyfenlei.setLayoutManager(gridLayoutManager);
        init();


    }

    private void init() {
        OkHttpUtils.sendOkHttpRequest("http://169.254.129.115/mobile/index.php?act=goods_class&gc_id="+i, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson=new Gson();
                fenleierjieBean bean = gson.fromJson(json, fenleierjieBean.class);
                final List<fenleierjieBean.DatasBean.ClassListBean> list = bean.getDatas().getClass_list();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyfenlei.setAdapter(new MyfenleiRecyAdapter(getActivity(),list));
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
