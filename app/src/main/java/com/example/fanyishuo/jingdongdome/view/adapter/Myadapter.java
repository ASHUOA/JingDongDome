package com.example.fanyishuo.jingdongdome.view.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.fanyishuo.jingdongdome.view.fragment.MyTabFragment;


/**
 * Created by fanyishuo on 2017/9/6.
 */

public class Myadapter extends FragmentPagerAdapter {
    String[] title={"精选","直播","订阅","视频购","问答","清单","社区","生活","数码","精选","直播","订阅","视频购","问答","清单","社区","生活","数码"};

    public Myadapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        MyTabFragment myfragment=new MyTabFragment();
        Bundle bundle=new Bundle();
        bundle.putString("title",title[position]);
        myfragment.setArguments(bundle);
        return myfragment;
    }

    @Override
    public int getCount() {
        return title!=null?title.length:0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }


}
