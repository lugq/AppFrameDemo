package com.lugq.appframedemo.ui.base;

/**
 * @Description
 * @Author Lu Guoqiang
 * @Time 2019/6/25 20:41
 */
public abstract class BasePresenter<V> {
    protected V mView;

    public BasePresenter(V view) {
        attachView(view);
    }

    public void attachView(V view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
    }
}
