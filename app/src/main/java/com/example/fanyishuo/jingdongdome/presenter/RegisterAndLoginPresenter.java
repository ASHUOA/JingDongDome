package com.example.fanyishuo.jingdongdome.presenter;

import com.example.fanyishuo.jingdongdome.model.LoginModel;
import com.example.fanyishuo.jingdongdome.model.bean.LoginDataBean;
import com.example.fanyishuo.jingdongdome.view.IView.IRegisterAndLoginView;

/**
 * Created by fanyishuo on 2017/9/6.
 */

public class RegisterAndLoginPresenter extends BasePresenter<IRegisterAndLoginView> {

    private LoginModel loginModel;

    public RegisterAndLoginPresenter(IRegisterAndLoginView iView) {
        super(iView);
    }

    @Override
    protected void initModle() {
        loginModel = new LoginModel();
    }
    public void log(){
        loginModel.login(new LoginModel.DataCallBack<LoginDataBean>() {
            @Override
            public void onGetDataSucced(LoginDataBean loginDataBean) {
                iView.onLoginSucced(loginDataBean);
            }

            @Override
            public void onGetDataFail(String exception) {
                iView.onLoginFail(exception);
            }
        });
    }
}
