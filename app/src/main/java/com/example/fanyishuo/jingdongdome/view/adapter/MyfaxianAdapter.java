package com.example.fanyishuo.jingdongdome.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fanyishuo.jingdongdome.R;
import com.example.fanyishuo.jingdongdome.model.bean.FaxianBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by fanyishuo on 2017/9/8.
 */

public class MyfaxianAdapter extends XRecyclerView.Adapter<MyfaxianAdapter.MyViewHolder> {

    Context context;
    List<FaxianBean.ResultBean.ListBean> list;

    public MyfaxianAdapter(Context context, List<FaxianBean.ResultBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    //创建布局和viewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.faxian_shuju, parent, false);
        return new MyViewHolder(inflate);

//        RecyclerView.LayoutManager layoutManager = ((XRecyclerView) parent).getLayoutManager();
//        View recyclerViewItem = null;
//        recyclerViewItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.faxian_shuju,parent,false);
//        return new MyViewHolder(recyclerViewItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getPic()).into(holder.imageView);
        holder.faxianshuju.setText(list.get(position).getTitle());
//        ImageLoader.getInstance().displayImage(list.get(position).getPic(),holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends XRecyclerView.ViewHolder{
         TextView faxianshuju;
         ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
           faxianshuju= (TextView) itemView.findViewById(R.id.faxianshuju);
            imageView= (ImageView) itemView.findViewById(R.id.faxianpic);
        }
    }

}
