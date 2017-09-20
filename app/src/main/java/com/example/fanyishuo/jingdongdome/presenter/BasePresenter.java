package com.example.fanyishuo.jingdongdome.presenter;

import android.content.Context;

import com.example.fanyishuo.jingdongdome.app.Myapp;
import com.example.fanyishuo.jingdongdome.view.IView.IView;

/**
 * Created by fanyishuo on 2017/9/6.
 */

public abstract class BasePresenter <T extends IView> {
    protected T iView;
    public BasePresenter(T iView){
        this.iView=iView;
        initModle();
    }
    protected abstract void initModle();

    Context context(){
        if (iView!=null&&iView.context()!=null){
            return iView.context();
        }
        return Myapp.appcontext();
    }
}
