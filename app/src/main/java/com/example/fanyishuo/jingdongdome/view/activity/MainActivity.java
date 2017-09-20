package com.example.fanyishuo.jingdongdome.view.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.fanyishuo.jingdongdome.R;
import com.example.fanyishuo.jingdongdome.view.fragment.Myfragment1;
import com.example.fanyishuo.jingdongdome.view.fragment.Myfragment2;
import com.example.fanyishuo.jingdongdome.view.fragment.Myfragment3;
import com.example.fanyishuo.jingdongdome.view.fragment.Myfragment4;
import com.example.fanyishuo.jingdongdome.view.fragment.Myfragment5;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private FrameLayout frame;
    private Myfragment1 myfragment1;
    private Myfragment2 myfragment2;
    private Myfragment3 myfragment3;
    private Myfragment4 myfragment4;
    private Myfragment5 myfragment5;
    private RadioButton shouye;
    private RadioGroup radiogroup;
    private RadioButton fenlei;
    private RadioButton faxian;
    private RadioButton gouwuche;
    private RadioButton wode;
    private List<Fragment> list;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        frame = (FrameLayout) findViewById(R.id.frame);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        shouye = (RadioButton) findViewById(R.id.shouye);
        fenlei = (RadioButton) findViewById(R.id.fenlei);
        faxian = (RadioButton) findViewById(R.id.faxian);
        gouwuche = (RadioButton) findViewById(R.id.gouwuche);
        wode = (RadioButton) findViewById(R.id.wode);

        myfragment1 = new Myfragment1();
        myfragment2 = new Myfragment2();
        myfragment3 = new Myfragment3();
        myfragment4 = new Myfragment4();
        myfragment5 = new Myfragment5();
//        list = new ArrayList<>();
//        list.add(myfragment1);
//        list.add(myfragment2);
//        list.add(myfragment3);
//        list.add(myfragment4);
//        list.add(myfragment5);
        getSupportFragmentManager().beginTransaction().add(R.id.frame,myfragment1).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.frame,myfragment2).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.frame,myfragment3).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.frame, myfragment4).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.frame,myfragment5).commit();

        radiogroup.check(R.id.shouye);
        radiogroup.setOnCheckedChangeListener(this);
        getSupportFragmentManager().beginTransaction().show(myfragment1).hide(myfragment2)
                .hide(myfragment3).hide(myfragment4).hide(myfragment5).commit();
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.shouye:
                getSupportFragmentManager().beginTransaction().show(myfragment1).hide(myfragment2)
                        .hide(myfragment3).hide(myfragment4).hide(myfragment5).commit();
                break;
            case R.id.fenlei:
                getSupportFragmentManager().beginTransaction().show(myfragment2).hide(myfragment1)
                        .hide(myfragment3).hide(myfragment4).hide(myfragment5).commit();
                break;
            case R.id.faxian:
                getSupportFragmentManager().beginTransaction().show(myfragment3).hide(myfragment2)
                        .hide(myfragment1).hide(myfragment4).hide(myfragment5).commit();
                break;
            case R.id.gouwuche:
                getSupportFragmentManager().beginTransaction().show(myfragment4).hide(myfragment2)
                        .hide(myfragment3).hide(myfragment1).hide(myfragment5).commit();
                break;
            case R.id.wode:
                getSupportFragmentManager().beginTransaction().show(myfragment5).hide(myfragment2)
                        .hide(myfragment3).hide(myfragment4).hide(myfragment1).commit();
                break;
        }
    }

}
