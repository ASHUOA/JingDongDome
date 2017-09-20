package com.example.fanyishuo.jingdongdome.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fanyishuo.jingdongdome.R;
import com.example.fanyishuo.jingdongdome.model.bean.FaxianBean;
import com.example.fanyishuo.jingdongdome.utils.OkHttpUtils;
import com.example.fanyishuo.jingdongdome.view.adapter.MyfaxianAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by fanyishuo on 2017/9/6.
 */

public class MyTabFragment extends Fragment implements XRecyclerView.LoadingListener {
    private XRecyclerView recy;
    private MyfaxianAdapter adapter;
    private List<String> list = new ArrayList<>();

    // private String title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle arguments = getArguments();
//        title = arguments.getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.tablayout, container, false);
        recy = (XRecyclerView) inflate.findViewById(R.id.xrecy);
        return inflate;
//        return inflater.inflate(R.layout.tablayout,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
//        recy = (XRecyclerView) view.findViewById(R.id.xrecy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recy.setLayoutManager(linearLayoutManager);

        init();

        recy.setLoadingListener(this);

    }

    public void init(){

        OkHttpUtils.sendOkHttpRequest("http://api.jisuapi.com/news/get?channel=头条&start=0&num=10&appkey=f687e2d75601ac18", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                String json = response.body().string();
                Log.e("TTTTTTTTT", "onResponse: "+json);
                Gson gson=new Gson();
                FaxianBean bean = gson.fromJson(json, FaxianBean.class);
                final List<FaxianBean.ResultBean.ListBean> list = bean.getResult().getList();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post("aaa");
                        adapter = new MyfaxianAdapter(getActivity(),list);
                        recy.setAdapter(adapter);
                    }
                });
            }
        });

    }

    @Override
    public void onRefresh() {
     //   list.clear();
//        init();
//        adapter.notifyDataSetChanged();
//        recy.refreshComplete();
    }

    @Override
    public void onLoadMore() {
//        init();
//        adapter.notifyDataSetChanged();
//        recy.loadMoreComplete();
    }
}
