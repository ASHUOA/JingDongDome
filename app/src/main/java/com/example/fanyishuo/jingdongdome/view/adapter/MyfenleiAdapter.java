package com.example.fanyishuo.jingdongdome.view.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.fanyishuo.jingdongdome.model.bean.fenleiOkHttpBean;
import com.example.fanyishuo.jingdongdome.view.fragment.MyfenleiFragment;

import java.util.List;

/**
 * Created by fanyishuo on 2017/9/11.
 */

public class MyfenleiAdapter extends FragmentPagerAdapter {
    List<fenleiOkHttpBean.DatasBean.ClassListBean> list;

    private String[] attr={"推荐分类","京东超市","国际名牌","奢侈品","全球购","男装","女装","女鞋","内衣搭配"};
    public MyfenleiAdapter(FragmentManager fm,List<fenleiOkHttpBean.DatasBean.ClassListBean> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        MyfenleiFragment myfenleiFragment=new MyfenleiFragment();
        Bundle bundle=new Bundle();
        bundle.putString("id",list.get(position).getGc_id());
        myfenleiFragment.setArguments(bundle);
        return myfenleiFragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getGc_name();
    }

}
