package com.lugq.appframedemo.net;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        // 显示进度框
    }

    @Override
    public abstract void onNext(T t);

    @Override
    public void onError(Throwable e) {
        // 隐藏进度条，还可以提示错误消息
    }

    @Override
    public void onComplete() {
        // 隐藏进度框
    }
}
