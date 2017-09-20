package com.example.fanyishuo.jingdongdome.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.fanyishuo.jingdongdome.R;
import com.example.fanyishuo.jingdongdome.view.adapter.MygouwuAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by fanyishuo on 2017/9/6.
 */

public class Myfragment4 extends Fragment {

    private View inflate;
    private XRecyclerView recygouwu;
    private List<String> list=new ArrayList<>();
    private HashMap<Integer,Boolean> ischek=new HashMap<>();
    private MygouwuAdapter adapter;
    private Set<Map.Entry<Integer, Boolean>> entries;
    private Button jiesuan;
    private TextView heji;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment4, container, false);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recygouwu = (XRecyclerView) inflate.findViewById(R.id.recygouwu);
        CheckBox quanxuan = (CheckBox) inflate.findViewById(R.id.quanxuan);
        jiesuan = (Button) inflate.findViewById(R.id.jiesuan);
        heji = (TextView) inflate.findViewById(R.id.heji);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recygouwu.setLayoutManager(layoutManager);
        init();
        adapter = new MygouwuAdapter(list,ischek,getContext());
        recygouwu.setAdapter(adapter);
//        adapter.setOnItemClickListener(new MygouwuAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                adapter.selectedAll();
//            }
//        });
        adapter.setOnItemLongClickListener(new MygouwuAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, int position) {
                adapter.revertSelected();
            }
        });
        adapter.setOnItemshanchuListener(new MygouwuAdapter.OnItemshanchuListener() {
            @Override
            public void onItemLongClick(View v, int position) {

                adapter.remove(position);
            }
        });

        quanxuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int count=0;
                entries = adapter.selectedAll();
                for (Map.Entry<Integer, Boolean> entry : entries) {
                    Boolean value = entry.getValue();
                    if (!value){
                    }else {
                        count++;
                    }
                }
                jiesuan.setText("去结算"+"("+count+")");
                heji.setText("合计: ￥"+count*228+".00");
            }
        });
        adapter.setOndanxuanjiesuanListener(new MygouwuAdapter.Ondanxuanjiesuan() {
            @Override
            public void onItemdanxuanClick(View v, HashMap<Integer, Boolean> map, int position) {
                int count2=0;
                Set<Map.Entry<Integer, Boolean>> entriess = map.entrySet();
                for (Map.Entry<Integer, Boolean> entry : entriess) {
                    Boolean value = entry.getValue();
                    if (!value){
                    }else {
                        count2++;
                    }
                }
                jiesuan.setText("去结算"+"("+count2+")");
                heji.setText("合计: ￥"+count2*228+".00");
            }
        });

    }

    public void init(){
        for (int i=0;i<15;i++){
            list.add("春秋季长款大风衣女装xxxxxxxxxxx"+i);
            ischek.put(i,false);
        }
    }
}
