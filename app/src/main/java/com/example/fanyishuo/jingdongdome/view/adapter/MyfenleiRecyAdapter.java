package com.example.fanyishuo.jingdongdome.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fanyishuo.jingdongdome.R;
import com.example.fanyishuo.jingdongdome.model.bean.fenleierjieBean;
import com.example.fanyishuo.jingdongdome.utils.OkHttpUtils;
import com.example.fanyishuo.jingdongdome.view.fragment.MyfenleiFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * Created by fanyishuo on 2017/9/12.
 */

public class MyfenleiRecyAdapter extends RecyclerView.Adapter<MyfenleiRecyAdapter.MyViewHolder> {

    List<fenleierjieBean.DatasBean.ClassListBean> list;
    Activity context;
    private String gc_id;

    public MyfenleiRecyAdapter(Activity context,List<fenleierjieBean.DatasBean.ClassListBean> list) {
        this.context=context;
         this.list=list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.LayoutManager layoutManager = ((RecyclerView) parent).getLayoutManager();
        View recyclerViewItem = null;
        recyclerViewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.fenlei_adapter, parent, false);
        return new MyViewHolder(recyclerViewItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText(list.get(position).getGc_name());

        gc_id = list.get(position).getGc_id();
        init(holder);

    }

    private void init(final MyViewHolder holder) {
        OkHttpUtils.sendOkHttpRequest("http://169.254.129.115/mobile/index.php?act=goods_class&gc_id=" + gc_id, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson=new Gson();
                fenleierjieBean bean = gson.fromJson(json, fenleierjieBean.class);
                final List<fenleierjieBean.DatasBean.ClassListBean> list = bean.getDatas().getClass_list();
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        holder.gridView.setAdapter(new GridViewAdpter(context,list));
                    }
                });

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private GridView gridView;
        private TextView title;
        public MyViewHolder(View itemView) {
            super(itemView);
            gridView= (GridView) itemView.findViewById(R.id.gridview);
            title= (TextView) itemView.findViewById(R.id.wenzi);
        }
    }
}
