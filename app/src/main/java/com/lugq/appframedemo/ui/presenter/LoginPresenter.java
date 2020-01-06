package com.lugq.appframedemo.ui.presenter;

import android.util.Log;

import com.lugq.appframedemo.entity.UserEntity;
import com.lugq.appframedemo.net.ApiService;
import com.lugq.appframedemo.ui.base.BasePresenter;
import com.lugq.appframedemo.ui.view.LoginView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginView> {
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private Disposable mDisposable;

    public LoginPresenter(LoginView view) {
        super(view);
    }

    public void login(String name, String pwd) {
        Observable<UserEntity> bookObservable = ApiService.createApiService().getInfo();
        bookObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserEntity>() {
                    //这是新加入的方法，在订阅后发送数据之前，
                    //回首先调用这个方法，而Disposable可用于取消订阅
                    @Override
                    public void onSubscribe(Disposable d) {
                        //mProgressBar.setVisibility(View.VISIBLE);
                        // 可以用于取消订阅
                        //d.dispose();
                        mDisposable = d;
                    }

                    // 需要界面上显示的
                    @Override
                    public void onNext(UserEntity user) {
                        //Log.i(TAG, "onNext:" + user.login);
                        mView.showUserInfo(user);
                    }

                    // 请求发生错误
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
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
