package com.lugq.appframedemo.ui.activity;

import android.widget.EditText;

import com.lugq.appframedemo.R;
import com.lugq.appframedemo.entity.UserEntity;
import com.lugq.appframedemo.ui.base.BaseActivity;
import com.lugq.appframedemo.ui.presenter.LoginPresenter;
import com.lugq.appframedemo.ui.view.LoginView;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.et_name)
    EditText et_name;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.btn_login)
    public void onClickLogin() {
        String name = et_name.getText().toString();
        String pwd = et_name.getText().toString();
        mPresenter.login(name, pwd);
    }

    @Override
    public void showUserInfo(UserEntity user) {

    }
}
