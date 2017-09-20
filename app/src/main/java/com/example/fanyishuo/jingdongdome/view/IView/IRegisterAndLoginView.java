package com.example.fanyishuo.jingdongdome.view.IView;

import com.example.fanyishuo.jingdongdome.model.bean.LoginDataBean;

/**
 * Created by fanyishuo on 2017/9/6.
 */

public interface IRegisterAndLoginView extends IView {
    void onLoginSucced(LoginDataBean loginDataBean);
    void onLoginFail(String exception);
}
