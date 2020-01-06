package com.lugq.appframedemo.ui.presenter;

import android.util.Log;

import com.lugq.appframedemo.entity.UserEntity;
import com.lugq.appframedemo.net.ApiService;
import com.lugq.appframedemo.net.BaseObserver;
import com.lugq.appframedemo.net.CommonSchedulers;
import com.lugq.appframedemo.ui.base.BasePresenter;
import com.lugq.appframedemo.ui.view.LoginView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class LoginPresenter extends BasePresenter<LoginView> {
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private Disposable mDisposable;

    public LoginPresenter(LoginView view) {
        super(view);
    }

    public void login(String name, String pwd) {
        Observable<UserEntity> bookObservable = ApiService.createApiService().getInfo();
        bookObservable.compose(CommonSchedulers.io2main())
                .subscribe(new BaseObserver<UserEntity>() {
                    @Override
                    public void onNext(UserEntity s) {
                        Log.i(TAG, "输出：" + s);
                        mView.showUserInfo(s);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "LoginActivity destroy");
        if (mDisposable != null)
            mDisposable.dispose();
    }
}
