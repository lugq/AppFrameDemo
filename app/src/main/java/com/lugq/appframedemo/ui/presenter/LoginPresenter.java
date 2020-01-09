package com.lugq.appframedemo.ui.presenter;

import android.util.Log;

import com.lugq.appframedemo.entity.UserEntity;
import com.lugq.appframedemo.net.ApiService;
import com.lugq.appframedemo.net.BaseObserver;
import com.lugq.appframedemo.net.CommonSchedulers;
import com.lugq.appframedemo.ui.base.BasePresenter;
import com.lugq.appframedemo.ui.view.LoginView;

import io.reactivex.Observable;

public class LoginPresenter extends BasePresenter<LoginView> {
    private static final String TAG = LoginPresenter.class.getSimpleName();

    public LoginPresenter(LoginView view) {
        super(view);
    }

    public void login(String name, String pwd) {
        Observable<UserEntity> observable = ApiService.createApiService().getInfo();
        observable.compose(CommonSchedulers.io2main())
                .subscribe(new BaseObserver<UserEntity>() {
                    @Override
                    public void onNext(UserEntity s) {
                        Log.i(TAG, "请求成功");
                        mView.showUserInfo(s);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "LoginActivity destroy");
    }
}
