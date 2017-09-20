package com.example.fanyishuo.jingdongdome.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fanyishuo.jingdongdome.R;
import com.example.fanyishuo.jingdongdome.model.bean.fenleierjieBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanyishuo on 2017/9/12.
 */

public class GridViewAdpter extends BaseAdapter {
    Context context;
    List<fenleierjieBean.DatasBean.ClassListBean> list;
    public GridViewAdpter(Context context,List<fenleierjieBean.DatasBean.ClassListBean> list){
        this.context=context;
        this.list=list;
//        list=new ArrayList<>();
//        for (int i=0;i<6;i++){
//            list.add("猕猴桃");
//        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewholder vh=null;
        if (convertView==null){
            vh=new viewholder();
            convertView=convertView.inflate(context,R.layout.gridview_fenlei,null);
            vh.iv = (ImageView) convertView.findViewById(R.id.iv);
            vh.tv= (TextView) convertView.findViewById(R.id.tt);
            convertView.setTag(vh);
        }else {
            vh= (viewholder) convertView.getTag();
        }
        vh.tv.setText(list.get(position).getGc_name());
        //ImageLoader.getInstance().displayImage(list.get(position),vh.iv.);
        return convertView;
    }
    class viewholder{
        ImageView iv;
        TextView tv;
    }
}
