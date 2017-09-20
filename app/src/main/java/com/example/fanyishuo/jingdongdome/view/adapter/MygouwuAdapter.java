package com.example.fanyishuo.jingdongdome.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fanyishuo.jingdongdome.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by fanyishuo on 2017/9/8.
 */

public class MygouwuAdapter extends XRecyclerView.Adapter<MygouwuAdapter.MyViewHolder> {

                List<String> list;
                HashMap<Integer,Boolean> map;
                Context context;
                private Set<Map.Entry<Integer, Boolean>> entries;

    public MygouwuAdapter(List<String> list,HashMap<Integer,Boolean> map,Context context){
                    this.list=list;
                    this.map=map;
                    this.context=context;
                }
                @Override
                public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    RecyclerView.LayoutManager mlayoutManager = ((RecyclerView) parent).getLayoutManager();
                    View recyclerViewItem = null;
                    recyclerViewItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.gouwuche_shuju,parent,false);

                    return new MyViewHolder(recyclerViewItem);
                }

                @Override
                public void onBindViewHolder(MyViewHolder holder, final int position) {
                    holder.zi.setText(list.get(position));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (monItemClickListener!=null){
//                    monItemClickListener.onItemClick(v,position);
//                }
//            }
//        });
                    holder.shanchu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (monItemshanchuListener!=null){
                    monItemshanchuListener.onItemLongClick(v,position);
                }
            }
        });
        holder.tu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (monItemLongClickListener!=null){
                    monItemLongClickListener.onItemLongClick(v,position);
                }
                return true;
            }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put(position, !map.get(position));
                notifyDataSetChanged();
                if (mondanxuanjiesuan!=null){
                    mondanxuanjiesuan.onItemdanxuanClick(v,map,position);
                }
            }
        });
        holder.checkBox.setChecked(map.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //删除
    public void remove(int position) {
        list.remove(position);
        notifyItemRangeRemoved(position, getItemCount());
    }
    //全选
    public Set<Map.Entry<Integer, Boolean>> selectedAll() {
        entries = map.entrySet();
        //如果发现有没有选中的item,我就应该去全部选中,这个变量就应该设置成true,否则就是false
        boolean shouldSelectedAll = false;
        //这个for循环就是判断一下接下来要全部选中,还是全部不选中
        for (Map.Entry<Integer, Boolean> entry : entries) {
            Boolean value = entry.getValue();
            //如果有没选中的,那就去全部选中 ,如果发现全都选中了那就全部不选中,
            if (!value) {
                shouldSelectedAll = true;
                break;
            }
        }
        //如果shouldSelectedAll为true说明需要全部选中,
        // 如果为false说明没有没有选中的,已经是是全部选中的状态,需要全部不选中
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(shouldSelectedAll);
        }
        notifyDataSetChanged();
        return entries;
    }

    //反选
    public void revertSelected() {
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(!entry.getValue());
        }
        notifyDataSetChanged();
    }

    //单选,点击checkBox的时候只选中当前的item,在checkBox的点击事件中调用
    public void singleSelected(int postion) {
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(false);
        }
        map.put(postion, true);
        notifyDataSetChanged();
    }
    public class MyViewHolder extends XRecyclerView.ViewHolder {
        private ImageView tu;
        private TextView zi;
        private CheckBox checkBox;
        TextView shanchu;
        View itemView;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            tu = (ImageView) itemView.findViewById(R.id.tu);
            zi= (TextView) itemView.findViewById(R.id.zi);
            checkBox= (CheckBox) itemView.findViewById(R.id.check);
            shanchu= (TextView) itemView.findViewById(R.id.shanchu);
        }

    }
    private OnItemClickListener monItemClickListener;
    private OnItemLongClickListener monItemLongClickListener;
    private OnItemshanchuListener monItemshanchuListener;
    private  Ondanxuanjiesuan mondanxuanjiesuan;
    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(View v,int position);
    }
    public interface OnItemshanchuListener{
        void onItemLongClick(View v,int position);
    }
    public interface Ondanxuanjiesuan{
        void onItemdanxuanClick(View v,HashMap<Integer,Boolean> map,int position);
    }
    //设置单击事件的接口
    public void setOnItemshanchuListener(OnItemshanchuListener sitemClickListener) {
        monItemshanchuListener = sitemClickListener;
    }
    //设置单击事件的接口
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        monItemClickListener = itemClickListener;
    }

    //设置长按事件的接口
    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        monItemLongClickListener = itemLongClickListener;
    }
    //设置长按事件的接口
    public void setOndanxuanjiesuanListener(Ondanxuanjiesuan ondanxuanjiesuan) {
        mondanxuanjiesuan = ondanxuanjiesuan;
    }

}
