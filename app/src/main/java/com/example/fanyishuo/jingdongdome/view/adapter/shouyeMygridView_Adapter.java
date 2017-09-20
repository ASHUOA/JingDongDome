package com.example.fanyishuo.jingdongdome.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fanyishuo.jingdongdome.R;
import com.example.fanyishuo.jingdongdome.model.bean.shouyeBean;

import java.util.List;

/**
 * Created by fanyishuo on 2017/9/14.
 */

public class shouyeMygridView_Adapter extends BaseAdapter {
    List<shouyeBean.GoodsListBean> suibian;
    Context context;

    public shouyeMygridView_Adapter(List<shouyeBean.GoodsListBean> suibian, Context context) {
        this.suibian = suibian;
        this.context = context;
    }

    @Override
    public int getCount() {
        return suibian.size();
    }

    @Override
    public Object getItem(int position) {
        return suibian.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.shouye_mygrid, null);
            holder = new MyViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.tv_text.setText(suibian.get(position).getGoods_name());
        holder.price.setText(suibian.get(position).getNormal_price() + "");
        Glide.with(context).load(suibian.get(position).getImage_url()).into(holder.img);
        return convertView;

    }
        class MyViewHolder{
            ImageView img;
            TextView tv_text;
            TextView price;
        }

    }
